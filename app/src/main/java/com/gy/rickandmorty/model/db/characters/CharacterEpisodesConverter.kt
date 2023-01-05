package com.gy.rickandmorty.model.db.characters

import androidx.room.TypeConverter

const val SEPARATOR = ","

class CharacterEpisodesConverter {
    @TypeConverter
    fun fromList(episodesList: List<String>) = episodesList.joinToString(SEPARATOR) { it }

    @TypeConverter
    fun fromString(episodesString: String) = episodesString.split(SEPARATOR)
}