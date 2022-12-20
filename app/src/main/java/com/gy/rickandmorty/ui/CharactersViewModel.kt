package com.gy.rickandmorty.ui

import android.accounts.NetworkErrorException
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gy.rickandmorty.Resource
import com.gy.rickandmorty.domain.usecases.CharactersUseCase
import com.gy.rickandmorty.model.CharactersResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject internal constructor(private val useCase: CharactersUseCase) : ViewModel() {

    private val _res = MutableLiveData<Resource<CharactersResponse>>()
    val res : LiveData<Resource<CharactersResponse>> = _res

    init {
        load()
    }

    fun load() {
        _res.postValue(Resource.Loading())
        viewModelScope.launch {
            val charactersRes = useCase.getCharacters()
            if (!charactersRes.isSuccessful) {
                _res.postValue(Resource.NetworkError(NetworkErrorException(charactersRes.errorBody().toString())))
            }
            else {
                _res.postValue(Resource.Success(charactersRes.body()))
            }
        }
    }
}