package com.gy.rickandmorty.model.db.utilities

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface UtilitiesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg utilities: AppUtilities)

    @Update
    suspend fun update(utilities: AppUtilities)

    @Query("SELECT * FROM Utilities WHERE id = :id")
    suspend fun getUtilitiesById(id: Int): AppUtilities?

    @Query("DELETE FROM Utilities")
    suspend fun clearAll()
}