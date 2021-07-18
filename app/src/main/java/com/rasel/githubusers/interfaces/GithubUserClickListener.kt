package com.rasel.githubusers.interfaces

import com.rasel.githubusers.data.models.GithubUser

interface GithubUserClickListener {
    fun onUserClick(user: GithubUser)
}