package com.gy.rickandmorty.model.db.characters

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gy.rickandmorty.model.entities.ShowCharacter

@Dao
interface CharactersDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(characters: List<ShowCharacter>?)

    @Query("SELECT * FROM Characters")
    fun pagingSource(): PagingSource<Int, ShowCharacter>

    @Query("DELETE FROM Characters")
    suspend fun clearAll()
}