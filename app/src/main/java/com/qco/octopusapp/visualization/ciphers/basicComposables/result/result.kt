package com.qco.octopusapp.visualization.ciphers.basicComposables.result

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Result(
    result:String,
    begin:Boolean,
    round:Int,
    onDialogClicked:(step:String)->Unit
) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(4.dp),
        horizontalAlignment = Alignment.Start) {
        Text(
            text = "Result",
            fontSize = 12.sp,
            color = MaterialTheme.colors.onBackground,
            modifier = Modifier.padding(bottom = 4.dp, start = 8.dp)
        )
        LazyRow(horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .shadow(elevation = 15.dp, shape = RoundedCornerShape(15.dp))
                .clip(shape = RoundedCornerShape(15.dp))
                .background(MaterialTheme.colors.secondary)
                .clickable { onDialogClicked("Result") }
                .padding(vertical = 16.dp, horizontal = 4.dp)
        ){
            items(count = 1){
                result.forEachIndexed { index, char ->
                    Box(modifier = Modifier
                        .size(50.dp)
                        .clip(RoundedCornerShape(15.dp))
                        .background(MaterialTheme.colors.onBackground),
                        contentAlignment = Alignment.Center,) {
                        Text(text = if(index < round - 1 && !char.isWhitespace()) "$char"
                        else if(index < round - 1 && char.isWhitespace()) "~"
                        else if(index < round && begin && !char.isWhitespace()) "$char"
                        else if(index < round && begin && char.isWhitespace()) "~"
                        else "",
                            color = MaterialTheme.colors.background)
                    }
                    Spacer(modifier = Modifier.padding(4.dp))
                }
            }
        }
    }
}