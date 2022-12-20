package com.gy.rickandmorty.network

import com.gy.rickandmorty.model.CharactersResponse
import retrofit2.Response
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(
    private val apiService: ApiService
):ApiHelper {
    override suspend fun getCharacters(): Response<CharactersResponse> = apiService.getCharacters()
}