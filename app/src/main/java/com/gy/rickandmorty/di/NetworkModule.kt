package com.gy.rickandmorty.di

import com.gy.rickandmorty.model.network.CharactersApiHelper
import com.gy.rickandmorty.model.network.CharactersApiHelperImpl
import com.gy.rickandmorty.model.network.CharactersApiContract
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Provides
    fun provideBaseURL() = "https://rickandmortyapi.com/api/"

    @Singleton
    @Provides
    fun provideRetrofit(BASE_URL: String): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
    }

    @Provides
    @Singleton
    fun provideApiHelper(apiHelperImpl: CharactersApiHelperImpl) : CharactersApiHelper {
        return apiHelperImpl
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit) : CharactersApiContract {
        return retrofit.create(CharactersApiContract::class.java)
    }
}