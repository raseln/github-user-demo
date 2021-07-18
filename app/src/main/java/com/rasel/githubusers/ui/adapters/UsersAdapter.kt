package com.rasel.githubusers.ui.adapters

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.rasel.githubusers.data.models.GithubUser
import com.rasel.githubusers.interfaces.GithubUserClickListener
import com.rasel.githubusers.ui.viewholders.UserViewHolder

class UsersAdapter : PagingDataAdapter<GithubUser, UserViewHolder>(USER_COMPARATOR) {

    var listener: GithubUserClickListener? = null

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        getItem(position)?.let { user ->
            holder.bind(user, listener)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder.create(parent)
    }

    companion object {
        private val USER_COMPARATOR = object : DiffUtil.ItemCallback<GithubUser>() {
            override fun areItemsTheSame(oldItem: GithubUser, newItem: GithubUser): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: GithubUser, newItem: GithubUser): Boolean {
                return oldItem == newItem
            }
        }
    }
}