package com.gy.rickandmorty.domain.usecases

import com.gy.rickandmorty.model.CharactersRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetCharacterByIdUseCase @Inject constructor(private val charactersRepo: CharactersRepository) {

   suspend operator fun invoke(characterId: Int) = charactersRepo.getCharacterById(characterId)

}