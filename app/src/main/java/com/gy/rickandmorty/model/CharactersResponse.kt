package com.gy.rickandmorty.model

import com.gy.rickandmorty.model.entities.Info
import com.gy.rickandmorty.model.entities.ShowCharacter

data class CharactersResponse(
    val info: Info,
    val results: List<ShowCharacter>
)