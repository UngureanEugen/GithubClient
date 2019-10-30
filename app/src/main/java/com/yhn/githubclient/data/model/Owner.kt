package com.yhn.githubclient.data.model


import com.google.gson.annotations.SerializedName

data class Owner(

    @SerializedName("received_events_url")
    val receivedEventsUrl: String? = null,

    @SerializedName("avatar_url")
    val avatarUrl: String? = null,

    @SerializedName("id")
    val id: Int? = null,

    @SerializedName("login")
    val login: String? = null,

    @SerializedName("type")
    val type: String? = null,

    @SerializedName("gravatar_id")
    val gravatarId: String? = null,

    @SerializedName("url")
    val url: String? = null,

    @SerializedName("node_id")
    val nodeId: String? = null
)