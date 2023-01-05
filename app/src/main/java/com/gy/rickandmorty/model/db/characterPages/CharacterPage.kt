package com.gy.rickandmorty.model.db.characterPages

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "CharacterPages")
data class CharacterPage(
    @PrimaryKey val characterId: Int,
    val page: Int
)
