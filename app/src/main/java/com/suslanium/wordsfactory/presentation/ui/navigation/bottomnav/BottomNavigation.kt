package com.suslanium.wordsfactory.presentation.ui.navigation.bottomnav

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.suslanium.wordsfactory.presentation.ui.navigation.WordsFactoryDestinations
import com.suslanium.wordsfactory.presentation.ui.screen.dictionary.DictionaryScreen
import com.suslanium.wordsfactory.presentation.ui.screen.training.TrainingScreen

object BottomNavigationDestinations {
    const val DICTIONARY = "dictionary"
    const val TRAINING = "training"
    const val VIDEO = "video"
}

@Composable
fun BottomNavigation(bottomNavController: NavHostController, rootNavController: NavHostController) {
    NavHost(
        navController = bottomNavController,
        startDestination = BottomNavigationDestinations.DICTIONARY
    ) {
        composable(BottomNavigationDestinations.DICTIONARY) {
            DictionaryScreen()
        }

        composable(BottomNavigationDestinations.TRAINING) {
            TrainingScreen(onNavigateToQuestions = {
                rootNavController.navigate(
                    WordsFactoryDestinations.TEST
                )
            })
        }

        composable(BottomNavigationDestinations.VIDEO) {

        }
    }
}