package com.qco.octopusapp.visualization.ciphers.binaryToText.toChar

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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ToChar(ascii:Int,
           toChar:Boolean,
           showChar:Boolean,
           isSpace:Boolean,
           onDialogClicked:(step:String)->Unit) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(4.dp),
        horizontalAlignment = Alignment.Start) {
        Text(
            text = "To Char",
            fontSize = 12.sp,
            color = MaterialTheme.colors.onBackground,
            modifier = Modifier.padding(bottom = 4.dp, start = 8.dp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .shadow(elevation = 15.dp, shape = RoundedCornerShape(15.dp))
                .clip(shape = RoundedCornerShape(15.dp))
                .background(MaterialTheme.colors.secondary)
                .clickable { onDialogClicked("To Char") }
                .padding(vertical = 16.dp, horizontal = 4.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(modifier = Modifier
                .size(width = 50.dp, height = 50.dp)
                .clip(RoundedCornerShape(15.dp))
                .background(MaterialTheme.colors.onBackground),
                contentAlignment = Alignment.Center) {
                Text(text = if(toChar && !isSpace) "$ascii" else "", color = MaterialTheme.colors.background)
            }
            Text(text = "to Char is", color = MaterialTheme.colors.onBackground)
            Box(modifier = Modifier
                .size(width = 50.dp, height = 50.dp)
                .clip(RoundedCornerShape(15.dp))
                .background(MaterialTheme.colors.onBackground),
                contentAlignment = Alignment.Center) {
                Text(text = if(toChar && !isSpace && showChar) "${ascii.toChar()}" else "", color = MaterialTheme.colors.background)
            }
        }
    }
}