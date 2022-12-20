package com.gy.rickandmorty.network

import com.gy.rickandmorty.model.CharactersResponse
import retrofit2.Response

interface ApiHelper {
    suspend fun getCharacters() : Response<CharactersResponse>
}