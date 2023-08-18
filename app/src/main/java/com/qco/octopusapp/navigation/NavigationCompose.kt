@file:Suppress("OPT_IN_IS_NOT_ENABLED")

package com.qco.octopusapp.navigation

import android.content.Context
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.qco.octopusapp.info.Info
import com.qco.octopusapp.explanationPage.ExplanationPage
import com.qco.octopusapp.main.screen.mainScreen.MainScreen
import com.qco.octopusapp.splash.SplashScreen
import com.qco.octopusapp.viewModel.OctopusAction
import com.qco.octopusapp.viewModel.OctopusState
import com.qco.octopusapp.viewPagers.pagerComposables.ViewPager
import com.qco.octopusapp.visualization.mainScreen.VisualisationMainScreen

/**navigation composable**/

@OptIn(ExperimentalPagerApi::class)
@Composable
fun Navigation(
    context: Context,
    state: OctopusState,
    onAction:(OctopusAction) -> Unit,
    onDialogClicked : (step:String) -> Unit,
    onNavigationClicked:()->Unit,
    alphabet:List<Char>,
    unSortedAlphabet:List<Char>,
    onDismiss:()->Unit,
    changeTheme:()->Unit,
    changeLanguage:()->Unit,
    closeNavigation:()->Unit,
    saveToClipboard:(link:String)->Unit,
    scaffoldState:ScaffoldState,
    isDark:Boolean,
    language:String,
    goToURL:(link:String)->Unit) {

    /**Navigation Controller**/
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "splash_screen" ){

        /**splash screen composable**/
        composable(route = "splash_screen"){
            SplashScreen(
                navController = navController
            )
        }

        /**main screen composable**/
        composable(route ="main_screen"){
            MainScreen(
                context = context,
                state = state,
                onAction = onAction,
                navController = navController,
                onNavigationClicked = onNavigationClicked,
                scaffoldState = scaffoldState,
                changeTheme = changeTheme,
                changeLanguage = changeLanguage,
                language = language,
                saveToClipboard = saveToClipboard
            )
        }

        /**visualising screen composable**/
        composable(route ="visualisation_screen"){
            VisualisationMainScreen(
                cipher = state.cipher,
                text = state.text,
                result = state.result,
                alphabet = alphabet,
                unSortedAlphabet = unSortedAlphabet,
                cryptographyType = state.cryptographyProcess,
                key = state.key,
                navController = navController,
                onDialogClicked = onDialogClicked,
                step = state.step,
                openDialog = state.openDialog,
                onDismiss = onDismiss,
                language = language)
        }

        /**docs screen composable**/
        composable(route ="docs_screen"){
            ViewPager(navController = navController,
                isDark = isDark,
                closeNavigation = closeNavigation,
                language = language)
        }

        /**Info screen composable**/
        composable(route ="info_screen"){
            Info(
                language = language,
                navController = navController,
                closeNavigation = closeNavigation,
                saveToClipboard = saveToClipboard,
                goToURL = goToURL)
        }

        /**Explanation screen composable**/
        composable(route ="explanation_screen"){
            ExplanationPage(cipher = state.cipher,
                language = language,
                crypto = state.cryptographyProcess,
                navController = navController,
                alphabet = alphabet,
                unsortedAlphabet = unSortedAlphabet)
        }
    }
}