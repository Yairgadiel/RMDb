package com.gy.rickandmorty.network

import com.gy.rickandmorty.model.CharacterResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET
    suspend fun getCharacters() : Response<List<CharacterResponse>>
}