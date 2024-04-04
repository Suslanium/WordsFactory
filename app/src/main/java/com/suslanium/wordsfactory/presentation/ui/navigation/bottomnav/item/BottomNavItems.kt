package com.suslanium.wordsfactory.presentation.ui.navigation.bottomnav.item

import com.suslanium.wordsfactory.R
import com.suslanium.wordsfactory.presentation.ui.navigation.bottomnav.BottomNavigationDestinations

object BottomNavItems {
    val bottomNavItems = listOf(
        BottomNavItem(
            labelResId = R.string.dictionary,
            route = BottomNavigationDestinations.DICTIONARY,
            iconResId = R.drawable.dictionary_icon
        ),
        BottomNavItem(
            labelResId = R.string.training,
            route = BottomNavigationDestinations.TRAINING,
            iconResId = R.drawable.training_icon
        ),
        BottomNavItem(
            labelResId = R.string.video,
            route = BottomNavigationDestinations.VIDEO,
            iconResId = R.drawable.video_icon
        )
    )
}