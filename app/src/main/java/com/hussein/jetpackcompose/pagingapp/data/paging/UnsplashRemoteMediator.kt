package com.hussein.jetpackcompose.pagingapp.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.hussein.jetpackcompose.pagingapp.data.local.UnsplashDatabase
import com.hussein.jetpackcompose.pagingapp.data.local.dao.UnsplashImageDao
import com.hussein.jetpackcompose.pagingapp.data.remote.UnsplashAPI
import com.hussein.jetpackcompose.pagingapp.model.UnsplashImage
import com.hussein.jetpackcompose.pagingapp.model.UnsplashRemoteKey
import com.hussein.jetpackcompose.pagingapp.util.Constant
import javax.inject.Inject

@ExperimentalPagingApi
class UnsplashRemoteMediator @Inject constructor(
    private val unsplashAPI: UnsplashAPI,
    private val unsplashDataBase: UnsplashDatabase
    ):RemoteMediator<Int, UnsplashImage>(){

    private val unsplashImageDao = unsplashDataBase.unsplashImageDao()
    private val unsplashRemoteKeyDao = unsplashDataBase.unsplashRemoteKeyDao()


    override suspend fun load(loadType: LoadType, state: PagingState<Int, UnsplashImage>): MediatorResult {
        return try {
            val currentPage= when(loadType){
                LoadType.REFRESH -> {
                    val remoteKeys= getRemoteKeyClosestToCurrentPosition(state)
                    remoteKeys?.nextPage?.minus(1) ?: 1
                }
                LoadType.PREPEND -> {
                    val remoteKeys= getRemoteKeyForFirstItem(state)
                    val prev=remoteKeys?.prevPage?:
                    return MediatorResult.Success (endOfPaginationReached = remoteKeys !=null)
                    prev
                }
                LoadType.APPEND -> {
                    val remoteKeys= getRemoteKeyForLastItem(state)
                    val next=remoteKeys?.nextPage?:
                    return MediatorResult.Success (endOfPaginationReached = remoteKeys !=null)
                    next
                }
            }

            val response= unsplashAPI.getAllImages(page = currentPage, perPage = Constant.ITEM_PER_PAGE)
            val endReachPagination= response.isEmpty()

            val prevPage = if(currentPage == 1) null else currentPage-1
            val nextPage = if(endReachPagination) null else currentPage+1
            unsplashDataBase.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    unsplashImageDao.deleteAllImages()
                    unsplashRemoteKeyDao.deleteKeys()
                }
                val keys = response.map { unsplashImage ->
                    UnsplashRemoteKey(
                        id = unsplashImage.id,
                        prevPage = prevPage,
                        nextPage = nextPage
                    )
                }
                unsplashRemoteKeyDao.addRemoteKeys(remoteKeys = keys)
                unsplashImageDao.addImages(images = response)
            }
            MediatorResult.Success(endReachPagination)

        }
        catch (e:Exception){
            return MediatorResult.Error(e)
        }
    }
    private suspend fun getRemoteKeyClosestToCurrentPosition(state: PagingState<Int, UnsplashImage>) : UnsplashRemoteKey?{
        return state.anchorPosition?.let { position->

            state.closestItemToPosition(position)?.id?.let {id->
                unsplashRemoteKeyDao.getRemoteKeys(id= id)
            }
        }
    }

    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, UnsplashImage>) : UnsplashRemoteKey?{
        return state.pages.firstOrNull() {it.data.isEmpty()}?.data?.firstOrNull()?.let { unsplashImage ->
            unsplashRemoteKeyDao.getRemoteKeys(id= unsplashImage.id)
        }
    }

    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, UnsplashImage>) : UnsplashRemoteKey?{
        return state.pages.lastOrNull() {it.data.isEmpty()}?.data?.lastOrNull()?.let { unsplashImage ->
            unsplashRemoteKeyDao.getRemoteKeys(id= unsplashImage.id)
        }
    }

}