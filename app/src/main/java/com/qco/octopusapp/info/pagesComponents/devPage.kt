package com.qco.octopusapp.info.pagesComponents

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
fun DeveloperPage(direction: LayoutDirection,
                  navController: NavController,
                  closeNavigation:()->Unit,
                  saveToClipboard:(link:String)->Unit,
                  goToURL:(link:String)->Unit) {
    CompositionLocalProvider(LocalLayoutDirection provides (LayoutDirection.Ltr)) {
        Scaffold(modifier = Modifier.padding(bottom = 20 .dp), topBar = {
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
                LazyColumn(modifier = Modifier
                    .fillMaxSize()
                    .background(color = MaterialTheme.colors.background)
                    .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(16.dp)){
                    items(count = 1){
                        //--------------Header--------------//
                        Row(modifier = Modifier.fillMaxWidth()) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(1f),
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(80.dp)
                                        .clip(shape = CircleShape)
                                        .border(
                                            width = 2.dp,
                                            shape = CircleShape,
                                            color = MaterialTheme.colors.onSurface
                                        )
                                    ,contentAlignment = Alignment.Center) {
                                    Image(
                                        painter = painterResource(id = R.drawable.img),
                                        contentDescription = "Developer Profile Photo",
                                        modifier = Modifier
                                            .size(150.dp)
                                    )
                                }
                            }
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(1f),
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Column(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.SpaceAround
                                ) {
                                    Row(
                                        horizontalArrangement = Arrangement.Start,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Text(
                                            text = stringResource(id = R.string.my_name),
                                            fontSize = 18.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = MaterialTheme.colors.onBackground
                                        )
                                    }
                                    Spacer(modifier = Modifier.padding(vertical = 10.dp))
                                    Row(
                                        horizontalArrangement = Arrangement.Start,
                                        verticalAlignment = Alignment.CenterVertically){
                                        Text(text = "zu698air@gmail.com",
                                            fontSize = 14.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = MaterialTheme.colors.primary)
                                    }
                                    CompositionLocalProvider(LocalLayoutDirection provides (LayoutDirection.Ltr)) {
                                        Row(
                                            horizontalArrangement = Arrangement.Start,
                                            verticalAlignment = Alignment.CenterVertically){
                                            Text(text = "+249999938322",
                                                fontSize = 10.sp,
                                                fontWeight = FontWeight.Thin,
                                                color = MaterialTheme.colors.primary)
                                        }
                                    }
                                }
                            }
                        }
                        Row(horizontalArrangement = Arrangement.Start,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 16.dp)) {
                            Text(
                                text = stringResource(id = R.string.fmo),
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colors.onBackground
                            )
                        }
                        LinkRow(imageId = R.drawable.`in`,
                            des = "my LinkedIn account",
                            text = stringResource(id = R.string.linked_in),
                            link = "https://www.linkedin.com/in/el-zubair-el-seddeg-591917226/",
                            saveToClipboard = saveToClipboard,
                            goToURL = goToURL)

                        LinkRow(imageId = R.drawable.git,
                            des = "my Github account",
                            text = stringResource(id = R.string.github),
                            link = "https://github.com/Elzubair-Dev?tab=repositories",
                            saveToClipboard = saveToClipboard,
                            goToURL = goToURL)

                        LinkRow(imageId = R.drawable.facebook,
                            des = "my Facebook account",
                            text = stringResource(id = R.string.facebook),
                            link = "https://web.facebook.com/el.zubair.1/",
                            saveToClipboard = saveToClipboard,
                            goToURL = goToURL)

                        LinkRow(imageId = R.drawable.x,
                            des = "my X account",
                            text = stringResource(id = R.string.x),
                            extraText = stringResource(id = R.string.twitter),
                            link = "https://twitter.com/el_seddeg",
                            saveToClipboard = saveToClipboard,
                            goToURL = goToURL)

                        Row(horizontalArrangement = Arrangement.Start,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 16.dp)) {
                            Text(
                                text = stringResource(id = R.string.support),
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colors.onBackground
                            )
                        }
                        LinkRow(imageId = R.drawable.buy_me_a_coffee,
                            des = "buy me a coffee account",
                            text = stringResource(id = R.string.bmc),
                            link = "https://bmc.link/zu698air",
                            saveToClipboard = saveToClipboard,
                            goToURL = goToURL)

                        Spacer(modifier = Modifier.padding(bottom = 16.dp))
                    }
                }
            }
        }
    }
}