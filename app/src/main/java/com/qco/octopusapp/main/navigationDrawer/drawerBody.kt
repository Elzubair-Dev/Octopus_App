package com.qco.octopusapp.main.navigationDrawer

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DrawerBody(items: List<DrawerItem>, onItemClicked:(DrawerItem)->Unit) {
    LazyColumn(modifier = Modifier
        .fillMaxHeight()
        .background(MaterialTheme.colors.background),
    horizontalAlignment = Alignment.Start,
    verticalArrangement = Arrangement.spacedBy(10.dp)){
        items(items){item ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onItemClicked(item) }
                    .padding(8.dp),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(painter = item.icon, contentDescription = item.description, modifier = Modifier.size(24.dp).padding(end = 8.dp), tint = MaterialTheme.colors.onBackground)
                Text(text = item.title, fontSize = 12.sp, color = MaterialTheme.colors.onBackground)
            }
        }
    }
}