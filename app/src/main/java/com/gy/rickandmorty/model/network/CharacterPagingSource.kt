package com.gy.rickandmorty.model.network

import android.accounts.NetworkErrorException
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.gy.rickandmorty.model.entities.ShowCharacter
import javax.inject.Inject

class CharacterPagingSource @Inject constructor(private val apiHelper: ApiHelper): PagingSource<Int, ShowCharacter>() {
    override fun getRefreshKey(state: PagingState<Int, ShowCharacter>): Int? {
        // Try to find the page key of the closest page to anchorPosition, from
        // either the prevKey or the nextKey, but you need to handle nullability
        // here:
        //  * prevKey == null -> anchorPage is the first page.
        //  * nextKey == null -> anchorPage is the last page.
        //  * both prevKey and nextKey null -> anchorPage is the initial page, so
        //    just return null.
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ShowCharacter> {
        return try {
            // Start refresh at page 1 if undefined.
            val nextPageNumber = (params.key ?: 0) + 1
            val response = apiHelper.getCharacters(nextPageNumber)

            response.body()?.let {
                LoadResult.Page(
                    data = it.results,
                    prevKey = null, // Only paging forward.
                    nextKey = if (it.info.pages == nextPageNumber) null else nextPageNumber
                )
            } ?: LoadResult.Error(NetworkErrorException(response.errorBody()?.string() ?: "No result"))
        } catch (e: Exception) {
            // Handle errors in this block and return LoadResult.Error if it is an
            // expected error (such as a network failure).
            LoadResult.Error(e)
        }
    }
}