@file:Suppress("DEPRECATION")

package com.qco.octopusapp.main.mainActivity

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import androidx.lifecycle.viewmodel.compose.viewModel
import com.qco.octopusapp.language.Language
import com.qco.octopusapp.navigation.Navigation
import com.qco.octopusapp.viewModel.OctopusViewModel
import com.qco.octopusapp.ui.theme.OctopusAppTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            /**This line below give fix direction layout, Ltr means Left to Right**/
            CompositionLocalProvider(LocalLayoutDirection provides (LayoutDirection.Ltr)) {

                /**creating instance of viewModel and state**/
                val viewModel = viewModel<OctopusViewModel>()
                val state = viewModel.state

                /**State and coroutine scope to open and close navigation drawer**/
                val scaffoldState = rememberScaffoldState()
                val scope = rememberCoroutineScope()

                /**String variable that determine whether the language is English or Arabic**/
                var language by remember {
                    mutableStateOf("Language")
                }
                val langManager = Language(this@MainActivity)
                /**Setting default language as English**/
                if(language == "Language"){
                    langManager.updateLanguage("en")
                }

                /**Boolean variable that determine whether the theme is dark or not**/
                var isDark by remember {
                    mutableStateOf(false)
                }

                /**for changing app theme**/
                fun changeTheme(){
                    isDark = !isDark
                }

                /**for changing app language**/
                fun changeLanguage(){
                    if(language == "Language"){
                        langManager.updateLanguage("ar")
                        language = "اللغة"
                    }
                    else {
                        langManager.updateLanguage("en")
                        language = "Language"
                    }
                }

                /**In this default composable we can change theme**/
                OctopusAppTheme(darkTheme = isDark) {

                    /**next line for changing status bar content color to black**/
                    window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR

                    /**this function is for opening the navigation drawer**/
                    fun onNavigationClicked(){
                        scope.launch {
                            if(scaffoldState.drawerState.isOpen){
                                scaffoldState.drawerState.close()
                            }
                            else scaffoldState.drawerState.open()
                        }
                    }
                    /**For saving texts to clipboard, so you can paste it anywhere you want**/
                    fun saveToClipboard(link:String){
                        if(link.isNotBlank()){
                            val clipboard : ClipboardManager = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                            val clip : ClipData = ClipData.newPlainText("Link", link)
                            clipboard.setPrimaryClip(clip)
                            Toast.makeText(this@MainActivity, "Copied .", Toast.LENGTH_SHORT).show()
                        }
                        else {
                            Toast.makeText(this@MainActivity, "Empty Text .", Toast.LENGTH_SHORT).show()
                        }
                    }

                    /**To open URL Links on another app, for example browser**/
                    fun goToURL(link:String){
                        val uri : Uri = Uri.parse(link)
                        startActivity(Intent(Intent.ACTION_VIEW, uri))
                    }

                    /**To close navigation drawer**/
                    fun closeNavigation(){
                        scope.launch {
                            scaffoldState.drawerState.close()
                        }
                    }

                    /**navigation composable control everything**/
                    Navigation(context = this@MainActivity,
                        state = state,
                        onAction = viewModel::onAction,
                        onDialogClicked = viewModel::onDialogClicked,
                        closeNavigation = ::closeNavigation,
                        onNavigationClicked = ::onNavigationClicked,
                        scaffoldState = scaffoldState,
                        onDismiss = viewModel::onDismiss,
                        alphabet = viewModel.alphabet,
                        unSortedAlphabet = viewModel.unSortedAlphabet,
                        changeTheme = ::changeTheme,
                        changeLanguage = ::changeLanguage,
                        isDark = isDark,
                        language = language,
                        saveToClipboard = ::saveToClipboard,
                        goToURL = ::goToURL)
                }
            }
        }
    }
}
