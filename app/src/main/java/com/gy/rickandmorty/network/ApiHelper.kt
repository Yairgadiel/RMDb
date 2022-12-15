package com.gy.rickandmorty.network

import com.gy.rickandmorty.model.CharacterResponse
import retrofit2.Response

interface ApiHelper {
    suspend fun getCharacters() : Response<List<CharacterResponse>>
}