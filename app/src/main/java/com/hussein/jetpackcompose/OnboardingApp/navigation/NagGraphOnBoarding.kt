package com.hussein.jetpackcompose.OnboardingApp.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.google.accompanist.pager.ExperimentalPagerApi
import com.hussein.jetpackcompose.OnboardingApp.screen.HomeScreenOnBoarding
import com.hussein.jetpackcompose.OnboardingApp.screen.WelcomeScreenOnBoarding

@ExperimentalAnimationApi
@ExperimentalPagerApi
@Composable
fun SetupNavGraphOnboarding(
    navController: NavHostController,
    startDestination: String
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(route = ScreenOnBoarding.Welcome.route) {
            WelcomeScreenOnBoarding(navController = navController)
        }
        composable(route = ScreenOnBoarding.Home.route) {
            HomeScreenOnBoarding()
        }
    }
}