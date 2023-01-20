package com.gy.rickandmorty.model

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.gy.rickandmorty.model.db.AppDatabase
import com.gy.rickandmorty.model.entities.ShowCharacter
import com.gy.rickandmorty.model.network.CharactersApiHelper
import com.gy.rickandmorty.model.pagination.CharactersRemoteMediator
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CharactersRepository @Inject constructor(private val charactersApiHelper: CharactersApiHelper, private val db: AppDatabase) {
    @OptIn(ExperimentalPagingApi::class)
    fun getCharactersStream(): Flow<PagingData<ShowCharacter>> {
        val pagingSourceFactory = { db.charactersDao().pagingSource() }
        return Pager(
            config = PagingConfig(pageSize = 5, enablePlaceholders = true),
            remoteMediator = CharactersRemoteMediator(
                database = db,
                charactersApiHelper = charactersApiHelper
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }

    suspend fun getCharacterById(id: Int) = charactersApiHelper.getCharacterById(id)
}