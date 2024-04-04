package com.suslanium.wordsfactory.presentation.ui.navigation.bottomnav.item

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class BottomNavItem(
    @StringRes val labelResId: Int, val route: String, @DrawableRes val iconResId: Int
)
