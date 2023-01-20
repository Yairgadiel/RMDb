package com.gy.rickandmorty.model.network

import com.gy.rickandmorty.model.CharactersResponse
import com.gy.rickandmorty.model.entities.ShowCharacter
import retrofit2.Response

interface CharactersApiHelper {
    suspend fun getCharacters(page: Int) : Response<CharactersResponse>

    suspend fun getCharacterById(characterId: Int) : Response<ShowCharacter>
}