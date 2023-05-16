package com.hussein.jetpackcompose.pagingapp.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.hussein.jetpackcompose.pagingapp.data.local.UnsplashDatabase
import com.hussein.jetpackcompose.pagingapp.data.paging.SearchPagingSource
import com.hussein.jetpackcompose.pagingapp.data.paging.UnsplashRemoteMediator
import com.hussein.jetpackcompose.pagingapp.data.remote.UnsplashAPI
import com.hussein.jetpackcompose.pagingapp.model.UnsplashImage
import com.hussein.jetpackcompose.pagingapp.util.Constant
import com.hussein.jetpackcompose.pagingapp.util.Constant.ITEMS_PER_PAGE
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ExperimentalPagingApi
class RepositoryPaging @Inject constructor(
    private val unsplashAPI: UnsplashAPI,
    private val unsplashDataBase: UnsplashDatabase
) {
    fun getAllImages(): Flow<PagingData<UnsplashImage>>{
        val pagingSourceFactory= {unsplashDataBase.unsplashImageDao().getAllImages()}
        return Pager(
            config = PagingConfig(pageSize = Constant.ITEM_PER_PAGE),
            remoteMediator = UnsplashRemoteMediator(unsplashAPI,unsplashDataBase),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }
    fun searchImages(query: String): Flow<PagingData<UnsplashImage>> {
        return Pager(
            config = PagingConfig(pageSize = ITEMS_PER_PAGE),
            pagingSourceFactory = {
                SearchPagingSource(unsplashApi = unsplashAPI, query = query)
            }
        ).flow
    }

}