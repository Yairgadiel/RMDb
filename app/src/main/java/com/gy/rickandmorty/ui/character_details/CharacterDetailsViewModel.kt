package com.gy.rickandmorty.ui.character_details

import android.accounts.NetworkErrorException
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gy.rickandmorty.Resource
import com.gy.rickandmorty.domain.usecases.GetCharacterByIdUseCase
import com.gy.rickandmorty.model.entities.ShowCharacter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "CharacterDetailsViewModel"

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class CharacterDetailsViewModel @Inject constructor(
    private val getCharacterById: GetCharacterByIdUseCase
    ) : ViewModel() {

    private val _id = MutableStateFlow(-1)

    val fetchedCharacterRes: StateFlow<Resource<ShowCharacter>> = _id.flatMapLatest { id ->
        if (id == -1) {
            flowOf(Resource.Loading())
        } else {
            flow {
                emit(Resource.Loading())
                try {
                    val response = getCharacterById(id)
                    if (response.isSuccessful) {
                        Log.i(TAG, "Fetch Success")
                        emit(Resource.Success(response.body()))
                    } else {
                        Log.i(TAG, "Fetch Error")
                        emit(
                            Resource.NetworkError(
                                NetworkErrorException(
                                    response.errorBody()?.string() ?: "Unknown Error"
                                )
                            )
                        )
                    }
                } catch (e: Exception) {
                    emit(Resource.NetworkError(e))
                }
            }
        }
    }.distinctUntilChanged()
        .stateIn(
            scope = viewModelScope,
            initialValue = Resource.Loading(),
            started = SharingStarted.Eagerly
        )

    fun fetchCharacterById(id: Int) {
        viewModelScope.launch(Dispatchers.Default) {
            _id.emit(id)
        }
    }
}