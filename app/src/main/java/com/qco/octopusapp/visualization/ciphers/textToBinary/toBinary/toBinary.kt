

package com.qco.octopusapp.visualization.ciphers.textToBinary.toBinary


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
fun ToBinary(ascii: Int,
             binaryList: String,
             divisionResult: Int,
             divisionMod:Int,
             onDialogClicked:(step:String)->Unit) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(4.dp),
        horizontalAlignment = Alignment.Start) {
        Text(
            text = "To Binary",
            fontSize = 12.sp,
            color = MaterialTheme.colors.onBackground,
            modifier = Modifier.padding(bottom = 4.dp, start = 8.dp)
        )
        Column(modifier = Modifier
            .fillMaxWidth()
            .shadow(elevation = 15.dp, shape = RoundedCornerShape(15.dp))
            .clip(shape = RoundedCornerShape(15.dp))
            .background(MaterialTheme.colors.secondary)
            .clickable { onDialogClicked("To Binary") }
            .padding(vertical = 16.dp, horizontal = 4.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally){
            Row(modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically) {
                Column(verticalArrangement = Arrangement.SpaceBetween,
                    horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "Division", fontSize = 10.sp, color = MaterialTheme.colors.onBackground)
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .size(width = 100.dp, height = 50.dp)
                            .clip(RoundedCornerShape(15.dp))
                            .background(MaterialTheme.colors.onBackground)
                    ) {
                        Text(
                            text = "$ascii / 2 =", color = MaterialTheme.colors.background
                        )
                    }
                }
                Column(verticalArrangement = Arrangement.SpaceBetween,
                    horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "Result", fontSize = 10.sp, color = MaterialTheme.colors.onBackground)
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .size(50.dp)
                            .clip(RoundedCornerShape(15.dp))
                            .background(MaterialTheme.colors.onBackground)
                    ) {
                        Text(text = "$divisionResult", color = MaterialTheme.colors.background)
                    }
                }
                Column(verticalArrangement = Arrangement.SpaceBetween,
                    horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "Mod", fontSize = 10.sp, color = MaterialTheme.colors.onBackground)
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .size(50.dp)
                            .clip(RoundedCornerShape(15.dp))
                            .background(MaterialTheme.colors.onBackground)
                    ) {
                        Text(text = "$divisionMod", color = MaterialTheme.colors.background)
                    }
                }
            }
            Spacer(modifier = Modifier.padding(8.dp))
            Row(modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically) {
                Text(text = "Remainders List:", color = MaterialTheme.colors.onBackground,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f))
                LazyRow(verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)){
                    items(1){
                        Text(text = "[", color = MaterialTheme.colors.onBackground)
                        binaryList.forEachIndexed { index, char->
                            Text(text = if(index < binaryList.length - 1)" $char," else " $char ", color = MaterialTheme.colors.onBackground)
                        }
                        Text(text = "]", color = MaterialTheme.colors.onBackground)
                    }
                }
            }
        }
    }
}