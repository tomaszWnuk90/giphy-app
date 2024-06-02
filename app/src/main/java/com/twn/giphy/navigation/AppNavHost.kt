package com.twn.giphy.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigation
import com.twn.giphy.navigation.screen.NavigationItem
import com.twn.giphy.ui.view.details.DetailsScreen
import com.twn.giphy.ui.view.mainPage.MainScreen
import com.twn.giphy.ui.viewModel.SharedViewModel

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = NavigationItem.Main.route,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = "main"
    ) {
        navigation(
            startDestination = startDestination,
            route = "main"
        ) {

            composable(NavigationItem.Main.route) { entry ->
                val viewModel =
                    entry.sharedViewModel<SharedViewModel>(navController = navController)
                MainScreen(navController, viewModel)
            }
            composable(NavigationItem.Details.route) { entry ->
                val viewModel =
                    entry.sharedViewModel<SharedViewModel>(navController = navController)
                DetailsScreen(viewModel)
            }

        }

    }
}