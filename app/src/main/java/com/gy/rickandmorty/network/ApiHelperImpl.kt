package com.gy.rickandmorty.network

import com.gy.rickandmorty.model.CharactersResponse
import retrofit2.Response
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(
    private val apiContract: ApiContract
):ApiHelper {
    override suspend fun getCharacters(page: Int): Response<CharactersResponse> = apiContract.getCharacters(page)
}