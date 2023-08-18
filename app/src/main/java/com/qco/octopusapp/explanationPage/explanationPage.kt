package com.qco.octopusapp.explanationPage

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.qco.octopusapp.R

@Composable
fun ExplanationPage(cipher: String,
                    language: String,
                    alphabet: List<Char>,
                    unsortedAlphabet: List<Char>,
                    crypto: String,
                    navController: NavController) {

    navController.enableOnBackPressed(enabled = false)

    val title = when(cipher){
        "Caesar"->{
            stringResource(id = R.string.caesar)
        }
        "Mono"->{
            stringResource(id = R.string.mono)
        }
        "B2T"->{
            stringResource(id = R.string.b2t)
        }
        "T2B"->{
            stringResource(id = R.string.t2b)
        }
        else ->{
            ""
        }
    }
    var direction by remember {
        mutableStateOf(LayoutDirection.Ltr)
    }
    direction = if(language == "Language"){
        LayoutDirection.Ltr
    }
    else {
        LayoutDirection.Rtl
    }
    CompositionLocalProvider(LocalLayoutDirection provides (direction)) {
        Scaffold(modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background),
        bottomBar = {

            Row(verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colors.background)
                    .padding(horizontal = 16.dp)) {
                Box(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = stringResource(id = R.string.skip),
                        fontSize = 12.sp,
                        color = MaterialTheme.colors.onBackground
                        , modifier = Modifier
                            .align(alignment = Alignment.CenterEnd)
                            .clip(CircleShape)
                            .padding(8.dp)
                            .clickable { navController.navigate("visualisation_screen") })
                    Text(
                        text = stringResource(id = R.string.back),
                        fontSize = 12.sp,
                        color = MaterialTheme.colors.onBackground
                        , modifier = Modifier
                            .align(alignment = Alignment.CenterStart)
                            .clip(CircleShape)
                            .padding(8.dp)
                            .clickable { navController.navigate("main_screen") })
                }
            }
        }
        ) {
            Column(
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Top,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 32.dp, start = 12.dp, end = 12.dp, top = 0.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colors.background)
                        .padding(top = 16.dp, bottom = 16.dp, start = 4.dp, end = 4.dp)
                ) {
                    Box(modifier = Modifier.fillMaxWidth()) {
                        Text(
                            text = title,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colors.onBackground,
                            modifier = Modifier.align(Alignment.CenterStart))
                    }
                }
                LazyColumn(horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Top,
                    modifier = Modifier.fillMaxSize()){
                    items(count = 1){
                        when(cipher){
                            "Caesar"->{
                                Text(text = if(crypto == "Encryption") stringResource(id = R.string.caesar_exp) else stringResource(id = R.string.caesar_exp_dec), color = MaterialTheme.colors.onBackground)
                            }
                            "Mono"->{
                                Text(text = if(crypto == "Encryption") stringResource(id = R.string.mono_exp) else stringResource(id = R.string.mono_exp_dec), color = MaterialTheme.colors.onBackground)
                            }
                            "T2B"->{
                                Text(text = stringResource(id = R.string.t2b_exp), color = MaterialTheme.colors.onBackground)
                            }
                            "B2T"->{
                                Text(text =  stringResource(id = R.string.b2t_exp), color = MaterialTheme.colors.onBackground)
                            }
                        }
                        CompositionLocalProvider(LocalLayoutDirection provides (direction)) {
                            Divider(modifier = Modifier.padding(bottom = 4.dp))
                            Column(modifier = Modifier
                                .fillMaxWidth()
                                .background(MaterialTheme.colors.background)) {
                                Row(modifier = Modifier
                                    .fillMaxWidth(),
                                    horizontalArrangement = Arrangement.Start,
                                    verticalAlignment = Alignment.CenterVertically) {
                                    Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                                        Text(text = stringResource(id = R.string.alphabet),
                                            fontSize = 12.sp,
                                            fontWeight = FontWeight.Bold,
                                            modifier = Modifier.padding(start = 8.dp),
                                            color = MaterialTheme.colors.onBackground)
                                        Text(text = if(cipher == "Caesar" || cipher == "Mono") stringResource(id = R.string.index) else stringResource(id = R.string.ascii),
                                            fontSize = 8.sp,
                                            fontWeight = FontWeight.Bold,
                                            modifier = Modifier.padding(start = 8.dp),
                                            color = MaterialTheme.colors.onBackground)
                                    }
                                    LazyRow(modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(bottom = 4.dp, start = 8.dp, end = 8.dp)){
                                        items(count = 1){
                                            Text(text = "[", color = MaterialTheme.colors.onBackground)
                                            alphabet.forEachIndexed { index, c ->
                                                Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                                                    Text(text = if(c.isWhitespace()) " ~ " else " $c ", color = MaterialTheme.colors.onBackground)
                                                    Text(text = if(cipher == "Caesar" || cipher == "Mono") "$index" else if(c.isWhitespace()) "" else  "${c.code}", fontSize = 6.sp, color = MaterialTheme.colors.onBackground)
                                                }
                                            }
                                            Text(text = "]", color = MaterialTheme.colors.onBackground)
                                        }
                                    }
                                }
                                if(cipher == "Mono"){
                                    Row(modifier = Modifier
                                        .fillMaxWidth(),
                                        horizontalArrangement = Arrangement.Start,
                                        verticalAlignment = Alignment.CenterVertically) {
                                        Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                                            Text(text = stringResource(id = R.string.unsorted_alphabet),
                                                fontSize = 12.sp,
                                                fontWeight = FontWeight.Bold,
                                                modifier = Modifier.padding(start = 8.dp),
                                                color = MaterialTheme.colors.onBackground)
                                            Text(text = stringResource(id = R.string.index),
                                                fontSize = 8.sp,
                                                fontWeight = FontWeight.Bold,
                                                modifier = Modifier.padding(start = 8.dp),
                                                color = MaterialTheme.colors.onBackground)
                                        }
                                        LazyRow(modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(bottom = 4.dp, start = 8.dp, end = 8.dp)){
                                            items(count = 1){
                                                Text(text = "[", color = MaterialTheme.colors.onBackground)
                                                unsortedAlphabet.forEachIndexed { index, c ->
                                                    Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                                                        Text(text = if(c.isWhitespace()) " ~ " else " $c ", color = MaterialTheme.colors.onBackground)
                                                        Text(text =  "$index", fontSize = 6.sp, color = MaterialTheme.colors.onBackground)
                                                    }
                                                }
                                                Text(text = "]", color = MaterialTheme.colors.onBackground)
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.padding(vertical = 48.dp))
            }
        }
    }
}