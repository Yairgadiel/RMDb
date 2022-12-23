package com.gy.rickandmorty.model.network

import com.gy.rickandmorty.model.CharactersResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiContract {
    @GET("character")
    suspend fun getCharacters(@Query("page") page: Int) : Response<CharactersResponse>
}