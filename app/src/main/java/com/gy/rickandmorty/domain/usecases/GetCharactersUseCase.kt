package com.gy.rickandmorty.domain.usecases

import com.gy.rickandmorty.model.CharactersRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetCharactersUseCase @Inject constructor(private val charactersRepo: CharactersRepository) {

   operator fun invoke() = charactersRepo.getCharactersStream()

}