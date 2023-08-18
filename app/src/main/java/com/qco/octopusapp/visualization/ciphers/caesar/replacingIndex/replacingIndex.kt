//@file:OptIn(ExperimentalAnimationApi::class)
//@file:Suppress("OPT_IN_IS_NOT_ENABLED")

package com.qco.octopusapp.visualization.ciphers.caesar.replacingIndex

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ReplacingIndex(
    n : Int,
    alphabets:List<Char>,
    indexCounter2:Int,
    begin:Boolean,
    onDialogClicked:(step:String) -> Unit
) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(4.dp),
        horizontalAlignment = Alignment.Start) {
        Text(
            text = "Replacing",
            fontSize = 12.sp,
            color = MaterialTheme.colors.onBackground,
            modifier = Modifier.padding(bottom = 4.dp, start = 8.dp)
        )
        Row(modifier = Modifier
            .fillMaxWidth()
            .shadow(elevation = 15.dp, shape = RoundedCornerShape(15.dp))
            .clip(shape = RoundedCornerShape(15.dp))
            .background(MaterialTheme.colors.secondary)
            .clickable { onDialogClicked("Replacing") }
            .padding(vertical = 16.dp, horizontal = 4.dp)
            , horizontalArrangement = Arrangement.SpaceBetween
            , verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .clip(RoundedCornerShape(15.dp))
                    .background(MaterialTheme.colors.onBackground)
                , contentAlignment = Alignment.Center){
                Text(text = if(begin) n.toString() else "",
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.background)
            }
            if(begin){
//            AnimatedContent(
//                targetState = indexCounter2 ,
//                transitionSpec = { slideInVertically { it } with slideOutVertically { -it } }
//            ) { animatedChar ->
//                Text(
//                    text = animatedChar.toString(),
//                    style = MaterialTheme.typography.h6,
//                    softWrap = false,
//                    color = MaterialTheme.colors.onBackground
//                )
//            }
                Text(
                    text = "$indexCounter2",
                    style = MaterialTheme.typography.h6,
                    softWrap = false,
                    color = MaterialTheme.colors.onBackground
                )
            }

            Box(
                modifier = Modifier
                    .size(50.dp)
                    .clip(RoundedCornerShape(15.dp))
                    .background(MaterialTheme.colors.onBackground)
                , contentAlignment = Alignment.Center){
                Text(
                    text = if(begin && alphabets[indexCounter2].isWhitespace()) "~" else if(begin && !alphabets[indexCounter2].isWhitespace()) alphabets[indexCounter2].toString() else "",
                    fontWeight = FontWeight.Bold, color = MaterialTheme.colors.background)
            }
        }
    }
}