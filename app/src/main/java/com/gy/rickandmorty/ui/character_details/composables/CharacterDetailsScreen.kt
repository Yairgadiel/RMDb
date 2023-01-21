package com.gy.rickandmorty.ui.character_details.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.gy.rickandmorty.R
import com.gy.rickandmorty.Resource
import com.gy.rickandmorty.model.entities.Location
import com.gy.rickandmorty.model.entities.Origin
import com.gy.rickandmorty.model.entities.ShowCharacter
import com.gy.rickandmorty.ui.character_details.CharacterDetailsViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun CharacterDetailsScreen(viewModel: CharacterDetailsViewModel = hiltViewModel(), navigator: DestinationsNavigator, characterId: Int) {
    viewModel.fetchCharacterById(characterId)

    val state = viewModel.fetchedCharacterRes.collectAsState()
    CharacterDetailsScreen(state.value)

}
@Composable
private fun CharacterDetailsScreen(characterResource: Resource<ShowCharacter>) {
    Box(modifier = Modifier
        .fillMaxSize()) {
        when (characterResource) {
            is Resource.Loading -> CircularProgressIndicator()
            is Resource.NetworkError -> ErrorScreen(characterResource.data?.message)
            is Resource.Success -> {
                if (characterResource.data != null) {
                    CharacterDetails(character = characterResource.data)
                } else {
                    ErrorScreen(error = "No Character")
                }
            }
        }
    }
}

@Composable
private fun CharacterDetails(character: ShowCharacter) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
    ) {

        Column(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .zIndex(2f)) {

            Spacer(modifier = Modifier.height(100.dp))

            Column(
                Modifier
                    .padding(start = 16.dp, end = 16.dp)
                    .fillMaxWidth()
                    .wrapContentHeight()
            ) {
                AsyncImage(
                    model = character.image,
                    contentDescription = "character_img",
                    modifier = Modifier
                        .size(100.dp)
                        .border(4.dp, Color.White, CircleShape)
                        .clip(CircleShape),
                    placeholder = painterResource(id = R.drawable.morty_ph),
                    error = painterResource(id = R.drawable.morty_ph),
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = character.name,
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,

                        ),
                    fontSize = 20.sp
                )

                Text(
                    text = if (character.origin.name == "unknown") "Origin Unknown" else character.origin.name,
                    fontSize = 16.sp
                )

                Spacer(modifier = Modifier.height(8.dp))

                val details = generateDetailsString(
                    character.type,
                    character.species,
                    character.status,
                    generatePronouns(character.gender)
                )

                Text(
                    text = details,
                    fontSize = 14.sp
                )

                Text(
                    text = character.location.name,
                    fontSize = 14.sp,
                    style = TextStyle(
                        color = Color.Gray
                    )
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "${character.episode.size} ${if (character.episode.size == 1) "episode" else "episodes"}",
                    fontSize = 14.sp,
                    style = TextStyle(
                        color = MaterialTheme.colors.primary,
                        fontWeight = FontWeight.SemiBold
                    )
                )
            }

            Spacer(modifier = Modifier.height(16.dp).background(MaterialTheme.colors.secondaryVariant))
        }

        Image(
            painter = painterResource(id = R.drawable.wallpaper),
            contentDescription = "wallpaper",
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .clipToBounds()
                .fillMaxWidth()
                .height(150.dp)
                .align(Alignment.TopCenter),
        )
    }
}

private fun generateDetailsString(vararg details: String?) : String {
    val detailsBuilder = StringBuilder()

    val nonEmpties = details.filter { !it.isNullOrEmpty() }

    nonEmpties.forEachIndexed { index, s ->
        detailsBuilder.append(s)

        if (index != nonEmpties.size - 1) {
            detailsBuilder.append(" ${Typography.bullet} ")
        }
    }

    return detailsBuilder.toString()
}

private fun generatePronouns(gender: String) : String {
    return when (gender) {
        "Male" -> {
            "He/Him"
        }
        "Female" -> {
            "She/Her"
        }
        else -> {
            "They/Them"
        }
    }
}

@Composable
fun ErrorScreen(error: String? = null) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = error ?: "Unknown Error")
    }
}

@Preview(showBackground = true)
@Composable
fun CharacterDetailsPreview() {
    CharacterDetails(character = previewCharacter)
}

@Preview
@Composable
fun ErrorScreenPreview() {
    ErrorScreen()
}

private val previewCharacter = ShowCharacter(
    created = "yesterday",
    episode = listOf(),
    gender = "Male",
    id = 1,
    image = "https://rickandmortyapi.com/api/character/avatar/2.jpeg",
    location = Location("Home", ""),
    name = "Yair Gadiel aa sadsa fs afas fsa f",
    origin = Origin("Beit Gamliel or Rehovot or Yavne", ""),
    species = "Human",
    status = "Alive",
    type = "Unknown",
    url = ""
)

