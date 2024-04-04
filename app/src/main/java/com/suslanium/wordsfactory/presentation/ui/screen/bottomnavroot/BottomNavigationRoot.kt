package com.suslanium.wordsfactory.presentation.ui.screen.bottomnavroot

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.suslanium.wordsfactory.presentation.ui.navigation.bottomnav.BottomNavigation
import com.suslanium.wordsfactory.presentation.ui.screen.bottomnavroot.components.BottomNavigationBar

@Composable
fun BottomNavigationRoot(rootNavController: NavHostController) {
    val navController = rememberNavController()

    Scaffold(modifier = Modifier.fillMaxSize(), bottomBar = {
        BottomNavigationBar(navHostController = navController)
    }) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .padding(it)
        ) {
            BottomNavigation(
                bottomNavController = navController, rootNavController = rootNavController
            )
        }
    }
}