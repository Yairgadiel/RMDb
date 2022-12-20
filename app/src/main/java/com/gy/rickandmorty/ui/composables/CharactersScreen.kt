package com.gy.rickandmorty.ui.composables

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.gy.rickandmorty.model.Location
import com.gy.rickandmorty.model.Origin
import com.gy.rickandmorty.model.ShowCharacter

@Composable
fun CharactersScreen(characters: List<ShowCharacter> = listOf()) {
    LazyColumn {
        items(characters) {
            Character(it)
        }
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
    CharactersScreen(characters)
}