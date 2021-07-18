package com.rasel.githubusers.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.rasel.githubusers.databinding.ActivityDetailBinding
import com.rasel.githubusers.interfaces.OnMoreClickListener
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class UserDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private val viewModel: UserDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.handler = object : OnMoreClickListener {
            override fun onSeeMoreClick(url: String?) {
                url?.let {
                    val intent = Intent(Intent.ACTION_VIEW)
                    intent.data = Uri.parse(it)
                    startActivity(intent)
                }
            }
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val userName = intent?.getStringExtra(USER_NAME) ?: ""
        if (userName.isEmpty()) {
            viewModel.error.value = "Something went wrong. User details cannot be displayed!"
        } else {
            viewModel.getUserDetails(userName)
            viewModel.error.value = null
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    companion object {
        const val USER_NAME: String = "user_name"
    }
}