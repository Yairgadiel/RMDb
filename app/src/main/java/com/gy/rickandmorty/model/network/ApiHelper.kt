package com.gy.rickandmorty.model.network

import com.gy.rickandmorty.model.CharactersResponse
import retrofit2.Response

interface ApiHelper {
    suspend fun getCharacters(page: Int) : Response<CharactersResponse>
}