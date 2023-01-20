package com.gy.rickandmorty.model.network

import com.gy.rickandmorty.model.CharactersResponse
import com.gy.rickandmorty.model.entities.ShowCharacter
import retrofit2.Response
import javax.inject.Inject

class CharactersApiHelperImpl @Inject constructor(
    private val charactersApiContract: CharactersApiContract
): CharactersApiHelper {
    override suspend fun getCharacters(page: Int): Response<CharactersResponse> =
        charactersApiContract.getAllCharacters(page)
    override suspend fun getCharacterById(characterId: Int): Response<ShowCharacter> =
        charactersApiContract.getCharacterById(characterId)

}