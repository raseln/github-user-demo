package com.rasel.githubusers.ui

import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.rasel.githubusers.data.models.GithubUser
import com.rasel.githubusers.data.repositories.GithubRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: GithubRepository): ViewModel() {

    fun getGithubUsers() : Flow<PagingData<GithubUser>> {
        return repository.getUsers()
    }
}