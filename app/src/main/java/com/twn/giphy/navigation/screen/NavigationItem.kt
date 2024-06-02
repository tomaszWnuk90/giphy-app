package com.twn.giphy.navigation.screen

sealed class NavigationItem(val route: String) {
    data object Main : NavigationItem(Screen.MAIN.name)
    data object Details : NavigationItem(Screen.DETAILS.name)
}