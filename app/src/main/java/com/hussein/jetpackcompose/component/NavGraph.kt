package com.hussein.jetpackcompose.component

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.hussein.jetpackcompose.DETAIL_ARGUMENT_ID
import com.hussein.jetpackcompose.DETAIL_ARGUMENT_NAME
import com.hussein.jetpackcompose.Screen

@Composable
fun SetupNavGraph(navHostController: NavHostController){
    NavHost(navController= navHostController, startDestination= Screen.Home.route){
        composable(
            route=Screen.Home.route
        ){
            HomeScreen(navHostController)
        }
        composable(
            route=Screen.Detail.route,
            arguments = listOf(
                navArgument(DETAIL_ARGUMENT_ID){
                type = NavType.IntType
                defaultValue= 0
                                               },
//                navArgument(DETAIL_ARGUMENT_NAME){
//                    type = NavType.StringType }
            )
        ){
            Log.d("Args",it.arguments?.getInt(DETAIL_ARGUMENT_ID).toString())
            Log.d("Args",it.arguments?.getString(DETAIL_ARGUMENT_NAME).toString())
            DetailScreen(navHostController)
        }
    }
}

@Composable
@Preview
fun SetupNavGraphPreview(){

}