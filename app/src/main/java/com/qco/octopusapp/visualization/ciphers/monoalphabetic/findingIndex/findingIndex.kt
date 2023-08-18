@file:OptIn(ExperimentalAnimationApi::class)
@file:Suppress("OPT_IN_IS_NOT_ENABLED")

package com.qco.octopusapp.visualization.ciphers.monoalphabetic.findingIndex

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun FindingIndexProcess(targetChar:String,
                        alphabets: List<Char>,
                        indexCounter:Int,
                        onDialogClicked :(step:String) -> Unit) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(4.dp),
        horizontalAlignment = Alignment.Start) {
        Text(
            text = "Finding Index",
            fontSize = 12.sp,
            color = MaterialTheme.colors.onBackground,
            modifier = Modifier.padding(bottom = 4.dp, start = 8.dp)
        )
        Row(modifier = Modifier
            .fillMaxWidth()
            .shadow(elevation = 15.dp, shape = RoundedCornerShape(15.dp))
            .clip(shape = RoundedCornerShape(15.dp))
            .background(MaterialTheme.colors.secondary)
            .clickable {
                onDialogClicked("Finding Index")
            }
            .padding(vertical = 16.dp, horizontal = 4.dp)
            , horizontalArrangement = Arrangement.SpaceBetween
            , verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .clip(RoundedCornerShape(15.dp))
                    .background(MaterialTheme.colors.onBackground)
                , contentAlignment = Alignment.Center){
                Text(text = if(targetChar == " ") "~" else targetChar,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.background)
            }
//            AnimatedContent(
//                targetState = alphabets[indexCounter],
//                transitionSpec = { slideInVertically { it } with slideOutVertically { -it }}
//            ) {animatedChar ->
//                Text(
//                    text = if(animatedChar.toString() == " ") "~" else animatedChar.toString(),
//                    style = MaterialTheme.typography.h6,
//                    softWrap = false,
//                    color = MaterialTheme.colors.onBackground
//                )
//            }
            Text(
                text = if(alphabets[indexCounter].toString() == " ") "~" else alphabets[indexCounter].toString(),
                style = MaterialTheme.typography.h6,
                softWrap = false,
                color = MaterialTheme.colors.onBackground
            )
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .clip(RoundedCornerShape(15.dp))
                    .background(MaterialTheme.colors.onBackground)
                , contentAlignment = Alignment.Center){
                Text(text = "$indexCounter",
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.background)
            }
        }
    }
}