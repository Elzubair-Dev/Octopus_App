@file:Suppress("OPT_IN_IS_NOT_ENABLED")

package com.qco.octopusapp.visualization.dialogs.dialogsComposables.pagerDialog.dialog

import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import com.qco.octopusapp.ui.theme.Dark
import com.qco.octopusapp.visualization.dialogs.dialogsComposables.pagerDialog.data.PagerDialogData
import kotlinx.coroutines.delay
import kotlinx.coroutines.yield

@OptIn(ExperimentalPagerApi::class, ExperimentalComposeUiApi::class)
@Composable
fun PagerDialog(image: Painter,
                title: String,
                pager:List<PagerDialogData>,
                language: String,
                onDismiss:()->Unit) {
    val pagerState = rememberPagerState(
        pageCount = pager.size,
        initialPage = 0
    )
    /**if you want view pager to automatically scroll to next page uncomment below code**/

    /**LaunchedEffect(Unit){
        while (true){
            yield()
            delay(10000L)
            pagerState.animateScrollToPage(
                page = if(pagerState.currentPage < pager.size - 1 ) (pagerState.currentPage + 1) else pagerState.currentPage,
                animationSpec = tween(600)
            )
        }
    }**/

    var direction by remember {
        mutableStateOf(LayoutDirection.Ltr)
    }
    direction = if(language == "Language"){
        LayoutDirection.Ltr
    }
    else {
        LayoutDirection.Rtl
    }
    Dialog(
        onDismissRequest = { onDismiss() },
        properties = DialogProperties(usePlatformDefaultWidth = true)
    ) {
        CompositionLocalProvider(LocalLayoutDirection provides (direction)) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(.95f)
                    .clip(RoundedCornerShape(15.dp))
                    .background(MaterialTheme.colors.background),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(topEnd = 15.dp))
                                .background(MaterialTheme.colors.primary)
                                .weight(1f)
                                .padding(10.dp),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Image(painter = image, contentDescription = null, modifier = Modifier.size(65.dp), colorFilter = ColorFilter.tint(Dark))
                        }
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(text = title, fontSize = 16.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colors.onBackground)
                        }
                    }
                    Divider(color = MaterialTheme.colors.onBackground)
                }
                Column(modifier = Modifier
                    .background(MaterialTheme.colors.background)
                    .padding(horizontal = 8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top) {
                    HorizontalPager(state = pagerState) { page ->
                        val newPage = pager[page]
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Top
                        ) {
                            Card(elevation = 2.dp) {
                                Image(painter = painterResource(id = newPage.image), contentDescription = "image")
                            }
                            Divider(modifier = Modifier.padding(vertical = 8.dp))
                            LazyColumn(modifier = Modifier
                                .fillMaxWidth()
                                .background(MaterialTheme.colors.background),
                                horizontalAlignment = Alignment.Start,
                                verticalArrangement = Arrangement.Center){
                                items(count = 1){
                                    Text(text = stringResource(id = newPage.explanation), fontSize = 10.sp, color = MaterialTheme.colors.onBackground)
                                }
                            }
                        }
                    }
                }
                HorizontalPagerIndicator(pagerState = pagerState, modifier = Modifier.padding(4.dp), indicatorWidth = 4.dp, inactiveColor = MaterialTheme.colors.primary, activeColor = MaterialTheme.colors.onBackground)
            }
        }
    }
}