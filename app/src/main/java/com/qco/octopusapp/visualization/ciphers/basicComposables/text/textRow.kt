package com.qco.octopusapp.visualization.ciphers.basicComposables.text

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TextRow(
    text:String,
    round :Int,
    onDialogClicked:(step:String)->Unit
) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(4.dp),
        horizontalAlignment = Alignment.Start) {
        Text(text = "Text", fontSize = 12.sp, color = MaterialTheme.colors.onBackground, modifier = Modifier.padding(bottom = 4.dp, start = 8.dp))
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .shadow(elevation = 15.dp, shape = RoundedCornerShape(15.dp))
                .clip(shape = RoundedCornerShape(15.dp))
                .background(MaterialTheme.colors.secondary)
                .clickable { onDialogClicked("Text") }
                .padding(vertical = 16.dp, horizontal = 4.dp),
            horizontalArrangement = Arrangement.Center){
            items(count = 1 ){
                text.forEachIndexed { index, char ->
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .size(if (index == round) 52.dp else 50.dp)
                            .border(
                                border = if (index == round) BorderStroke(3.dp, color = MaterialTheme.colors.primary) else BorderStroke(0.dp, Color.Transparent),
                                shape = RoundedCornerShape(15.dp)
                            )
                            .clip(RoundedCornerShape(15.dp))
                            .background(MaterialTheme.colors.onBackground)
                    ) {
                        Text(
                            text = if(char.isWhitespace()) "~" else "$char", color = MaterialTheme.colors.background
                        )
                    }
                    Spacer(modifier = Modifier.padding(4.dp))
                }
            }
        }
    }
}