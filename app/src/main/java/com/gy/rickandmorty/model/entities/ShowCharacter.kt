package com.gy.rickandmorty.model.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Characters")
data class ShowCharacter(
    @PrimaryKey val id: Int,
    val created: String,
    val episode: List<String>,
    val gender: String,
    val image: String,
    @Embedded val location: Location,
    val name: String,
    @Embedded val origin: Origin,
    val species: String,
    val status: String,
    val type: String,
    val url: String
)