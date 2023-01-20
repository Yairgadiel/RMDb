package com.gy.rickandmorty.ui.character_details.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
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

    println(1)
    val a = viewModel.fetchedCharacterRes.collectAsState()
    CharacterDetailsScreen(a.value)
    println(2)

}
@Composable
private fun CharacterDetailsScreen(characterResource: Resource<ShowCharacter>) {
    Box() {
        when (characterResource) {
            is Resource.Loading -> CircularProgressIndicator()
            is Resource.NetworkError -> ErrorScreen(characterResource.data?.message)
            is Resource.Success -> {
                if (characterResource.data != null) {
                    Character(character = characterResource.data)
                } else {
                    ErrorScreen(error = "No Character")
                }
            }
        }
    }
}

@Composable
private fun Character(character: ShowCharacter) {
    Text(text = character.name)
}

@Composable
fun ErrorScreen(error: String? = null) {
    Text(text = error ?: "Unknown Error")
}

@Preview
@Composable
fun CharacterPreview() {
    Character(character = previewCharacter)
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

