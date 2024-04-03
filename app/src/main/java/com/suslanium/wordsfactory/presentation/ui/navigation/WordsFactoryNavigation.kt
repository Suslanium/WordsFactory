package com.suslanium.wordsfactory.presentation.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.suslanium.wordsfactory.presentation.ui.screen.auth.signin.SignInScreen
import com.suslanium.wordsfactory.presentation.ui.screen.auth.signup.SignUpScreen
import com.suslanium.wordsfactory.presentation.ui.screen.intro.IntroScreen

object WordsFactoryDestinations {
    const val INTRO = "intro"
    const val SIGN_UP = "sign_up"
    const val SIGN_IN = "sign_in"
}

@Composable
fun WordsFactoryNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = WordsFactoryDestinations.INTRO) {
        composable(WordsFactoryDestinations.INTRO) {
            IntroScreen(onNavigateToNext = {
                navController.navigate(WordsFactoryDestinations.SIGN_UP) {
                    popUpTo(WordsFactoryDestinations.INTRO) {
                        inclusive = true
                    }
                }
            })
        }

        composable(WordsFactoryDestinations.SIGN_UP) {
            SignUpScreen(onNavigateToSignIn = { navController.navigate(WordsFactoryDestinations.SIGN_IN) })
        }

        composable(WordsFactoryDestinations.SIGN_IN) {
            SignInScreen()
        }
    }
}