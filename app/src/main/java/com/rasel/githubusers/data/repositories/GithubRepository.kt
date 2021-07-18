package com.rasel.githubusers.data.repositories

import androidx.lifecycle.MutableLiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.rasel.githubusers.data.api.GithubApiService
import com.rasel.githubusers.data.models.GithubUser
import com.rasel.githubusers.data.models.UserDetails
import com.rasel.githubusers.data.pagingsources.GithubPagingSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GithubRepository @Inject constructor( private val service: GithubApiService) {

    val errorLiveData: MutableLiveData<String?> = MutableLiveData()
    val userDetailsLiveData = MutableLiveData<UserDetails?>()

    fun getUsers() : Flow<PagingData<GithubUser>> {
        val pagingConfig = PagingConfig(
            pageSize = NETWORK_PAGE_SIZE,
            enablePlaceholders = false
        )

        return Pager(
            config = pagingConfig,
            pagingSourceFactory = {
                GithubPagingSource(service)
            }
        ).flow
    }

    suspend fun getUserDetails(userName: String) {
        try {
            val response = service.getUserDetails(userName)
            if (response.isSuccessful) {
                userDetailsLiveData.postValue(response.body())
                errorLiveData.postValue(null)
            } else {
                userDetailsLiveData.postValue(null)
                errorLiveData.postValue(response.errorBody().toString())
            }
        } catch (exception: Exception) {
            userDetailsLiveData.postValue(null)
            errorLiveData.postValue(exception.message)
        }
    }

    companion object {
        const val NETWORK_PAGE_SIZE = 30
    }
}