package com.gy.rickandmorty.ui.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemsIndexed
import com.gy.rickandmorty.model.entities.Location
import com.gy.rickandmorty.model.entities.Origin
import com.gy.rickandmorty.model.entities.ShowCharacter

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CharactersScreen(
    lazyPagingItems: LazyPagingItems<ShowCharacter>
) {
    val isRefreshing by remember {
        derivedStateOf { lazyPagingItems.loadState.refresh == LoadState.Loading }
    }

    fun refresh() {
        lazyPagingItems.refresh()
    }

    val state = rememberPullRefreshState(isRefreshing, ::refresh)

    Box(Modifier.pullRefresh(state)) {
        LazyColumn(Modifier.fillMaxSize()) {
//            if (isRefreshing || lazyPagingItems.loadState.refresh == LoadState.Loading) {
//                item {
//                    Text(
//                        text = "Waiting for items to load from the backend",
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .wrapContentWidth(Alignment.CenterHorizontally)
//                    )
//                }
//            }

            itemsIndexed(lazyPagingItems) {_, item ->
                Character(item)
            }

            if (lazyPagingItems.loadState.append == LoadState.Loading) {
                item {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentWidth(Alignment.CenterHorizontally)
                    )
                }
            }
        }

        PullRefreshIndicator(isRefreshing, state, Modifier.align(Alignment.TopCenter))
    }
}

@Preview
@Composable
fun CharactersPrev() {
    val characters = listOf<ShowCharacter>(
        ShowCharacter(
            created = "yesterday",
            episode = listOf(),
            gender = "Male",
            id = 1,
            image = "https://rickandmortyapi.com/api/character/avatar/2.jpeg",
            location = Location("Home", ""),
            name = "Yair Gadiel",
            origin = Origin("Beit Gamliel", ""),
            species = "Human",
            status = "Alive",
            type = "Unknown",
            url = ""
        ),
        ShowCharacter(
            created = "yesterday",
            episode = listOf(),
            gender = "Male",
            id = 1,
            image = "https://rickandmortyapi.com/api/character/avatar/2.jpeg",
            location = Location("Home", ""),
            name = "Yair Gadiel",
            origin = Origin("Beit Gamliel", ""),
            species = "Human",
            status = "Alive",
            type = "Unknown",
            url = ""
        ),
        ShowCharacter(
            created = "yesterday",
            episode = listOf(),
            gender = "Male",
            id = 1,
            image = "https://rickandmortyapi.com/api/character/avatar/2.jpeg",
            location = Location("Home", ""),
            name = "Yair Gadiel",
            origin = Origin("Beit Gamliel", ""),
            species = "Human",
            status = "Alive",
            type = "Unknown",
            url = ""
        ),

    )

//    CharactersScreen(characters, lazyPagingItems)
}