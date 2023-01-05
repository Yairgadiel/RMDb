package com.gy.rickandmorty.model.db.utilities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Utilities")
data class AppUtilities(
    @PrimaryKey(autoGenerate = true) val id: Int,
    var characterPages: Int
)