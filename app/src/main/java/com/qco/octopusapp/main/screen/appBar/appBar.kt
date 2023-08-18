package com.qco.octopusapp.main.screen.appBar

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.qco.octopusapp.R

@Composable
fun AppBar(modifier: Modifier = Modifier,
           result:String,
           onNavigationClicked:()->Unit,
           saveToClipboard:(link:String)->Unit) {
    Box(
        modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)) {
        Image(painter = painterResource(id = R.drawable.ic_baseline_more_vert_24),
            contentDescription = "Theme mode",
            colorFilter = ColorFilter.tint(MaterialTheme.colors.onBackground),
        modifier = Modifier
            .align(Alignment.CenterStart)
            .clip(shape = CircleShape)
            .clickable {
                onNavigationClicked()
            })

        Image(painter = painterResource(id = R.drawable.octopus), contentDescription = "App Logo",
            colorFilter = ColorFilter.tint(MaterialTheme.colors.onBackground),
            modifier = Modifier
                .size(40.dp)
                .align(Alignment.Center))
        Image(painter = painterResource(id = R.drawable.ic_baseline_content_copy_24), contentDescription = "Save to the clipboard",
        colorFilter = ColorFilter.tint(MaterialTheme.colors.onBackground),
        modifier = Modifier
            .align(alignment = Alignment.CenterEnd)
            .clip(shape = CircleShape)
            .clickable {
                saveToClipboard(result)
            })
    }
}