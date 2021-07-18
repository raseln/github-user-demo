package com.rasel.githubusers.data.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.text.SimpleDateFormat
import java.util.*

@JsonClass(generateAdapter = true)
data class UserDetails(
    val id: Int,
    val name: String,
    val bio: String?,
    @Json(name = "avatar_url")
    val avatarUrl: String,
    val login: String,
    @Json(name = "followers_url")
    val followersUrl: String?,
    @Json(name = "following_url")
    val followingUrl: String?,
    @Json(name = "html_url")
    val htmlUrl: String?,
    @Json(name = "organizations_url")
    val organizationsUrl: String,
    @Json(name = "received_events_url")
    val receivedEventsUrl: String,
    @Json(name = "site_admin")
    val siteAdmin: Boolean,
    @Json(name = "starred_url")
    val starredUrl: String,
    @Json(name = "subscriptions_url")
    val subscriptionsUrl: String,
    val type: String,
    val url: String,
    val company: String?,
    val email: String?,
    @Json(name = "repos_url")
    val reposUrl: String?,
    @Json(name = "events_url")
    val eventsUrl: String?,
    val followers: Int?,
    val following: Int?,
    @Json(name = "gists_url")
    val gistsUrl: String?,
    val location: String?,
    @Json(name = "owned_private_repos")
    val ownedPrivateRepos: Int?,
    @Json(name = "private_gists")
    val privateGists: Int?,
    @Json(name = "public_gists")
    val publicGists: Int?,
    @Json(name = "public_repos")
    val publicRepos: Int?,
    @Json(name = "total_private_repos")
    val totalPrivateRepos: Int?,
    @Json(name = "twitter_username")
    val twitterUsername: String?,
    @Json(name = "created_at")
    val createdAt: String,
    @Json(name = "updated_at")
    val updatedAt: String
) {
    val formattedCreatedDate: String
        get() {
            val date = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault()).parse(createdAt) ?: Date()
            return SimpleDateFormat("d MMMM, yyyy", Locale.getDefault()).format(date)
        }

    val formattedUpdatedDate: String
        get() {
            val date = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault()).parse(updatedAt) ?: Date()
            return SimpleDateFormat("d MMMM, yyyy (hh:mm a)", Locale.getDefault()).format(date)
        }
}