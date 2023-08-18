package com.qco.octopusapp.info.pagesComponents

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.qco.octopusapp.R

@Composable
fun AppPage(direction: LayoutDirection,
            navController: NavController,
            closeNavigation:()->Unit) {
    CompositionLocalProvider(LocalLayoutDirection provides (LayoutDirection.Ltr)) {
        Scaffold(topBar = {
            Box(modifier = Modifier
                .fillMaxWidth()
                .background(color = MaterialTheme.colors.background)
                .padding(8.dp)) {
                Image(painter = painterResource(id = R.drawable.ic_baseline_arrow_right_alt_24),
                    contentDescription = "Back arrow",
                    colorFilter = ColorFilter.tint(MaterialTheme.colors.onBackground),
                    modifier = Modifier
                        .align(alignment = Alignment.CenterEnd)
                        .clip(RoundedCornerShape(5.dp))
                        .padding(5.dp)
                        .clickable {
                            closeNavigation()
                            navController.navigate(route = "main_screen")
                        })
            }
        }) {
            CompositionLocalProvider(LocalLayoutDirection provides (direction)) {
                LazyColumn(horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = MaterialTheme.colors.background)
                        .padding(16.dp)) {
                    items(count = 1) {
                        Box(modifier = Modifier
                            .fillMaxWidth()
                            .background(MaterialTheme.colors.background), Alignment.Center) {
                            Image(
                                painter = painterResource(id = R.drawable.octopus),
                                contentDescription = "",
                                colorFilter = ColorFilter.tint(MaterialTheme.colors.primary),
                                modifier = Modifier
                                    .size(100.dp)
                                    .padding(bottom = 16.dp))

                            Divider(modifier = Modifier
                                .fillMaxWidth(.5f)
                                .padding(start = 16.dp, end = 16.dp)
                                .background(MaterialTheme.colors.onBackground)
                                .align(alignment = Alignment.BottomCenter))
                        }
                        Spacer(modifier = Modifier.padding(8.dp))
                        Column(
                            horizontalAlignment = Alignment.Start,
                            verticalArrangement = Arrangement.spacedBy(8.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 16.dp)
                        ) {
                            Text(
                                text = stringResource(id = R.string.app) + ":",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colors.onBackground
                            )
                            Column(
                                horizontalAlignment = Alignment.Start,
                                verticalArrangement = Arrangement.spacedBy(8.dp),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 16.dp)
                            ) {
                                Row(horizontalArrangement = Arrangement.Start,
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(top = 16.dp)) {
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .weight(1f),
                                        horizontalArrangement = Arrangement.Start,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Text(
                                            text = stringResource(id = R.string.name)+":",
                                            fontSize = 12.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = MaterialTheme.colors.onBackground
                                        )
                                    }
                                    Row(modifier = Modifier
                                        .fillMaxWidth()
                                        .weight(1f),
                                        horizontalArrangement = Arrangement.Center,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Text(text = "Octopus app",
                                            fontSize = 10.sp,
                                            color = MaterialTheme.colors.primary)
                                    }
                                }
                                Spacer(modifier = Modifier.padding(vertical = 4.dp))
                                Row(horizontalArrangement = Arrangement.SpaceEvenly,
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier
                                        .fillMaxWidth()) {
                                    Row(modifier = Modifier
                                        .fillMaxWidth()
                                        .weight(1f),
                                        horizontalArrangement = Arrangement.Start,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Text(
                                            text = stringResource(id = R.string.version)+":",
                                            fontSize = 12.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = MaterialTheme.colors.onBackground
                                        )
                                    }
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .weight(1f),
                                        horizontalArrangement = Arrangement.Center,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Text(text = "1.0",
                                            fontSize = 10.sp,
                                            color = MaterialTheme.colors.primary)
                                    }
                                }
                                Spacer(modifier = Modifier.padding(vertical = 4.dp))
                                Row(horizontalArrangement = Arrangement.SpaceEvenly,
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier
                                        .fillMaxWidth()) {
                                    Row(modifier = Modifier
                                        .fillMaxWidth()
                                        .weight(1f),
                                        horizontalArrangement = Arrangement.Start,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Text(
                                            text = "Min SDK: ",
                                            fontSize = 12.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = MaterialTheme.colors.onBackground
                                        )
                                    }
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .weight(1f),
                                        horizontalArrangement = Arrangement.Center,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Text(text = "API 21: Android 5.0 (Lollipop)",
                                            fontSize = 10.sp,
                                            color = MaterialTheme.colors.primary)
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}