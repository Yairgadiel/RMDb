package com.gy.rickandmorty.model.db.characterPages

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CharacterPagesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(characterPages: List<CharacterPage>)

    @Query("SELECT * FROM CharacterPages WHERE characterId = :characterId")
    suspend fun getPageForCharacter(characterId: Int): CharacterPage

    @Query("DELETE FROM CharacterPages")
    suspend fun clearAll()
}