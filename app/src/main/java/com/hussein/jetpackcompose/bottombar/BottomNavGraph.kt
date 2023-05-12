package com.hussein.jetpackcompose.bottombar

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun BottomNavGraph(navController: NavHostController, innerPadding: PaddingValues= PaddingValues(5.dp)){
    NavHost(navController = navController, startDestination = BottomBarScreen.Home.route, modifier = Modifier.padding(innerPadding)){
        composable(route = BottomBarScreen.Home.route){
            Home()
        }
        composable(route = BottomBarScreen.Profile.route){
            Profile()
        }
        composable(route = BottomBarScreen.Settings.route){
            Settings()
        }
    }
}