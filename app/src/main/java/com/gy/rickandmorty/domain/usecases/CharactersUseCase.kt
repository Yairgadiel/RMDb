package com.gy.rickandmorty.domain.usecases

import com.gy.rickandmorty.model.CharactersRepo
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CharactersUseCase @Inject constructor(private val charactersRepo: CharactersRepo) {
//    suspend fun getCharacters() = charactersRepo.getCharacters()
}