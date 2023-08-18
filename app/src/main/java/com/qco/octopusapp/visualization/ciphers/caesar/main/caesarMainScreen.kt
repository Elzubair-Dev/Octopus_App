package com.qco.octopusapp.visualization.ciphers.caesar.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.qco.octopusapp.visualization.ciphers.basicComposables.result.Result
import com.qco.octopusapp.visualization.ciphers.basicComposables.round.RoundNumber
import com.qco.octopusapp.visualization.ciphers.basicComposables.skip.Skip
import com.qco.octopusapp.visualization.ciphers.basicComposables.text.TextRow
import com.qco.octopusapp.visualization.ciphers.caesar.findingIndex.FindingIndex
import com.qco.octopusapp.visualization.ciphers.caesar.nEquation.NEquation
import com.qco.octopusapp.visualization.ciphers.caesar.replacingIndex.ReplacingIndex
import kotlinx.coroutines.delay

@Composable
fun CaesarMainScreen(text: String,
                     result: String,
                     cryptographyType:String,
                     alphabets: List<Char>,
                     key:Int,
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
    var toReplacingIndexStep by remember {
        mutableStateOf(false)
    }
    var toResultStep by remember {
        mutableStateOf(false)
    }
    var toEquationStep by remember {
        mutableStateOf(false)
    }
    var showN by remember {
        mutableStateOf(false)
    }
    var round by remember {
        mutableStateOf(0)
    }
    var targetChar by remember {
        mutableStateOf(text[round])
    }
    var n by remember {
        mutableStateOf(0)
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
                        n = (targetIndex + key) % alphabets.size
                        delay(500L)
                        toEquationStep = true
                        delay(500L)
                        showN = true
                        delay(500L)
                        toReplacingIndexStep = true
                        delay(500L)
                        if(indexCounter2 != n ) {
                            indexCounter2++
                        }
                        else{
                            delay(200L)
                            toResultStep = true

                            if(round < text.length - 1){
                                round += 1
                                targetChar = text[round]
                                toReplacingIndexStep = false
                                toResultStep = false
                                showN = false
                                toEquationStep = false
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

            //--------------------in case that cryptography was decryption--------------------//
            else -> {
                while (round < text.length){
                    if(alphabets[indexCounter] != targetChar ) {
                        delay(500L)
                        indexCounter++
                    }
                    else
                    {
                        targetIndex = indexCounter
                        n = (targetIndex - key) % alphabets.size
                        if (n < 0) n += alphabets.size
                        delay(500L)
                        toEquationStep = true
                        delay(2000L)
                        showN = true
                        delay(500L)
                        toReplacingIndexStep = true
                        delay(500L)
                        if(indexCounter2 != n ) {
                            indexCounter2++
                        }
                        else{
                            delay(200L)
                            toResultStep = true

                            if(round < text.length - 1){
                                round += 1
                                targetChar = text[round]
                                toReplacingIndexStep = false
                                toResultStep = false
                                showN = false
                                toEquationStep = false
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
            .padding(8.dp)
    ) {
        RoundNumber(number = round + 1, onDialogClicked = onDialogClicked)

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

                FindingIndex(targetChar = targetChar.toString(),
                    alphabets = alphabets,
                    indexCounter = indexCounter,
                    onDialogClicked = onDialogClicked)

                NEquation(size = alphabets.size,
                    indexCounter = indexCounter,
                    key = key,
                    n = n,
                    cryptography = cryptographyType,
                    shouldStart = toEquationStep,
                    showN = showN,
                    onDialogClicked = onDialogClicked
                )

                ReplacingIndex(n = n,
                    alphabets = alphabets,
                    indexCounter2 = indexCounter2,
                    begin = toReplacingIndexStep,
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