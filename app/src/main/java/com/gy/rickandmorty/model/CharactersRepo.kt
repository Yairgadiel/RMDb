package com.gy.rickandmorty.model

import com.gy.rickandmorty.network.ApiHelper
import javax.inject.Inject

class CharactersRepo @Inject constructor(private val apiHelper: ApiHelper) {
    suspend fun getCharacters() = apiHelper.getCharacters()
}