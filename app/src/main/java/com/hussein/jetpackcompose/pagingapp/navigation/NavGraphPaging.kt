package com.hussein.jetpackcompose.pagingapp.navigation
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.paging.ExperimentalPagingApi
import coil.annotation.ExperimentalCoilApi
import com.hussein.jetpackcompose.Screen
import com.hussein.jetpackcompose.pagingapp.screens.home.HomePagingScreen
import com.hussein.jetpackcompose.pagingapp.screens.search.SearchScreenPaging

@ExperimentalPagingApi
@ExperimentalCoilApi
@Composable
fun NavGraphPaging(navHostController: NavHostController){
    NavHost(navController= navHostController, startDestination= Screen.Home.route){
        composable(
            route= ScreenPaging.Home.route
        ){
            HomePagingScreen(navController = navHostController)
        }
        composable(
            route= ScreenPaging.Search.route
        ){
            SearchScreenPaging(navHostController)
        }
    }
}

@Composable
@Preview
fun NavGraphPagingPreview(){

}