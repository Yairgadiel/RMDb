package com.gy.rickandmorty.ui.characters.composables.display_mode_selector

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gy.rickandmorty.R


@Composable
fun DisplayModeSelector(displayModes: List<DisplayMode>, onDisplayModeSelected: (DisplayMode) -> Unit) {
    require(displayModes.isNotEmpty())

    var selectedMode by rememberSaveable {
        mutableStateOf(displayModes.first())
    }

    DisplayModeButton(currState = selectedMode) {
        selectedMode = displayModes[(displayModes.indexOf(selectedMode) + 1) % displayModes.size]
        onDisplayModeSelected(selectedMode)
    }
}

@Composable
private fun DisplayModeButton(modifier: Modifier = Modifier, currState: DisplayMode, onClick: () -> Unit) {
    val imageRes = when (currState) {
        DisplayMode.LIST -> R.drawable.baseline_view_list_24
        DisplayMode.GRID -> R.drawable.baseline_view_module_24
    }

    Image(
        painter = painterResource(id = imageRes),
        contentDescription = "DisplayModeButton",
        modifier = modifier
            .size(40.dp)
            .border(
                width = 1.dp,
                color = Color.LightGray,
                shape = RoundedCornerShape(percent = 20)
            )
            .clip(
                RoundedCornerShape(percent = 20))
            .clickable {
                onClick()
            }
    )
}

@Preview(showBackground = true)
@Composable
fun ActionsBarPrev() {
    DisplayModeSelector(displayModes = listOf(DisplayMode.LIST, DisplayMode.GRID)) {}
}

@Preview(showBackground = true)
@Composable
fun DisplayModeButtonPrev() {
    DisplayModeButton(currState = DisplayMode.LIST) {}
}