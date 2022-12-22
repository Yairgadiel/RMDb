package com.gy.rickandmorty.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.paging.compose.collectAsLazyPagingItems
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
            val lazyPagingItems = viewModel.flow.collectAsLazyPagingItems()

            RickAndMortyTheme {
                var characters by remember {
                    mutableStateOf(listOf<ShowCharacter>())
                }

//                viewModel.res.observe(this) {
//                     when (it) {
//                        is Resource.Loading -> {}
//                        is Resource.NetworkError -> {
//                            Log.e(TAG, "Error result", it.data)
//                        }
//                        is Resource.Success -> {
//                            it.data?.results?.let { result ->
//                                characters = result
//                            } ?: {
//                                // error
//                            }
//                        }
//                    }
//                }

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    CharactersScreen(lazyPagingItems)
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