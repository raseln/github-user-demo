package com.rasel.githubusers.ui.viewholders

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.rasel.githubusers.R
import com.rasel.githubusers.data.models.GithubUser
import com.rasel.githubusers.databinding.LayoutUserCellBinding
import com.rasel.githubusers.interfaces.GithubUserClickListener

class UserViewHolder(private val binding: LayoutUserCellBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(user: GithubUser, listener: GithubUserClickListener?) {
        binding.user = user
        binding.root.setOnClickListener {
            listener?.onUserClick(user)
        }
        binding.executePendingBindings()
    }

    companion object {
        fun create(parent: ViewGroup): UserViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = DataBindingUtil.inflate<LayoutUserCellBinding>(
                inflater,
                R.layout.layout_user_cell,
                parent,
                false
            )

            return UserViewHolder(binding)
        }
    }
}