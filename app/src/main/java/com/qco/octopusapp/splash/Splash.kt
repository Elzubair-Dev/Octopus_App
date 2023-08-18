package com.qco.octopusapp.splash


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.qco.octopusapp.R
import com.qco.octopusapp.ui.theme.Dark
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navController: NavController
) {
    /**Side effect that delay for 2 seconds and then navigate to docs screen**/
    LaunchedEffect(key1 = true){
        delay(2000L)
        navController.navigate(route = "docs_screen")
        navController.enableOnBackPressed(enabled = false)
    }

    Box(contentAlignment = Alignment.BottomCenter,
    modifier = Modifier
        .fillMaxSize()
        .background(color = MaterialTheme.colors.onSurface)) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 32.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .weight(1f)
                    .background(MaterialTheme.colors.onSurface)
                    .clip(
                        RoundedCornerShape(bottomEnd = 40.dp)
                    )
                    .background(MaterialTheme.colors.background)
            ) {}
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .weight(2f)
                    .background(MaterialTheme.colors.background)
                    .clip(
                        RoundedCornerShape(topStart = 40.dp)
                    )
                    .background(MaterialTheme.colors.onSurface),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(painter = painterResource(id = R.drawable.octopus),
                    contentDescription = "Octopus Logo"
                , colorFilter = ColorFilter.tint(Dark)
                , alignment = Alignment.BottomCenter,
                modifier = Modifier.fillMaxWidth(.8f))
                Spacer(modifier = Modifier.padding(vertical = 24.dp))
                Text(text = "11110010110001100010111011110110000011101010111011001110", color = Dark, fontSize = 7.sp, fontWeight = FontWeight.Bold)
            }
        }
    }

}