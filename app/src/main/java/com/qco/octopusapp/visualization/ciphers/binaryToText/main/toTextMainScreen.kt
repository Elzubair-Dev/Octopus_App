package com.qco.octopusapp.visualization.ciphers.binaryToText.main


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.qco.octopusapp.R
import com.qco.octopusapp.visualization.ciphers.basicComposables.result.Result
import com.qco.octopusapp.visualization.ciphers.basicComposables.round.RoundNumber
import com.qco.octopusapp.visualization.ciphers.basicComposables.skip.Skip
import com.qco.octopusapp.visualization.ciphers.binaryToText.roundDigits.RoundDigits
import com.qco.octopusapp.visualization.ciphers.binaryToText.toAscii.Summation
import com.qco.octopusapp.visualization.ciphers.binaryToText.toChar.ToChar
import com.qco.octopusapp.visualization.ciphers.binaryToText.toTextTextRow.ToTextTextRow
import kotlinx.coroutines.delay
import kotlin.math.pow

@Composable
fun ToTextMainScreen(text:String,
                     result:String,
                     onDialogClicked:(step:String)->Unit,
                     navController: NavController,
                     language:String
) {
    /**set of state variables**/
    val decimalList = remember {
        mutableListOf<Int>()
    }
    val filteredDecimalList = remember {
        mutableListOf<Int>()
    }
    var round by remember {
        mutableStateOf(0)
    }
    var range by remember {
        mutableStateOf(0..0)
    }
    var spaces by remember {
        mutableStateOf(0)
    }
    var index by remember {
        mutableStateOf(0)
    }
    var decimal by remember {
        mutableStateOf(0)
    }
    var letterDigits by remember {
        mutableStateOf(text.subSequence(range = range))
    }
    var isSpace by remember {
        mutableStateOf(false)
    }
    var toAscii by remember {
        mutableStateOf(false)
    }
    var showAscii by remember {
        mutableStateOf(false)
    }
    var toChar by remember {
        mutableStateOf(false)
    }
    var showChar by remember {
        mutableStateOf(false)
    }
    var toResult by remember {
        mutableStateOf(false)
    }

    /**Side effect so we can recompose our view after specific period of time**/
    LaunchedEffect(key1 = true){

        //--------------------Loop repeated by the number of result length--------------------//
        //--------------------it loop by number of result's length because inserted text is binary numbers--------------------//
        //--------------------for example: text is "10000110 01000110", result will be "a b"--------------------//
        //--------------------result's length is 3, while text's length is 17--------------------//
        //--------------------so we will repeat the loop 3 times because every 8 digits on the text represent one letter only--------------------//
        while (round < result.length){

            //--------------------Checking indexes, when round is 0 => index = 0, when round is 1 => index = 8, and so on--------------------//
            //--------------------so when first element on the index is space, isSpace = true and the range will be one index--------------------//
            //--------------------if first element is binary digit, isSpace = false, range = from index to index + 7--------------------//
            if(text[round * 8].isWhitespace()){
                isSpace = true
                spaces +=1
                range =  (round * 8 .. round * 8)
                delay(1000L)
                toAscii = false
                toChar = false
                toResult = true
                delay(2000L)
                if(round < result.length - 1){
                    round+=1
                    decimalList.clear()
                    filteredDecimalList.clear()
                    decimal = 0
                    toAscii = false
                    showAscii = false
                    toChar = false
                    showChar = false
                    toResult = false
                }
                else{
                    break
                }
            }
            else {
                isSpace = false
                range = ((round * 8) - (7 * spaces) .. ((round * 8) + 7) - (7 * spaces))

                letterDigits = text.subSequence(range = range)

                letterDigits.forEachIndexed { thisIndex, c ->
                    index = thisIndex
                    decimal = (c.toString().toInt() * (2.0.pow(thisIndex))).toInt()
                    decimalList.add(index = thisIndex, element = decimal)
                    delay(1500L)
                }
                delay(1000L)
                toAscii = true
                decimalList.forEach { i ->
                    if(i != 0) filteredDecimalList.add(i)
                }

                delay(2000L)
                showAscii = true
                delay(2000L)
                toChar = true
                delay(2000L)
                showChar = true
                delay(2000L)
                toResult = true

                if(round < result.length - 1){
                    round+=1
                    decimalList.clear()
                    filteredDecimalList.clear()
                    decimal = 0
                    toAscii = false
                    showAscii = false
                    toChar = false
                    showChar = false
                    toResult = false
                    delay(2000L)
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
            .padding(horizontal = 8.dp)
    ){
        RoundNumber(number = round + 1,
            onDialogClicked = onDialogClicked)

        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colors.background)
                .padding(bottom = 16.dp))
        {
            items(count = 1){
                ToTextTextRow(text = text,
                    range = range, onDialogClicked = onDialogClicked)

                RoundDigits(
                    letterDigits = letterDigits.toString(),
                    round = round + 1,
                    isSpace = isSpace,
                    globalIndex = index,
                    decimalList = decimalList,
                    decimalValue = decimal,
                    onDialogClicked = onDialogClicked)

                Summation(
                    decimalList = filteredDecimalList,
                    ascii = if(result[round].code == '~'.code) 0 else result[round].code,
                    toAscii = toAscii,
                    isSpace = isSpace,
                    showAscii = showAscii,
                    onDialogClicked = onDialogClicked)

                ToChar(
                    ascii = result[round].code,
                    toChar = toChar,
                    isSpace = isSpace,
                    showChar = showChar,
                    onDialogClicked = onDialogClicked)

                Result(
                    result = result,
                    begin = toResult,
                    round = round + 1,
                    onDialogClicked = onDialogClicked
                )
            }
        }
        Row(verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colors.background)
                .padding(horizontal = 16.dp)) {
            Box(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = stringResource(id = R.string.back),
                    fontSize = 12.sp,
                    color = MaterialTheme.colors.onBackground
                    , modifier = Modifier
                        .align(alignment = Alignment.CenterStart)
                        .clip(CircleShape)
                        .clickable { navController.navigate("main_screen") })
            }
        }
        Skip(navController = navController, language = language)
    }
}