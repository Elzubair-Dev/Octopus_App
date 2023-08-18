package com.qco.octopusapp.main.navigationDrawer

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import com.qco.octopusapp.ui.theme.Dark

@Composable
fun DrawerHeader(image:Painter, imageDescription:String) {
    Box(modifier = Modifier
        .fillMaxWidth()
        .background(MaterialTheme.colors.background)
        .clip(RoundedCornerShape(bottomEnd = 15.dp))
        .background(MaterialTheme.colors.onSurface)
        .padding(vertical = 32.dp), contentAlignment = Alignment.Center) {
        Image(painter = image,
            contentDescription = imageDescription,
            colorFilter = ColorFilter.tint(Dark),
            modifier = Modifier.size(100.dp).padding(bottom = 16.dp))
    }
}