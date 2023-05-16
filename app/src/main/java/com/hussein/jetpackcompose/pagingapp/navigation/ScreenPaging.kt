package com.hussein.jetpackcompose.pagingapp.navigation

sealed class ScreenPaging(val route:String) {
    object Home: ScreenPaging("home_screen")
    object Search: ScreenPaging("search_screen")
}