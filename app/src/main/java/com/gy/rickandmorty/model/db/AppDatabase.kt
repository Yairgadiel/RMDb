package com.gy.rickandmorty.model.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.gy.rickandmorty.model.db.characterPages.CharacterPage
import com.gy.rickandmorty.model.db.characterPages.CharacterPagesDao
import com.gy.rickandmorty.model.db.characters.CharacterEpisodesConverter
import com.gy.rickandmorty.model.db.characters.CharactersDao
import com.gy.rickandmorty.model.db.utilities.AppUtilities
import com.gy.rickandmorty.model.db.utilities.UtilitiesDao
import com.gy.rickandmorty.model.entities.ShowCharacter

@Database(entities = [ShowCharacter::class, AppUtilities::class, CharacterPage::class], version = 1)
@TypeConverters(CharacterEpisodesConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun charactersDao(): CharactersDao
    abstract fun utilitiesDao(): UtilitiesDao
    abstract fun characterPagesDao(): CharacterPagesDao
}