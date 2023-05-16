package com.hussein.jetpackcompose.pagingapp.screens.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.ExperimentalPagingApi
import androidx.paging.compose.collectAsLazyPagingItems
import coil.annotation.ExperimentalCoilApi
import com.hussein.jetpackcompose.pagingapp.screens.common.ListContent
import com.hussein.jetpackcompose.pagingapp.viewmodel.SearchViewModel

@ExperimentalPagingApi
@ExperimentalCoilApi
@Composable
fun SearchScreenPaging(
    navController: NavHostController,
    searchViewModel: SearchViewModel = hiltViewModel()
) {
    val searchQuery by searchViewModel.searchQuery
    val searchedImages = searchViewModel.searchedImages.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            SearchWidget(
                text = searchQuery,
                onTextChange = {
                    searchViewModel.updateSearchQuery(query = it)
                },
                onSearchClicked = {
                    searchViewModel.searchHeroes(query = it)
                },
                onCloseClicked = {
                    navController.popBackStack()
                }
            )
        },
        content = {padding ->
            Column(
                modifier = androidx.compose.ui.Modifier
                    .padding(padding)){
                ListContent(items = searchedImages)
            }
        }
    )
}