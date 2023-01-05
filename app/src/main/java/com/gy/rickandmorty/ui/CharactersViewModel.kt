package com.gy.rickandmorty.ui

import androidx.lifecycle.ViewModel
import com.gy.rickandmorty.domain.usecases.CharactersUseCase
import com.gy.rickandmorty.model.network.ApiHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject internal constructor(private val useCase: CharactersUseCase, private val apiHelper: ApiHelper) : ViewModel() {
    val charactersFlow = useCase.getCharacters()
}