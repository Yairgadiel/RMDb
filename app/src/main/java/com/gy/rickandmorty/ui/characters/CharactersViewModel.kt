package com.gy.rickandmorty.ui.characters

import androidx.lifecycle.ViewModel
import com.gy.rickandmorty.domain.usecases.GetCharactersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject internal constructor(private val getCharacters: GetCharactersUseCase) : ViewModel() {
    val charactersFlow = getCharacters()
}