package com.gy.rickandmorty.model.entities

import androidx.room.ColumnInfo

data class Location(
    @ColumnInfo(name = "locationName")
    val name: String,
    @ColumnInfo(name = "locationUrl")
    val url: String
)