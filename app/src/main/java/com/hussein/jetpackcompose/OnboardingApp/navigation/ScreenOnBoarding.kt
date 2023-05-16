package com.hussein.jetpackcompose.OnboardingApp.navigation

sealed class ScreenOnBoarding(val route:String) {
    object Welcome: ScreenOnBoarding("welcome_screen")
    object Home: ScreenOnBoarding("home_screen")
}