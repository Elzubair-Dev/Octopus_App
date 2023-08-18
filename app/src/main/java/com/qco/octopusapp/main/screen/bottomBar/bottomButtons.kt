package com.qco.octopusapp.main.screen.bottomBar

import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun BottomButtons(
    item:List<BottomButtonsItem>,
    modifier: Modifier = Modifier,
    onClick:(BottomButtonsItem) -> Unit,
    color:Color = MaterialTheme.colors.background
) {
    BottomNavigation(
        modifier = modifier,
        backgroundColor = MaterialTheme.colors.background,
        elevation = 10.dp
    ) {
        item.forEach { item ->
            BottomNavigationItem(
                onClick = { onClick(item) },
                selected = false,
                selectedContentColor = MaterialTheme.colors.background,
                unselectedContentColor = color,
                icon = {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(painter = item.icon, contentDescription = "Item Icon", tint = MaterialTheme.colors.onBackground)
                        Text(text = item.name,
                            textAlign = TextAlign.Center,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold, color = MaterialTheme.colors.onBackground
                        )
                    }
                }
            )

        }
    }
}