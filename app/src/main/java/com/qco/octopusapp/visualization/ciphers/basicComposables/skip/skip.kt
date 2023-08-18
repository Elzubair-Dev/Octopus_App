package com.qco.octopusapp.visualization.ciphers.basicComposables.skip

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.qco.octopusapp.R

@Composable
fun Skip(navController: NavController, language:String) {
    var direction by remember {
        mutableStateOf(LayoutDirection.Ltr)
    }
    direction = if(language == "Language"){
        LayoutDirection.Ltr
    }
    else {
        LayoutDirection.Rtl
    }

    CompositionLocalProvider(LocalLayoutDirection provides (direction)) {
        Row(verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colors.background)
                .padding(all = 16.dp)) {
            Box(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = stringResource(id = R.string.back),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.onBackground
                    , modifier = Modifier
                        .align(alignment = Alignment.CenterStart)
                        .clip(CircleShape)
                        .clickable { navController.navigate("main_screen") })
            }
        }
    }
}