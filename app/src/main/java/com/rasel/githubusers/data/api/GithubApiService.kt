package com.rasel.githubusers.data.api

import com.rasel.githubusers.data.models.GithubUser
import com.rasel.githubusers.data.models.UserDetails
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubApiService {

    @GET("users")
    suspend fun getUsers(
        @Query("page") pageNo: Int,
        @Query("per_page") pageSize: Int
    ) : List<GithubUser>

    @GET("users/{username}")
    suspend fun getUserDetails(
        @Path("username") userName: String
    ) : Response<UserDetails>
}