package com.suslanium.wordsfactory.presentation.ui.screen.bottomnavroot.components

import androidx.compose.foundation.border
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.suslanium.wordsfactory.presentation.ui.navigation.bottomnav.item.BottomNavItem
import com.suslanium.wordsfactory.presentation.ui.navigation.bottomnav.item.BottomNavItems
import com.suslanium.wordsfactory.presentation.ui.theme.Gray
import com.suslanium.wordsfactory.presentation.ui.theme.ParagraphMedium
import com.suslanium.wordsfactory.presentation.ui.theme.PrimaryColor

@Composable
fun BottomNavigationBar(
    modifier: Modifier = Modifier,
    buttons: List<BottomNavItem> = BottomNavItems.bottomNavItems,
    navHostController: NavHostController
) {
    val backStackEntry = navHostController.currentBackStackEntryAsState()

    NavigationBar(
        modifier = modifier
            .clip(
                RoundedCornerShape(
                    topStart = 16.dp, topEnd = 16.dp
                )
            )
            .border(
                width = 1.dp,
                shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
                color = Gray
            ), containerColor = Color.White, contentColor = Gray, tonalElevation = 0.dp
    ) {
        buttons.forEach { item ->
            val selected = item.route == backStackEntry.value?.destination?.route

            NavigationBarItem(modifier = Modifier, selected = selected, onClick = {
                if (!selected) navHostController.navigate(item.route)
            }, icon = {
                Icon(
                    imageVector = ImageVector.vectorResource(id = item.iconResId),
                    contentDescription = null
                )
            }, label = {
                Text(
                    text = stringResource(id = item.labelResId),
                    style = ParagraphMedium,
                    textAlign = TextAlign.Center
                )
            }, colors = NavigationBarItemDefaults.colors(
                indicatorColor = Color.White,
                selectedIconColor = PrimaryColor,
                selectedTextColor = PrimaryColor,
                unselectedIconColor = Gray,
                unselectedTextColor = Gray,
                disabledIconColor = Gray,
                disabledTextColor = Gray
            )
            )
        }
    }
}