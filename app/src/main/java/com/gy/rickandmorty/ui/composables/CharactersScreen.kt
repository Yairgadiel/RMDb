package com.gy.rickandmorty.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemsIndexed
import com.gy.rickandmorty.R
import com.gy.rickandmorty.model.entities.ShowCharacter
import com.gy.rickandmorty.ui.theme.RickAndMortyTheme

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

    val pullState = rememberPullRefreshState(isRefreshing, ::refresh)

    Box(Modifier.pullRefresh(pullState)) { var characterDisplayMode by remember {
            mutableStateOf(DisplayMode.GRID)
        }

        PullRefreshIndicator(
            refreshing = isRefreshing,
            state = pullState,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .zIndex(2f)
        )

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            ActionsBar { mode ->
                characterDisplayMode = mode
            }

            when (characterDisplayMode) {
                DisplayMode.LIST -> {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize(),
                    ) {
                        itemsIndexed(lazyPagingItems) { _, item ->
                            if (item != null) {
                                ListCharacter(item)
                            }
                        }

                        if (lazyPagingItems.loadState.append == LoadState.Loading) {
                            item {
                                ProgressIndicator()
                            }
                        }
                    }
                }
                DisplayMode.GRID -> {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(characterDisplayMode.size),
                        contentPadding = PaddingValues(8.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier
                            .fillMaxSize()
                            .align(Alignment.CenterHorizontally),
                    ) {
                        items(lazyPagingItems.itemCount) {
                            val character = lazyPagingItems[it]
                            if (character != null) {
                                GridCharacter(character)
                            }
                        }

                        if (lazyPagingItems.loadState.append == LoadState.Loading) {
                            item(span = {
                                GridItemSpan(characterDisplayMode.size)
                            }) {
                                ProgressIndicator()
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun ProgressIndicator() {
    CircularProgressIndicator(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentWidth(Alignment.CenterHorizontally)
    )
}

@Composable
fun ActionsBar(onDisplayModeSelected: (DisplayMode) -> Unit) {
    val iconModifier = Modifier
        .size(40.dp)
        .border(
            width = 2.dp,
            color = Color.LightGray,
            shape = RoundedCornerShape(percent = 20)
        )

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End,
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Image(
            painter = painterResource(id = R.drawable.baseline_view_list_24),
            contentDescription = "list",
            modifier = iconModifier.clickable { onDisplayModeSelected(DisplayMode.LIST) }
        )
        Spacer(modifier = Modifier.width(8.dp))
        Image(
            painter = painterResource(id = R.drawable.baseline_view_module_24),
            contentDescription = "grid",
            modifier = iconModifier.clickable { onDisplayModeSelected(DisplayMode.GRID) }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ActionsBarPrev() {
    RickAndMortyTheme {
        ActionsBar() {}
    }
}

@Preview
@Composable
fun CharactersPrev() {
//    val characters = listOf<ShowCharacter>(
//        ShowCharacter(
//            created = "yesterday",
//            episode = listOf(),
//            gender = "Male",
//            id = 1,
//            image = "https://rickandmortyapi.com/api/character/avatar/2.jpeg",
//            location = Location("Home", ""),
//            name = "Yair Gadiel",
//            origin = Origin("Beit Gamliel", ""),
//            species = "Human",
//            status = "Alive",
//            type = "Unknown",
//            url = ""
//        ),
//        ShowCharacter(
//            created = "yesterday",
//            episode = listOf(),
//            gender = "Male",
//            id = 1,
//            image = "https://rickandmortyapi.com/api/character/avatar/2.jpeg",
//            location = Location("Home", ""),
//            name = "Yair Gadiel",
//            origin = Origin("Beit Gamliel", ""),
//            species = "Human",
//            status = "Alive",
//            type = "Unknown",
//            url = ""
//        ),
//        ShowCharacter(
//            created = "yesterday",
//            episode = listOf(),
//            gender = "Male",
//            id = 1,
//            image = "https://rickandmortyapi.com/api/character/avatar/2.jpeg",
//            location = Location("Home", ""),
//            name = "Yair Gadiel",
//            origin = Origin("Beit Gamliel", ""),
//            species = "Human",
//            status = "Alive",
//            type = "Unknown",
//            url = ""
//        ),
//
//    )

//    CharactersScreen(characters, lazyPagingItems)
}