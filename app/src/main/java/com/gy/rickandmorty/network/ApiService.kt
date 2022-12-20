package com.gy.rickandmorty.network

import com.gy.rickandmorty.model.CharactersResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("character/?page=1")
    suspend fun getCharacters() : Response<CharactersResponse>
}