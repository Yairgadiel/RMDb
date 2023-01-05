package com.gy.rickandmorty.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.gy.rickandmorty.R
import com.gy.rickandmorty.model.entities.Location
import com.gy.rickandmorty.model.entities.Origin
import com.gy.rickandmorty.model.entities.ShowCharacter

@Composable
fun Character(character: ShowCharacter? = null) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .wrapContentHeight()
            .padding(16.dp)
            .fillMaxWidth()
            .background(color = colorResource(id = R.color.white))) {
        AsyncImage(
            model = character?.image,
            contentDescription = character?.name,
            modifier = Modifier
                .size(64.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop,
            placeholder = painterResource(id = R.drawable.morty_ph)
        )

        Spacer(modifier = Modifier.width(8.dp))

        Column(verticalArrangement = Arrangement.Center) {
            Text(text = character?.name ?: "Name",
            style = TextStyle(fontSize = 20.sp)
            )
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