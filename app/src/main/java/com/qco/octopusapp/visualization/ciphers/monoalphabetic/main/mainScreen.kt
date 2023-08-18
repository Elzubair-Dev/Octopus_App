package com.qco.octopusapp.visualization.ciphers.monoalphabetic.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.qco.octopusapp.visualization.ciphers.monoalphabetic.findingIndex.FindingIndexProcess
import com.qco.octopusapp.visualization.ciphers.monoalphabetic.replacingAlphabetList.ReplacingTheLetter
import com.qco.octopusapp.visualization.ciphers.basicComposables.result.Result
import com.qco.octopusapp.visualization.ciphers.basicComposables.round.RoundNumber
import com.qco.octopusapp.visualization.ciphers.basicComposables.skip.Skip
import com.qco.octopusapp.visualization.ciphers.basicComposables.text.TextRow
import kotlinx.coroutines.delay

@Composable
fun MonoMainScreen(text: String,
                   result: String,
                   cryptographyType:String,
                   alphabets: List<Char>,
                   unSortedAlphabets: List<Char>,
                   onDialogClicked : (step:String) -> Unit,
                   navController: NavController,
                   language:String
) {
    /**set of state variables**/
    var indexCounter by remember {
        mutableStateOf(0)
    }
    var indexCounter2 by remember {
        mutableStateOf(0)
    }
    var targetIndex by remember {
        mutableStateOf(0)
    }
    var toReplacingStep by remember {
        mutableStateOf(false)
    }
    var toResultStep by remember {
        mutableStateOf(false)
    }
    var round by remember {
        mutableStateOf(0)
    }
    var targetChar by remember {
        mutableStateOf(text[round])
    }

    /**Side effect so we can recompose our view after specific period of time**/
    LaunchedEffect(key1 = true){

        //--------------------checking for cryptography type, whether is encryption or decryption--------------------//
        when(cryptographyType){
            "Encryption" -> {

                //--------------------Loop repeated by the number of text length--------------------//
                while (round < text.length){
                    //--------------------we are comparing alphabet letters to the target letter--------------------//
                    //--------------------if alphabet letter is not equal to the target letter, go to next letter--------------------//
                    //--------------------else jump to next steps--------------------//
                    if(alphabets[indexCounter] != targetChar ) {
                        delay(500L)
                        indexCounter++
                    }
                    else
                    {
                        targetIndex = indexCounter
                        delay(500L)
                        toReplacingStep = true
                        delay(500L)
                        if(indexCounter2 != targetIndex ) {
                            delay(200L)
                            indexCounter2++
                        }
                        else{
                            delay(200L)
                            toResultStep = true
                            delay(1000L)
                            if(round < text.length - 1){
                                round += 1
                                targetChar = text[round]
                                toReplacingStep = false
                                toResultStep = false
                                indexCounter = 0
                                indexCounter2 = 0
                                delay(2000L)
                            }
                            else{
                                break
                            }
                        }
                    }
                }
            }

            /** Decryption **/
            else -> {
                while (round < text.length){
                    if(unSortedAlphabets[indexCounter] != targetChar ) {
                        delay(500L)
                        indexCounter++
                    }
                    else
                    {
                        targetIndex = indexCounter
                        delay(500L)
                        toReplacingStep = true
                        delay(500L)
                        if(indexCounter2 != targetIndex ) {
                            delay(200L)
                            indexCounter2++
                        }
                        else{
                            delay(200L)
                            toResultStep = true
                            delay(1000L)
                            if(round < text.length - 1){
                                round += 1
                                targetChar = text[round]
                                toReplacingStep = false
                                toResultStep = false
                                indexCounter = 0
                                indexCounter2 = 0
                                delay(2000L)
                            }
                            else{
                                break
                            }
                        }
                    }
                }
            }
        }
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
            .padding(8.dp)) {

        RoundNumber(number = round + 1, onDialogClicked = onDialogClicked)
        Spacer(modifier = Modifier.padding(bottom = 16.dp))
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colors.background))
        {
            items(count = 1){
                TextRow(text = text,
                    round = round,
                    onDialogClicked = onDialogClicked)

                FindingIndexProcess(
                    targetChar = targetChar.toString(),
                    alphabets = when(cryptographyType){
                        "Encryption" -> alphabets
                        else -> unSortedAlphabets },
                    indexCounter = indexCounter,
                    onDialogClicked = onDialogClicked)

                ReplacingTheLetter(
                    alphabetList = when(cryptographyType){
                        "Encryption" -> unSortedAlphabets
                        else -> alphabets },
                    begin = toReplacingStep,
                    targetIndex = targetIndex,
                    indexCounter2 = indexCounter2,
                    onDialogClicked = onDialogClicked)

                Result(
                    result = result,
                    begin = toResultStep,
                    round = round + 1,
                    onDialogClicked = onDialogClicked
                )
            }
        }
        Skip(navController = navController, language = language)
    }
}
