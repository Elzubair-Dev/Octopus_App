package com.qco.octopusapp.main.screen.toolSet


import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.qco.octopusapp.viewModel.OctopusAction
import com.qco.octopusapp.R
import com.qco.octopusapp.viewModel.OctopusState

@Composable
fun ToolsSet(
    context:Context,
    state: OctopusState,
    onAction:(OctopusAction) -> Unit,
    navController: NavController
) {
    Row(modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically) {
        CipherButton(
            text = state.cipher,
            image = painterResource(id = R.drawable.ic_baseline_arrow_drop_down_24),
            content = "Cipher",
            color = MaterialTheme.colors.onBackground,
            onClick = {onAction(OctopusAction.CipherType)}
        )
        ToolSetButton(text = "Swap",
            image = painterResource(id = R.drawable.ic_baseline_swap_vert_24),
            content = "Swapping result text with the entered text",
            color = MaterialTheme.colors.onBackground,
            onClick = {onAction(OctopusAction.Swap(context = context))}
        )
        ToolSetButton(text = state.clearName,
            image = painterResource(id = R.drawable.ic_baseline_delete_24),
            content = "Clearing and resetting the app",
            color = MaterialTheme.colors.onBackground,
            onClick = {onAction(OctopusAction.Clear)}
        )
        ToolSetButton(text = "Show Process",
            image = painterResource(id = R.drawable.ic_baseline_remove_red_eye_24),
            content = "Showing the process of Cryptography",
            color = if(!state.visualizable) MaterialTheme.colors.primaryVariant
            else MaterialTheme.colors.onBackground,
            onClick = {
                if(state.visualizable) {
                    when(state.cipher){
                        "Mono" -> {
                            if(state.text.isNotBlank() && state.result.isNotBlank() && state.cryptographyProcess.isNotBlank()){
                                navController.navigate("explanation_screen")
                            }
                            else {
                                onAction(OctopusAction.ShowProcess(context))
                            }
                        }
                        "Caesar" -> {
                            if(state.text.isNotBlank() && state.result.isNotBlank() && state.key.isNotBlank() && state.cryptographyProcess.isNotBlank()){
                                navController.navigate("explanation_screen")
                            }
                            else {
                                onAction(OctopusAction.ShowProcess(context))
                            }
                        }
                        "B2T" -> {
                            if(state.text.isNotBlank() && state.result.isNotBlank() && state.cryptographyProcess.isNotBlank()){
                                navController.navigate("explanation_screen")
                            }
                            else {
                                onAction(OctopusAction.ShowProcess(context))
                            }
                        }
                        "T2B" -> {
                            if(state.text.isNotBlank() && state.result.isNotBlank() && state.cryptographyProcess.isNotBlank()){
                                navController.navigate("explanation_screen")
                            }
                            else {
                                onAction(OctopusAction.ShowProcess(context))
                            }
                        }
                        else -> {onAction(OctopusAction.ShowProcess(context))}
                    }
                }
                else{
                    onAction(OctopusAction.ShowProcess(context))
                }
            }
        )
    }
    DropdownMenu(expanded = state.expended, onDismissRequest = { onAction(OctopusAction.CipherType) }, modifier = Modifier.background(MaterialTheme.colors.background)) {
        DropdownMenuItem(onClick = {
            if(state.cipher != "Caesar"){
                state.cipher = "Caesar"
               /* state.text = ""
                state.key = ""
                state.prime1 = ""
                state.prime2 = ""
                state.result = ""*/
                onAction(OctopusAction.CipherType)
            }
            else onAction(OctopusAction.CipherType)
        }) {
            Text(text = "Caesar", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colors.onBackground)
        }
        Divider(color = MaterialTheme.colors.primary)
        DropdownMenuItem(onClick = {
            if(state.cipher != "Mono"){
                state.cipher = "Mono"
                /*state.text = ""
                state.key = ""
                state.prime1 = ""
                state.prime2 = ""
                state.result = ""*/
                onAction(OctopusAction.CipherType)
            }
            else onAction(OctopusAction.CipherType)
        }) {
            Text(text = "Monoalphabetic", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colors.onBackground)
        }
        Divider(color = MaterialTheme.colors.primary)
        DropdownMenuItem(onClick = {
            if(state.cipher != "T2B"){
                state.cipher = "T2B"
                /*state.text = ""
                state.key = ""
                state.prime1 = ""
                state.prime2 = ""
                state.result = ""*/
                onAction(OctopusAction.CipherType)
            }
            else onAction(OctopusAction.CipherType)
        }) {
            Text(text = "Text -> Binary", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colors.onBackground)
        }
        Divider(color = MaterialTheme.colors.primary)
        DropdownMenuItem(onClick = {
            if(state.cipher != "B2T"){
                state.cipher = "B2T"
                /*state.text = ""
                state.key = ""
                state.prime1 = ""
                state.prime2 = ""
                state.result = ""*/
                onAction(OctopusAction.CipherType)
            }
            else onAction(OctopusAction.CipherType)
        }) {
            Text(text = "Binary -> Text", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colors.onBackground)
        }
    }
}