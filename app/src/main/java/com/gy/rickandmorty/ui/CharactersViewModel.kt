package com.gy.rickandmorty.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.gy.rickandmorty.Resource
import com.gy.rickandmorty.domain.usecases.CharactersUseCase
import com.gy.rickandmorty.model.CharactersResponse
import com.gy.rickandmorty.model.network.ApiHelper
import com.gy.rickandmorty.model.network.CharacterPagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject internal constructor(private val useCase: CharactersUseCase, private val apiHelper: ApiHelper) : ViewModel() {

    private val _res = MutableLiveData<Resource<CharactersResponse>>()
    val res : LiveData<Resource<CharactersResponse>> = _res

    init {
//        load()
    }

    val charactersFlow = Pager(
        // Configure how data is loaded by passing additional properties to
        // PagingConfig, such as prefetchDistance.
        PagingConfig(pageSize = 2)
    ) {
        CharacterPagingSource(apiHelper)
    }.flow.cachedIn(viewModelScope)

//    fun load() {
//        _res.postValue(Resource.Loading())
//        viewModelScope.launch {
//            val charactersRes = useCase.getCharacters()
//            if (!charactersRes.isSuccessful) {
//                _res.postValue(Resource.NetworkError(NetworkErrorException(charactersRes.errorBody().toString())))
//            }
//            else {
//                _res.postValue(Resource.Success(charactersRes.body()))
//            }
//        }
//    }
}