package com.gy.rickandmorty.model

import com.gy.rickandmorty.model.network.ApiHelper
import javax.inject.Inject

class CharactersRepo @Inject constructor(private val apiHelper: ApiHelper) {
//    suspend fun getCharacters() = apiHelper.getCharacters()
}