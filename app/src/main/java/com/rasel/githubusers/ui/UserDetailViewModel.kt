package com.rasel.githubusers.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rasel.githubusers.data.models.UserDetails
import com.rasel.githubusers.data.repositories.GithubRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserDetailViewModel @Inject constructor(private val repository: GithubRepository) :
    ViewModel() {

    val error: MutableLiveData<String?> = repository.errorLiveData
    val userDetailsLiveData: LiveData<UserDetails?> = repository.userDetailsLiveData

    fun getUserDetails(userName: String) {
        viewModelScope.launch {
            repository.getUserDetails(userName)
        }
    }
}