package com.qco.octopusapp.main.screen.textFeilds

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.qco.octopusapp.viewModel.OctopusAction
import com.qco.octopusapp.viewModel.OctopusState

@Composable
fun CaesarTexts(
    state: OctopusState,
    onAction:(OctopusAction)->Unit) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 2.dp)
        , horizontalArrangement = Arrangement.SpaceBetween
        , verticalAlignment = Alignment.CenterVertically){
        Row(modifier = Modifier
            .fillMaxWidth()
            .weight(3.5f)
            .padding(horizontal = 2.dp)) {
            OutlinedTextField(value = state.text, onValueChange = {newValue ->
                onAction(OctopusAction.TextTyping(newValue))
            }
                , singleLine = true
                , label = { Text(text = "Enter your Text", color = MaterialTheme.colors.onBackground) }
                , shape = RoundedCornerShape(5.dp)
                , colors = TextFieldDefaults.outlinedTextFieldColors(textColor = MaterialTheme.colors.onBackground, placeholderColor = MaterialTheme.colors.onBackground, unfocusedBorderColor = MaterialTheme.colors.primary))
        }
        Row(modifier = Modifier
            .fillMaxWidth()
            .weight(.8f)
            .padding(horizontal = 2.dp)) {
            OutlinedTextField(value = state.key, onValueChange = {key->
                onAction(OctopusAction.KeyTyping(key))
            }
                , singleLine = true
                , label = { Text(text = "key", fontSize = 10.sp, color = MaterialTheme.colors.onBackground) }
                , keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                , shape = RoundedCornerShape(5.dp)
                , colors = TextFieldDefaults.outlinedTextFieldColors(textColor = MaterialTheme.colors.onBackground, placeholderColor = MaterialTheme.colors.onBackground, unfocusedBorderColor = MaterialTheme.colors.primary))
        }

    }
}