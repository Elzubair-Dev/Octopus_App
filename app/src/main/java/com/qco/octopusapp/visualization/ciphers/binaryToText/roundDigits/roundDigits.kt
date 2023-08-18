package com.qco.octopusapp.visualization.ciphers.binaryToText.roundDigits

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun RoundDigits(letterDigits:String,
                round:Int,
                isSpace:Boolean,
                globalIndex:Int,
                decimalValue:Int,
                decimalList:MutableList<Int>,
                onDialogClicked:(step:String)->Unit) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(4.dp),
        horizontalAlignment = Alignment.Start) {
        Text(
            text = "To Decimal",
            fontSize = 12.sp,
            color = MaterialTheme.colors.onBackground,
            modifier = Modifier.padding(bottom = 4.dp, start = 8.dp)
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .shadow(elevation = 15.dp, shape = RoundedCornerShape(15.dp))
                .clip(shape = RoundedCornerShape(15.dp))
                .background(MaterialTheme.colors.secondary)
                .clickable { onDialogClicked("To Decimal") }
                .padding(vertical = 16.dp, horizontal = 4.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(modifier = Modifier
                .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically) {
                Column(horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    Text(text = "Letter $round = ", color = MaterialTheme.colors.onBackground)
                    Text(text = "index ", fontSize = 8.sp, color = MaterialTheme.colors.primary)
                }
                if(!isSpace){
                    letterDigits.forEachIndexed { index, digit ->
                        Column(horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(4.dp)) {

                            Text(text = "$digit", color = MaterialTheme.colors.onBackground)

                            Text(text = "$index", fontSize = 8.sp, color = if(index == globalIndex) MaterialTheme.colors.onBackground else MaterialTheme.colors.background)
                        }
                    }
                }
                else {
                    Box(modifier = Modifier
                        .size(width = 50.dp, height = 50.dp)
                        .clip(RoundedCornerShape(15.dp))
                        .background(MaterialTheme.colors.onBackground),
                        contentAlignment = Alignment.Center) {
                        Text(text = "~", color = MaterialTheme.colors.background)
                    }
                }
            }
            if(!isSpace){
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "index $globalIndex = ", color = MaterialTheme.colors.onBackground)
                    Text(text = "${letterDigits[globalIndex]} ", color = if(letterDigits[globalIndex] == '0') Color.Red else MaterialTheme.colors.onBackground)
                    Text(text = "x (2 to power ", color = MaterialTheme.colors.onBackground)
                    Text(text = "$globalIndex ", color = MaterialTheme.colors.onBackground)
                    Text(text = ") = ", color = MaterialTheme.colors.onBackground)
                    Text(text ="$decimalValue", color = if(decimalValue == 0) Color.Red else MaterialTheme.colors.onBackground)
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "decimal list = ", color = MaterialTheme.colors.onBackground)
                    LazyRow( horizontalArrangement = Arrangement.Center){
                        items(1){
                            Text(text = "[", color = MaterialTheme.colors.onBackground)
                            decimalList.forEachIndexed { index, i ->
                                Text(text = if(index < 7) " $i," else " $i", color = if(i == 0) Color.Red else MaterialTheme.colors.onBackground)
                            }
                            Text(text = "]", color = MaterialTheme.colors.onBackground)
                        }
                    }
                }
            }
        }
    }
}