package com.hussein.jetpackcompose.pagingapp.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.ExperimentalPagingApi
import androidx.paging.compose.collectAsLazyPagingItems
import coil.annotation.ExperimentalCoilApi
import com.hussein.jetpackcompose.pagingapp.navigation.ScreenPaging
import com.hussein.jetpackcompose.pagingapp.screens.common.ListContent
import com.hussein.jetpackcompose.pagingapp.viewmodel.HomeViewModel

@ExperimentalPagingApi
@ExperimentalCoilApi
@Composable
fun HomePagingScreen(navController: NavHostController,
                        homeViewModel: HomeViewModel = hiltViewModel()) {
    val getAllImages = homeViewModel.getAllImages.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            HomeTopBar(
                onSearchClicked = {
                    navController.navigate(ScreenPaging.Search.route)
                }
            )
        },
        content = {padding ->
            Column(
                modifier = Modifier
                    .padding(padding)){
                ListContent(items = getAllImages)
            }
        }
    )
}