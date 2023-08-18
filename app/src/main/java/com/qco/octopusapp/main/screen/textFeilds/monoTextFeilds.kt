package com.qco.octopusapp.main.screen.textFeilds

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.qco.octopusapp.viewModel.OctopusAction
import com.qco.octopusapp.viewModel.OctopusState

@Composable
fun MonoText(
    state: OctopusState,
    label: String,
    onAction:(OctopusAction)->Unit
) {
    Row(modifier = Modifier
        .fillMaxWidth()
    ) {
        OutlinedTextField(value = state.text, onValueChange = { newValue ->
            onAction(OctopusAction.TextTyping(newValue))
        }
            , singleLine = true
            , label = { Text(text = label, color = MaterialTheme.colors.onBackground) }
            , shape = RoundedCornerShape(5.dp)
            , keyboardOptions = if (state.cipher == "B2T") KeyboardOptions(keyboardType = KeyboardType.Number) else KeyboardOptions.Default
            , modifier = Modifier.fillMaxWidth()
            , colors = TextFieldDefaults.outlinedTextFieldColors(textColor = MaterialTheme.colors.onBackground, placeholderColor = MaterialTheme.colors.onBackground, unfocusedBorderColor = MaterialTheme.colors.primary))
    }
}