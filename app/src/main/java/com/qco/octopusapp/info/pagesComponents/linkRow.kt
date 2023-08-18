package com.qco.octopusapp.info.pagesComponents

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.qco.octopusapp.R

@Composable
fun LinkRow(imageId:Int,
            des:String,
            text:String,
            extraText:String = "",
            link :String,
            goToURL:(link:String)->Unit,
            saveToClipboard:(link:String)->Unit) {
    Row(
        modifier = Modifier
            .padding(vertical = 8.dp)
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(15.dp))
            .background(color = MaterialTheme.colors.secondary)
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(painter = painterResource(id = imageId),
                contentDescription = des,
                colorFilter = ColorFilter.tint(color = MaterialTheme.colors.onBackground),
                modifier = Modifier.size(20.dp))
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(6f)
                .padding(horizontal = 6.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = text, fontSize = 16.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colors.onBackground)
            Spacer(modifier = Modifier.padding(horizontal = 6.dp))
            Text(text = extraText, fontSize = 12.sp, color = MaterialTheme.colors.primary)
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(3f),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(painter = painterResource(id = R.drawable.ic_baseline_content_copy_24),
                contentDescription = "Save to Clipboard",
                colorFilter = ColorFilter.tint(color = MaterialTheme.colors.onBackground),
                modifier = Modifier
                    .clip(shape = CircleShape)
                    .size(20.dp)
                    .clickable {
                        saveToClipboard(link)
                    })

            Text(text = "Go",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colors.onBackground,modifier = Modifier
                    .clip(shape = CircleShape)
                    .clickable {
                        goToURL(link)
                    })
        }
    }
}