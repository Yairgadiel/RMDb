package com.gy.rickandmorty.di

import android.content.Context
import androidx.room.Room
import com.gy.rickandmorty.model.db.AppDatabase
import com.gy.rickandmorty.model.db.characterPages.CharacterPagesDao
import com.gy.rickandmorty.model.db.characters.CharactersDao
import com.gy.rickandmorty.model.db.utilities.UtilitiesDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {
    @Provides
    fun provideCharactersDao(appDatabase: AppDatabase): CharactersDao {
        return appDatabase.charactersDao()
    }

    @Provides
    fun provideUtilitiesDao(appDatabase: AppDatabase): UtilitiesDao {
        return appDatabase.utilitiesDao()
    }

    @Provides
    fun provideCharacterPagesDao(appDatabase: AppDatabase): CharacterPagesDao {
        return appDatabase.characterPagesDao()
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "RnM_DB"
        ).build()
    }
}