package com.qco.octopusapp.visualization.ciphers.caesar.nEquation

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
fun NEquation(
    size:Int,
    indexCounter:Int,
    key:Int,
    shouldStart:Boolean,
    showN:Boolean,
    n:Int,
    cryptography:String,
    onDialogClicked:(step:String) -> Unit
) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(4.dp),
        horizontalAlignment = Alignment.Start) {
        Text(
            text = "N Equation",
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
                .clickable { onDialogClicked("n Equation") }
                .padding(vertical = 16.dp, horizontal = 4.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center) {
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start) {

                Text(text = "n =", fontWeight = FontWeight.Bold, color = MaterialTheme.colors.onBackground)
                Text(text = if(cryptography == "Encryption") " (index + key) % size" else " (index - key) % size", color = MaterialTheme.colors.primary, fontSize = 10.sp)
            }
            Row(modifier = Modifier
                .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween) {
                Box(
                    modifier = Modifier
                        .size(height = 50.dp, width = 120.dp)
                        .clip(RoundedCornerShape(15.dp))
                        .background(MaterialTheme.colors.onBackground)
                    , contentAlignment = Alignment.Center){
                    Text(text = if (shouldStart && cryptography == "Encryption") "( $indexCounter + $key )"
                    else if (shouldStart && cryptography != "Encryption") "( $indexCounter - $key )"
                    else if (!shouldStart && cryptography != "Encryption") " - $key"
                    else " + $key",
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colors.background)
                }
                Text(text = " % ", color = MaterialTheme.colors.onBackground)
                Box(
                    modifier = Modifier
                        .size(50.dp)
                        .clip(RoundedCornerShape(15.dp))
                        .background(MaterialTheme.colors.onBackground)
                    , contentAlignment = Alignment.Center){
                    Text(text = "$size", fontWeight = FontWeight.Bold, color = MaterialTheme.colors.background)
                }
                Text(text = " = ", color = MaterialTheme.colors.onBackground)
                Box(
                    modifier = Modifier
                        .size(50.dp)
                        .clip(RoundedCornerShape(15.dp))
                        .background(MaterialTheme.colors.onBackground)
                    , contentAlignment = Alignment.Center){
                    Text(text = if(showN) "$n" else "", fontWeight = FontWeight.Bold, color = MaterialTheme.colors.background)
                }
            }
        }
    }
}