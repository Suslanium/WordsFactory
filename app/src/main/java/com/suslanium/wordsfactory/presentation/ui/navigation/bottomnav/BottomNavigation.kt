package com.suslanium.wordsfactory.presentation.ui.navigation.bottomnav

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

object BottomNavigationDestinations {
    const val DICTIONARY = "dictionary"
    const val TRAINING = "training"
    const val VIDEO = "video"
}

@Composable
fun BottomNavigation(bottomNavController: NavHostController, rootNavController: NavHostController) {
    NavHost(navController = bottomNavController, startDestination = BottomNavigationDestinations.DICTIONARY) {
        composable(BottomNavigationDestinations.DICTIONARY) {

        }

        composable(BottomNavigationDestinations.TRAINING) {

        }

        composable(BottomNavigationDestinations.VIDEO) {

        }
    }
}