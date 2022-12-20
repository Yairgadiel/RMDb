package com.gy.rickandmorty.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gy.rickandmorty.Resource
import com.gy.rickandmorty.model.ShowCharacter
import com.gy.rickandmorty.ui.composables.CharactersScreen
import com.gy.rickandmorty.ui.theme.RickAndMortyTheme
import dagger.hilt.android.AndroidEntryPoint

const val TAG = "MainActivity"

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel : CharactersViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)




        setContent {
            RickAndMortyTheme {
                var str by remember {
                    mutableStateOf("")
                }

                var characters by remember {
                    mutableStateOf(listOf<ShowCharacter>())
                }

                viewModel.res.observe(this) {
                     when (it) {
                        is Resource.Loading -> {}
                        is Resource.NetworkError -> {
                            Log.e(TAG, "Error result", it.data)
                        }
                        is Resource.Success -> {
                            it.data?.results?.let { result ->
                                characters = result
                            } ?: {
                                // error
                            }
                        }
                    }
                }

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    CharactersScreen(characters)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    RickAndMortyTheme {

    }
}