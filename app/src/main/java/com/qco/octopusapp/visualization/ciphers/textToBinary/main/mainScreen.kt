package com.qco.octopusapp.visualization.ciphers.textToBinary.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.qco.octopusapp.visualization.ciphers.basicComposables.round.RoundNumber
import com.qco.octopusapp.visualization.ciphers.basicComposables.skip.Skip
import com.qco.octopusapp.visualization.ciphers.basicComposables.text.TextRow
import com.qco.octopusapp.visualization.ciphers.textToBinary.toAscii.ToAscii
import com.qco.octopusapp.visualization.ciphers.textToBinary.toBinary.ToBinary
import com.qco.octopusapp.visualization.ciphers.textToBinary.toBinaryResult.ToBinaryResult
import kotlinx.coroutines.delay

@Composable
fun ToBinaryScreen(text: String,
                   onDialogClicked:(step:String)->Unit,
                   navController: NavController,
                   language:String
) {
    /**set of state variables**/
    val result = remember {
        mutableListOf<String>()
    }
    var round by remember {
        mutableStateOf(0)
    }

    var divisionResult by remember {
        mutableStateOf(0)
    }
    var divisionMod by remember {
        mutableStateOf(0)
    }
    var targetChar by remember {
        mutableStateOf(text[round])
    }
    var ascii by remember {
        mutableStateOf(targetChar.code)
    }
    var tempAscii by remember {
        mutableStateOf(ascii)
    }
    var showAscii by remember{
        mutableStateOf(false)
    }
    var toResultStep by remember{
        mutableStateOf(false)
    }
    var binaryList by remember {
        mutableStateOf("")
    }

    /**Side effect so we can recompose our view after specific period of time**/
    LaunchedEffect(key1 = true){

        //--------------------Loop repeated by the number of text length--------------------//
        while(round < text.length)
        {
            //--------------------to convert text to binary, you must separate between letters and spaces--------------------//
            //--------------------if it is a space, we replace it by '~' as a place holder, and then go to next steps--------------------//
            if(targetChar.isWhitespace())
            {
                delay(500L)
                result.add("~")
                delay(500L)
                toResultStep = true
                if(round < text.length - 1){
                    round += 1
                    targetChar = text[round]
                    binaryList = ""
                    showAscii = false
                    ascii = targetChar.code
                    tempAscii = ascii
                    toResultStep = false
                    delay(1000L)
                }
                else{
                    break
                }
            }
            //--------------------if it is not space then we need to convert the letter to ASCII and then to binary--------------------//
            else
            {
                delay(500L)
                showAscii = true
                delay(500L)
                while(tempAscii > 0){
                    delay(500L)
                    divisionResult = tempAscii / 2
                    delay(500L)
                    divisionMod = tempAscii % 2
                    delay(500L)
                    binaryList += divisionMod.toString()
                    delay(1000L)
                    tempAscii = divisionResult
                }
                binaryList+="0"
                result.add(binaryList)
                delay(500L)
                toResultStep = true
                if(round < text.length - 1){
                    round += 1
                    targetChar = text[round]
                    binaryList = ""
                    showAscii = false
                    ascii = targetChar.code
                    tempAscii = ascii
                    toResultStep = false
                    delay(1000L)
                }
                else{
                    break
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
            .padding(horizontal = 8.dp)) {

        RoundNumber(number = round + 1, onDialogClicked = onDialogClicked)

        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colors.background))
        {
            items(count = 1){
                TextRow(text = text, round = round, onDialogClicked = onDialogClicked)

                ToAscii(char = targetChar, ascii = ascii, showAscii = showAscii, onDialogClicked = onDialogClicked)

                ToBinary(
                    ascii = tempAscii,
                    binaryList = binaryList,
                    divisionResult = divisionResult,
                    divisionMod = divisionMod,
                    onDialogClicked = onDialogClicked
                )

                ToBinaryResult(
                    result = result,
                    text = text,
                    begin = toResultStep,
                    round = round + 1,
                    textLength = text.length,
                    onDialogClicked = onDialogClicked
                )
            }
        }
        Skip(navController = navController, language = language)
    }
}
