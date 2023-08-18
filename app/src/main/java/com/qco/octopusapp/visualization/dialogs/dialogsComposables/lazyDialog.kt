@file:Suppress("OPT_IN_IS_NOT_ENABLED")

package com.qco.octopusapp.visualization.dialogs.dialogsComposables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.qco.octopusapp.R
import com.qco.octopusapp.ui.theme.Dark

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun LazyRowDialog(title:String,
                  explanation: String,
                  language: String,
                  image: Painter,
                  listTitle: String,
                  list: List<Char>,
                  withIndex:Boolean,
                  onDismiss:()->Unit) {
    var direction by remember {
        mutableStateOf(LayoutDirection.Ltr)
    }
    direction = if(language == "Language"){
        LayoutDirection.Ltr
    }
    else {
        LayoutDirection.Rtl
    }
    Dialog(onDismissRequest = { onDismiss()},
        properties = DialogProperties(usePlatformDefaultWidth = true)) {
        Card(
            elevation = 5.dp,
            shape = RoundedCornerShape(15.dp),
            modifier = Modifier
                .fillMaxWidth(0.95f)
                .fillMaxHeight(0.7f)
                .clip(shape = RoundedCornerShape(15.dp))
                .border(
                    width = 1.dp,
                    color = MaterialTheme.colors.primary,
                    shape = RoundedCornerShape(15.dp)
                )
                .background(MaterialTheme.colors.background)
        ) {
            Scaffold(
                topBar = {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(MaterialTheme.colors.background),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
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
                },
                bottomBar = {
                    CompositionLocalProvider(LocalLayoutDirection provides (direction)) {
                        Divider(modifier = Modifier.padding(bottom = 4.dp))
                        Row(modifier = Modifier
                            .fillMaxWidth(),
                            horizontalArrangement = Arrangement.Start,
                            verticalAlignment = Alignment.CenterVertically) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                                Text(text = listTitle, fontSize = 12.sp, fontWeight = FontWeight.Bold,modifier = Modifier.padding(start = 8.dp), color = MaterialTheme.colors.onBackground)
                                Text(text = if(withIndex) stringResource(id = R.string.index) else stringResource(id = R.string.ascii), fontSize = 8.sp, fontWeight = FontWeight.Bold,modifier = Modifier.padding(start = 8.dp), color = MaterialTheme.colors.onBackground)
                            }
                            LazyRow(modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 4.dp, start = 8.dp, end = 8.dp)){
                                items(count = 1){
                                    Text(text = "[", color = MaterialTheme.colors.onBackground)
                                    list.forEachIndexed { index, c ->
                                        Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                                            Text(text = if(c.isWhitespace()) " ~ " else " $c ", color = MaterialTheme.colors.onBackground)
                                            Text(text = if(withIndex) "$index" else if(c.isWhitespace()) "" else  "${c.code}", fontSize = 6.sp, color = MaterialTheme.colors.onBackground)
                                        }
                                    }
                                    Text(text = "]", color = MaterialTheme.colors.onBackground)
                                }
                            }
                        }
                    }
                }
            ) {
                CompositionLocalProvider(LocalLayoutDirection provides (direction)) {
                    LazyColumn(modifier = Modifier
                        .fillMaxWidth(),
                        horizontalAlignment = Alignment.Start,
                        verticalArrangement = Arrangement.Center){
                        items(count = 1){
                            Text(text = explanation, fontSize = 12.sp, style = MaterialTheme.typography.h6, modifier = Modifier.padding(16.dp), color = MaterialTheme.colors.primary)
                        }
                    }
                }
            }
        }
    }
}