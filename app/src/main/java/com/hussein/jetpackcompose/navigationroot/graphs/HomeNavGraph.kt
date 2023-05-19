package com.hussein.jetpackcompose.navigationroot.graphs

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.hussein.jetpackcompose.navigationroot.BottomBarRootNavigationScreen
import com.hussein.jetpackcompose.navigationroot.screens.ScreenContent

@Composable
fun HomeNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = Graph.HOME,
        startDestination = BottomBarRootNavigationScreen.Home.route
    ) {
        composable(route = BottomBarRootNavigationScreen.Home.route) {
            ScreenContent(
                name = BottomBarRootNavigationScreen.Home.route,
                onClick = {
                    navController.navigate(Graph.DETAILS)
                }
            )
        }
        composable(route = BottomBarRootNavigationScreen.Profile.route) {
            ScreenContent(
                name = BottomBarRootNavigationScreen.Profile.route,
                onClick = { }
            )
        }
        composable(route = BottomBarRootNavigationScreen.Settings.route) {
            ScreenContent(
                name = BottomBarRootNavigationScreen.Settings.route,
                onClick = { }
            )
        }
        detailsNavGraph(navController = navController)
    }
}

fun NavGraphBuilder.detailsNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.DETAILS,
        startDestination = DetailsScreen.Information.route
    ) {
        composable(route = DetailsScreen.Information.route) {
            ScreenContent(name = DetailsScreen.Information.route) {
                navController.navigate(DetailsScreen.Overview.route)
            }
        }
        composable(route = DetailsScreen.Overview.route) {
            ScreenContent(name = DetailsScreen.Overview.route) {
                navController.popBackStack(
                    route = DetailsScreen.Information.route,
                    inclusive = false
                )
            }
        }
    }
}

sealed class DetailsScreen(val route: String) {
    object Information : DetailsScreen(route = "INFORMATION")
    object Overview : DetailsScreen(route = "OVERVIEW")
}