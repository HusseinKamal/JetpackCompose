package com.hussein.jetpackcompose.pagingapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.paging.ExperimentalPagingApi
import com.hussein.jetpackcompose.pagingapp.data.repository.RepositoryPaging
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@ExperimentalPagingApi
@HiltViewModel
class HomeViewModel @Inject constructor(
    repository: RepositoryPaging
): ViewModel() {
    val getAllImages = repository.getAllImages()
}