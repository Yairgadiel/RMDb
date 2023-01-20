package com.gy.rickandmorty.model.network

import com.gy.rickandmorty.model.CharactersResponse
import com.gy.rickandmorty.model.entities.ShowCharacter
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CharactersApiContract {

    // Maybe don't ask for all the details
    @GET("character")
    suspend fun getAllCharacters(@Query("page") page: Int) : Response<CharactersResponse>

    @GET("character/{id}")
    suspend fun getCharacterById(@Path("id") id: Int) : Response<ShowCharacter>
}