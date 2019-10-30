package com.yhn.githubclient.data.model

import com.google.gson.annotations.SerializedName

data class RepoItem(

	@SerializedName("path")
	val path: String? = null,

	@SerializedName("score")
	val score: Double? = null,

	@SerializedName("html_url")
	val htmlUrl: String? = null,

	@SerializedName("name")
	val name: String? = null,

	@SerializedName("git_url")
	val gitUrl: String? = null,

	@SerializedName("repository")
	val repository: Repository? = null,

	@SerializedName("sha")
	val sha: String? = null,

	@SerializedName("url")
	val url: String? = null
)