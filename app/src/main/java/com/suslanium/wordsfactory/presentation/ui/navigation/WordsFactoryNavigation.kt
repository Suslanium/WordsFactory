package com.suslanium.wordsfactory.presentation.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.suslanium.wordsfactory.presentation.ui.screen.auth.signin.SignInScreen
import com.suslanium.wordsfactory.presentation.ui.screen.auth.signup.SignUpScreen
import com.suslanium.wordsfactory.presentation.ui.screen.intro.IntroScreen
import com.suslanium.wordsfactory.presentation.ui.screen.splash.SplashScreen

object WordsFactoryDestinations {
    const val SPLASH = "splash"
    const val INTRO = "intro"
    const val SIGN_UP = "sign_up"
    const val SIGN_IN = "sign_in"
    const val BOTTOM_NAVIGATION = "bottom_nav"
}

@Composable
fun WordsFactoryNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = WordsFactoryDestinations.SPLASH) {
        composable(WordsFactoryDestinations.SPLASH) {
            SplashScreen(onNavigateAuthorized = {
                navController.navigate(WordsFactoryDestinations.BOTTOM_NAVIGATION) {
                    popUpTo(WordsFactoryDestinations.SPLASH) {
                        inclusive = true
                    }
                }
            }, onNavigateUnauthorized = {
                navController.navigate(WordsFactoryDestinations.INTRO) {
                    popUpTo(WordsFactoryDestinations.SPLASH) {
                        inclusive = true
                    }
                }
            })
        }

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
            SignUpScreen(onNavigateToSignIn = { navController.navigate(WordsFactoryDestinations.SIGN_IN) },
                onNavigateToNext = {
                    navController.navigate(WordsFactoryDestinations.BOTTOM_NAVIGATION) {
                        popUpTo(WordsFactoryDestinations.SIGN_UP) {
                            inclusive = true
                        }
                    }
                })
        }

        composable(WordsFactoryDestinations.SIGN_IN) {
            SignInScreen(onNavigateToNext = {
                navController.navigate(WordsFactoryDestinations.BOTTOM_NAVIGATION) {
                    popUpTo(WordsFactoryDestinations.SIGN_IN) {
                        inclusive = true
                    }
                }
            })
        }

        composable(WordsFactoryDestinations.BOTTOM_NAVIGATION) {
            BottomNavigationRoot()
        }
    }
}