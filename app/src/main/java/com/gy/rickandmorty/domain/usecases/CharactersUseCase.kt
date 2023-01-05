package com.gy.rickandmorty.domain.usecases

import com.gy.rickandmorty.model.CharactersRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CharactersUseCase @Inject constructor(private val charactersRepo: CharactersRepository) {
   fun getCharacters() = charactersRepo.getCharactersStream()
}