package com.gy.rickandmorty.model.entities

import androidx.room.ColumnInfo

data class Origin(
    @ColumnInfo(name = "originName")
    val name: String,
    @ColumnInfo(name = "originUrl")
    val url: String
)
