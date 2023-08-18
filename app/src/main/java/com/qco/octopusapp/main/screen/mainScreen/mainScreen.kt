package com.qco.octopusapp.main.screen.mainScreen

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.qco.octopusapp.viewModel.OctopusAction
import com.qco.octopusapp.R
import com.qco.octopusapp.main.navigationDrawer.DrawerBody
import com.qco.octopusapp.main.navigationDrawer.DrawerHeader
import com.qco.octopusapp.main.navigationDrawer.DrawerItem
import com.qco.octopusapp.main.screen.appBar.AppBar
import com.qco.octopusapp.main.screen.bottomBar.BottomButtons
import com.qco.octopusapp.main.screen.bottomBar.BottomButtonsItem
import com.qco.octopusapp.main.screen.resultTV.ResultDisplay
import com.qco.octopusapp.main.screen.textFeilds.TextFields
import com.qco.octopusapp.main.screen.toolSet.ToolsSet
import com.qco.octopusapp.viewModel.OctopusState

/**main screen composable**/

@Composable
fun MainScreen(
    context: Context,
    state: OctopusState,
    scaffoldState: ScaffoldState,
    onAction:(OctopusAction) -> Unit,
    navController: NavController,
    onNavigationClicked:()->Unit,
    changeTheme:()->Unit,
    changeLanguage:()->Unit,
    language:String,
    saveToClipboard:(link:String)->Unit
) {

    navController.enableOnBackPressed(enabled = false)

    Box(modifier= Modifier
        .fillMaxSize()
        .background(color = MaterialTheme.colors.background)
        .padding(4.dp)) {

        Column{
            Scaffold(modifier = Modifier.background(MaterialTheme.colors.background),
                scaffoldState = scaffoldState,
                topBar = {
                    AppBar(
                        result = state.result,
                        onNavigationClicked = onNavigationClicked,
                        saveToClipboard = saveToClipboard)
                },
                drawerGesturesEnabled = scaffoldState.drawerState.isOpen,
                drawerContent = {
                    DrawerHeader(image = painterResource(id = R.drawable.octopus), imageDescription = "")
                    DrawerBody(items = listOf(
                        DrawerItem(
                            id = "theme",
                            title = stringResource(id = R.string.theme),
                            icon = painterResource(id = R.drawable.theme_icon),
                            description = "theme icon"
                        ),
                        DrawerItem(
                            id = "language",
                            title = language,
                            icon = painterResource(id = R.drawable.ic_baseline_language_24 ),
                            description = "Language icon"),
                        DrawerItem(
                            id = "docs",
                            title = stringResource(id = R.string.docs),
                            icon = painterResource(id = R.drawable.ic_baseline_read_more_24 ),
                            description = "documentation icon"),
                        DrawerItem(
                            id = "about",
                            title = stringResource(id = R.string.info),
                            icon = painterResource(id = R.drawable.ic_baseline_info_24 ),
                            description = "app information icon")
                    )
                        , onItemClicked = {
                            when(it.id){
                                "theme"->{
                                    changeTheme()
                                }
                                "docs"->{
                                    navController.navigate("docs_screen")
                                }
                                "language"->{
                                    changeLanguage()
                                }
                                "about"->{
                                    navController.navigate("info_screen")
                                }
                            }
                        })
                },
                bottomBar = {

                    BottomButtons(
                        item = if(state.cipher == "B2T" || state.cipher == "T2B") {
                            listOf(
                                BottomButtonsItem(
                                    id = "con",
                                    name = "Convert",
                                    icon = painterResource(id = R.drawable.ic_baseline_autorenew_24)
                                )
                            )
                        }
                        else
                            {
                                listOf(
                                    BottomButtonsItem(
                                        id = "enc",
                                        name = "Encryption",
                                        icon = painterResource(id = R.drawable.ic_baseline_lock_24)
                                    ),

                                    BottomButtonsItem(
                                        id = "dec",
                                        name = "Decryption",
                                        icon = painterResource(id = R.drawable.ic_baseline_lock_open_24)
                                    )
                                )
                        },
                        onClick = {
                            onAction(OctopusAction.Cryptography(name = it.name, context = context))
                        }
                    ) }
                    , backgroundColor = MaterialTheme.colors.background
                ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Spacer(modifier = Modifier.padding(2.dp))

                    ResultDisplay(state = state,
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(.5f)
                            .padding(4.dp)
                            .background(MaterialTheme.colors.secondary)
                            .shadow(2.dp, clip = true)
                            .padding(10.dp))

                    ToolsSet(state = state,
                        onAction = onAction,
                        context = context,
                        navController = navController)

                    Divider(Modifier.padding(bottom =  10.dp))

                    TextFields(state = state,
                        onAction = onAction,
                        modifier = Modifier
                            .padding(horizontal = 4.dp))
                }
            }
        }
    }
}









