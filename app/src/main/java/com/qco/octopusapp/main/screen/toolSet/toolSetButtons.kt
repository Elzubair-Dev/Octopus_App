package com.qco.octopusapp.main.screen.toolSet

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ToolSetButton(text:String,
                  image:Painter,
                  content:String,
                  color:Color,
                  onClick:()->Unit) {
    Column(horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .size(60.dp)
                .clip(CircleShape)
                .padding(2.dp)
                .clickable {
                    onClick()
                }) {
            Image(painter = image,
                contentDescription = content,
                colorFilter = ColorFilter.tint(color),
                modifier = Modifier.size(25.dp))
            Text(text = text, fontSize = 6.sp, fontWeight = FontWeight.Bold, color = color)
        }
}

@Composable
fun CipherButton(
    text:String,
    image:Painter,
    content:String,
    color:Color,
    onClick:()->Unit
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
        , modifier = Modifier
            .size(60.dp)
            .clip(CircleShape)
            .padding(2.dp)
            .clickable {
                onClick()
            }) {
        Text(text = text, fontSize = 8.sp, fontWeight = FontWeight.Bold, color = color)
        Image(painter = image,
            contentDescription = content,
            colorFilter = ColorFilter.tint(color = color),
            modifier = Modifier.size(25.dp))
    }
}