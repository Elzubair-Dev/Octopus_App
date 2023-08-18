package com.qco.octopusapp.viewPagers.pagerComposables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.accompanist.pager.*
import com.qco.octopusapp.viewPagers.data.darkPager
import com.qco.octopusapp.viewPagers.data.pager

@ExperimentalPagerApi
@Composable
fun ViewPager(
    navController: NavController,
    isDark : Boolean,
    language:String,
    closeNavigation:()->Unit
) {
    navController.enableOnBackPressed(enabled = false)
    val pagerState = rememberPagerState(
        pageCount = pager.size,
        initialPage = 0
    )

    /**if you want view pager to automatically scroll to next page uncomment below code**/

    /**
     * LaunchedEffect(Unit){
        while (true){
            yield()
            delay(3000L)
            pagerState.animateScrollToPage(
                page = (pagerState.currentPage + 1) % (pagerState.pageCount),
                animationSpec = tween(600)
            )
        }
    }**/

    /**for changing layout direction**/
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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            HorizontalPager(state = pagerState, modifier = Modifier
                .weight(1f)
                .padding(start = 4.dp, top = 16.dp, end = 4.dp, bottom = 16.dp)) { page ->
                Card(
                    modifier = Modifier
                        .background(MaterialTheme.colors.background)
                        .fillMaxWidth()
                        //.clip(RoundedCornerShape(15.dp))
                ) {
                    val newPage = if (isDark ) darkPager[page] else pager[page]
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(MaterialTheme.colors.background)
                                .padding(bottom = 8.dp)
                        ) {
                            Image(painter = painterResource(id = newPage.image), contentDescription = "image")
                        }
                        LazyColumn(modifier = Modifier
                            .fillMaxWidth()
                            .background(MaterialTheme.colors.background),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center){
                            items(count = 1){
                                Text(text = stringResource(id = newPage.explanation), fontSize = 14.sp, color = MaterialTheme.colors.onBackground)
                            }
                        }
                    }
                }
            }
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                Box(modifier = Modifier.fillMaxWidth()) {
                    HorizontalPagerIndicator(pagerState = pagerState, modifier = Modifier
                        .padding(8.dp)
                        .align(alignment = Alignment.Center), indicatorWidth = 4.dp, activeColor = MaterialTheme.colors.onBackground, inactiveColor = MaterialTheme.colors.primary)
                    Text(text ="Skip",
                        fontSize = 12.sp,
                        color = MaterialTheme.colors.onBackground,
                        modifier = Modifier
                            .align(alignment = Alignment.CenterEnd)
                            .padding(8.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .clickable {
                                navController.navigate("main_screen")
                                closeNavigation()
                            }
                    )

                }

            }
        }
    }
}