package com.qco.octopusapp.main.navigationDrawer

import androidx.compose.ui.graphics.painter.Painter

data class DrawerItem(
    val id :String,
    val title:String,
    val description:String,
    val icon: Painter
)
