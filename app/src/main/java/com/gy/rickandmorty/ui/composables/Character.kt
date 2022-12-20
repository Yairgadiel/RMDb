package com.gy.rickandmorty.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.gy.rickandmorty.R
import com.gy.rickandmorty.model.Location
import com.gy.rickandmorty.model.Origin
import com.gy.rickandmorty.model.ShowCharacter

@Composable
fun Character(character: ShowCharacter? = null) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
        .height(100.dp)
        .fillMaxWidth()
        .background(color = colorResource(id = R.color.white))) {
        AsyncImage(model = character?.image, contentDescription = character?.name, modifier = Modifier.size(50.dp))

        Column(verticalArrangement = Arrangement.Center) {
            Text(text = character?.name ?: "Name")
            Text(text = character?.status ?: "Status")
        }
    }
}

@Preview
@Composable
fun CharacterPrev() {
    Character(
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
        )
    )
}