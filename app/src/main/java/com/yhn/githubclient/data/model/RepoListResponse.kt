package com.yhn.githubclient.data.model

import com.google.gson.annotations.SerializedName

data class RepoListResponse(

    @SerializedName("total_count")
    val totalCount: Int? = null,

    @SerializedName("incomplete_results")
    val incompleteResults: Boolean? = null,

    @SerializedName("items")
    val items: List<RepoItem?>? = null
)