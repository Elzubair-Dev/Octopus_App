package com.qco.octopusapp.visualization.mainScreen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import androidx.navigation.NavController
import com.qco.octopusapp.visualization.ciphers.binaryToText.main.ToTextMainScreen
import com.qco.octopusapp.visualization.ciphers.caesar.main.CaesarMainScreen
import com.qco.octopusapp.visualization.ciphers.monoalphabetic.main.MonoMainScreen
import com.qco.octopusapp.visualization.ciphers.textToBinary.main.ToBinaryScreen
import com.qco.octopusapp.visualization.dialogs.dialogsManager.DialogsManager

@Composable
fun VisualisationMainScreen(
    text:String,
    result:String,
    cipher:String,
    key:String,
    step: String,
    language: String,
    cryptographyType:String,
    alphabet: List<Char>,
    unSortedAlphabet: List<Char>,
    navController: NavController,
    openDialog:Boolean,
    onDismiss:()->Unit,
    onDialogClicked:(step:String) -> Unit
) {
    navController.enableOnBackPressed(enabled = false)
    when(cipher){
        "Mono" -> {
            MonoMainScreen(text = text,
                result = result,
                cryptographyType = cryptographyType,
                alphabets = alphabet,
                navController = navController,
                unSortedAlphabets = unSortedAlphabet,
                onDialogClicked = onDialogClicked,
            language = language)
        }
        "Caesar" -> {
            CaesarMainScreen(text = text,
                result = result,
                cryptographyType = cryptographyType,
                alphabets = alphabet,
                navController = navController,
                key = key.toInt(),
                onDialogClicked = onDialogClicked,
                language = language)}

        "T2B" -> {
            ToBinaryScreen(text = text,
                navController = navController,
                onDialogClicked = onDialogClicked,
                language = language)
        }

        "B2T" -> {
            ToTextMainScreen(text = text,
                result = result,
                navController = navController,
                onDialogClicked = onDialogClicked,
                language = language)}
    }
    if (openDialog) {
        CompositionLocalProvider(LocalLayoutDirection provides (LayoutDirection.Ltr)) {
            DialogsManager(
                cipher = cipher,
                cryptography = cryptographyType,
                step = step,
                alphabet = alphabet,
                unSortedAlphabet = unSortedAlphabet,
                onDismiss = onDismiss,
                language = language
            )
        }
    }
}