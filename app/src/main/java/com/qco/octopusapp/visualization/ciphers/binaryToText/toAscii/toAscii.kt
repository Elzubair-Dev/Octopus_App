package com.qco.octopusapp.visualization.ciphers.binaryToText.toAscii

import androidx.compose.foundation.background
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Summation(decimalList:MutableList<Int>,
              ascii:Int,
              toAscii:Boolean,
              showAscii:Boolean,
              isSpace:Boolean,
              onDialogClicked:(step:String)->Unit) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(4.dp),
        horizontalAlignment = Alignment.Start) {
        Text(
            text = "Summation",
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
                .clickable { onDialogClicked("Summation") }
                .padding(vertical = 16.dp, horizontal = 4.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically)
        {
            Text(text = "ascii = ", color = MaterialTheme.colors.onBackground)
            LazyRow{
                items(1){
                    if(toAscii && !isSpace){
                        for(i in 0 until decimalList.size){
                            Text(text =
                            if(i < decimalList.size - 1) {
                                if(decimalList[i] != 0 ) "${decimalList[i]} + "
                                else ""}
                            else { if(decimalList[i] != 0 ) "${decimalList[i]}" else ""}
                                , color = MaterialTheme.colors.onBackground)
                        }
                    }
                }
            }
            Text(text = if(toAscii && !isSpace && showAscii) "= $ascii"
            else if(toAscii && !isSpace && showAscii) "= ~"
            else "=    ",
                color = MaterialTheme.colors.onBackground)
        }
    }
}