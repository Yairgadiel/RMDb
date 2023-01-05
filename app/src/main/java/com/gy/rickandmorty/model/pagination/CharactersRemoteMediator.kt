package com.gy.rickandmorty.model.pagination

import android.accounts.NetworkErrorException
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.gy.rickandmorty.model.db.AppDatabase
import com.gy.rickandmorty.model.db.characterPages.CharacterPage
import com.gy.rickandmorty.model.db.utilities.AppUtilities
import com.gy.rickandmorty.model.entities.ShowCharacter
import com.gy.rickandmorty.model.network.ApiHelper
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class CharactersRemoteMediator @Inject constructor(private val database: AppDatabase,
                                                   private val apiHelper: ApiHelper
) : RemoteMediator<Int, ShowCharacter>() {
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, ShowCharacter>
    ): MediatorResult {
        return try {
            val appUtilities = database.utilitiesDao().getUtilitiesById(1)

            // The network load method takes an optional after=<user.id>
            // parameter. For every page after the first, pass the last user
            // ID to let it continue from where it left off. For REFRESH,
            // pass null to load the first page.
            val page = when (loadType) {
                LoadType.REFRESH -> 0
                // No need to prepend, since REFRESH will always load the first page in the list.
                // Immediately return, reporting end of pagination.
                LoadType.PREPEND -> null
                LoadType.APPEND -> {
                    // You must explicitly check if the last item is null when
                    // appending, since passing null to networkService is only
                    // valid for initial load. If lastItem is null it means no
                    // items were loaded after the initial REFRESH and there are
                    // no more items to load.
                    val lastItem = state.lastItemOrNull()
                    if (lastItem == null) {
                        null
                    }
                    else {
                        val nextPage =
                            database.characterPagesDao().getPageForCharacter(lastItem.id).page + 1

                        if (appUtilities == null || nextPage <= appUtilities.characterPages) {
                            nextPage
                        }
                        else {
                            null
                        }
                    }
                }
            } ?: return MediatorResult.Success(
                endOfPaginationReached = true
            )

            // Suspending network load via Retrofit. This doesn't need to be
            // wrapped in a withContext(Dispatcher.IO) { ... } block since
            // Retrofit's Coroutine CallAdapter dispatches on a worker
            // thread.
            val response = apiHelper.getCharacters(page)
            val charactersRes = response.body() ?: throw NetworkErrorException("Empty results")
            val pagesCount = charactersRes.info.pages

            database.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    database.charactersDao().clearAll()
                    database.characterPagesDao().clearAll()
                }

                if (appUtilities == null) {
                    database.utilitiesDao().insertAll(AppUtilities(0, pagesCount))
                }
                else {
                    // Update the DB with the received pages num if had changed
                    if (pagesCount == appUtilities.characterPages) {
                        database.utilitiesDao()
                            .update(appUtilities.also {
                                it.characterPages = pagesCount
                            })
                    }
                }
                // Insert new characters into database, which invalidates the current PagingData,
                // allowing Paging to present the updates in the DB.
                database.charactersDao().insertAll(charactersRes.results)

                // Insert the new character pages
                database.characterPagesDao().insertAll(charactersRes.results.map {CharacterPage(it.id, page) })
            }

            MediatorResult.Success(
                endOfPaginationReached = pagesCount == page
            )
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }

    override suspend fun initialize(): InitializeAction {
        // TODO To make sure we are updated over time,
        //  we need to check if the remote data had changed (maybe character count?)

        val appUtilities = database.utilitiesDao().getUtilitiesById(1)

        // if db was not init - refresh
        return (if (appUtilities == null)
            InitializeAction.LAUNCH_INITIAL_REFRESH
        else
            InitializeAction.SKIP_INITIAL_REFRESH)
    }
}