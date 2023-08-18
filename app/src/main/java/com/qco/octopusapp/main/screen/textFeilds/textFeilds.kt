package com.qco.octopusapp.main.screen.textFeilds

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.qco.octopusapp.viewModel.OctopusAction
import com.qco.octopusapp.viewModel.OctopusState

@Composable
fun TextFields(
    state: OctopusState,
    onAction:(OctopusAction)->Unit,
    modifier: Modifier
) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .then(modifier),
    horizontalAlignment = Alignment.CenterHorizontally
    , verticalArrangement = Arrangement.SpaceAround){
        when(state.cipher)
        {
            "Mono" -> {
                MonoText(state = state, onAction = onAction, label = "Enter your Text")
            }
            "Caesar" -> {
                CaesarTexts(state = state, onAction = onAction)
            }
            "B2T" -> {
                MonoText(state = state, onAction = onAction, label = "Enter Binary here")
            }
            "T2B" -> {
                MonoText(state = state, onAction = onAction, label = "Enter your Text")
            }
            else ->{}
        }
    }
}