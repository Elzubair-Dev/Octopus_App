package com.qco.octopusapp.info


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.qco.octopusapp.R
import com.qco.octopusapp.info.pagesComponents.AppPage
import com.qco.octopusapp.info.pagesComponents.DeveloperPage
import com.qco.octopusapp.main.screen.bottomBar.BottomButtons
import com.qco.octopusapp.main.screen.bottomBar.BottomButtonsItem

@Composable
fun Info(
        language: String,
        navController: NavController,
        closeNavigation:()->Unit,
        saveToClipboard:(link:String)->Unit,
        goToURL:(link:String)->Unit
) {

    navController.enableOnBackPressed(enabled = false)

    var page by remember {
        mutableStateOf("App")
    }
    var direction by remember {
        mutableStateOf(LayoutDirection.Ltr)
    }
    direction = if(language == "Language"){
        LayoutDirection.Ltr
    }
    else {
        LayoutDirection.Rtl
    }

    CompositionLocalProvider(LocalLayoutDirection provides (LayoutDirection.Ltr)) {
        Scaffold(bottomBar = {
            BottomButtons(item = listOf(
                BottomButtonsItem(
                    id = "app",
                    name = stringResource(id = R.string.app),
                    icon = painterResource(id = R.drawable.ic_baseline_phone_android_24)
                ),
                BottomButtonsItem(
                    id = "dev",
                    name = stringResource(id = R.string.dev),
                    icon = painterResource(id = R.drawable.ic_baseline_person_outline_24)
                )
            ),
                onClick = {
                when(it.id){
                    "app" -> {
                        page = "App"
                    }
                    "dev" -> {
                        page = "Developer"
                    }
                }
            },
                color = MaterialTheme.colors.primaryVariant)
        }) {
            if(page == "Developer"){
                Column(modifier = Modifier.fillMaxWidth()) {
                    DeveloperPage(direction = direction,
                        navController = navController,
                        closeNavigation = closeNavigation,
                        saveToClipboard = saveToClipboard,
                    goToURL = goToURL)

                    Spacer(modifier = Modifier.padding(vertical = 16.dp))
                }
            }
            else{
                Column(modifier = Modifier.fillMaxWidth()) {
                    AppPage(direction = direction, navController = navController, closeNavigation = closeNavigation)
                    Spacer(modifier = Modifier.padding(vertical = 16.dp))
                }
            }
        }
    }
}