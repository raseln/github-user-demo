package com.rasel.githubusers.data.pagingsources

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.rasel.githubusers.data.api.GithubApiService
import com.rasel.githubusers.data.models.GithubUser
import com.rasel.githubusers.data.repositories.GithubRepository
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

private const val GITHUB_STARTING_PAGE_INDEX = 1

class GithubPagingSource @Inject constructor(
    private val service: GithubApiService
) : PagingSource<Int, GithubUser>() {

    override fun getRefreshKey(state: PagingState<Int, GithubUser>): Int? {
        return state.anchorPosition?.let { anchorPosition->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GithubUser> {
        val position = params.key ?: GITHUB_STARTING_PAGE_INDEX
        return try {
            val response = service.getUsers(position, params.loadSize)
            val nextKey = if (response.isEmpty()) {
                null
            } else {
                position + (params.loadSize / GithubRepository.NETWORK_PAGE_SIZE)
            }
            LoadResult.Page(
                data = response,
                prevKey = if (position == GITHUB_STARTING_PAGE_INDEX) null else position - 1,
                nextKey = nextKey
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }
}