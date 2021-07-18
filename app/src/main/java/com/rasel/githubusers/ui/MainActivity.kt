package com.rasel.githubusers.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.rasel.githubusers.data.models.GithubUser
import com.rasel.githubusers.databinding.ActivityMainBinding
import com.rasel.githubusers.interfaces.GithubUserClickListener
import com.rasel.githubusers.ui.adapters.UsersAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()
    private lateinit var adapter: UsersAdapter
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerview()

        getUsers()
    }

    private fun setupRecyclerview() {
        adapter = UsersAdapter()
        adapter.listener = object : GithubUserClickListener {
            override fun onUserClick(user: GithubUser) {
                val intent = Intent(this@MainActivity, UserDetailActivity::class.java)
                intent.putExtra(UserDetailActivity.USER_NAME, user.login)

                startActivity(intent)
            }
        }
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.adapter = adapter
    }

    private fun getUsers() {
        lifecycleScope.launch {
            viewModel.getGithubUsers().collectLatest {
                adapter.submitData(it)
            }
        }
    }
}