package com.yhn.githubclient.data.model

import com.google.gson.annotations.SerializedName

data class Repository(

	@SerializedName("tags_url")
	val tagsUrl: String? = null,

	@SerializedName("private")
	val jsonMemberPrivate: Boolean? = null,

	@SerializedName("contributors_url")
	val contributorsUrl: String? = null,

	@SerializedName("notifications_url")
	val notificationsUrl: String? = null,

	@SerializedName("description")
	val description: String? = null,

	@SerializedName("subscription_url")
	val subscriptionUrl: String? = null,

	@SerializedName("keys_url")
	val keysUrl: String? = null,

	@SerializedName("branches_url")
	val branchesUrl: String? = null,

	@SerializedName("deployments_url")
	val deploymentsUrl: String? = null,

	@SerializedName("issue_comment_url")
	val issueCommentUrl: String? = null,

	@SerializedName("labels_url")
	val labelsUrl: String? = null,

	@SerializedName("subscribers_url")
	val subscribersUrl: String? = null,

	@SerializedName("releases_url")
	val releasesUrl: String? = null,

	@SerializedName("comments_url")
	val commentsUrl: String? = null,

	@SerializedName("stargazers_url")
	val stargazersUrl: String? = null,

	@SerializedName("id")
	val id: Int? = null,

	@SerializedName("owner")
	val owner: Owner? = null,

	@SerializedName("archive_url")
	val archiveUrl: String? = null,

	@SerializedName("commits_url")
	val commitsUrl: String? = null,

	@SerializedName("git_refs_url")
	val gitRefsUrl: String? = null,

	@SerializedName("forks_url")
	val forksUrl: String? = null,

	@SerializedName("compare_url")
	val compareUrl: String? = null,

	@SerializedName("statuses_url")
	val statusesUrl: String? = null,

	@SerializedName("git_commits_url")
	val gitCommitsUrl: String? = null,

	@SerializedName("blobs_url")
	val blobsUrl: String? = null,

	@SerializedName("git_tags_url")
	val gitTagsUrl: String? = null,

	@SerializedName("merges_url")
	val mergesUrl: String? = null,

	@SerializedName("downloads_url")
	val downloadsUrl: String? = null,

	@SerializedName("url")
	val url: String? = null,

	@SerializedName("contents_url")
	val contentsUrl: String? = null,

	@SerializedName("milestones_url")
	val milestonesUrl: String? = null,

	@SerializedName("teams_url")
	val teamsUrl: String? = null,

	@SerializedName("fork")
	val fork: Boolean? = null,

	@SerializedName("issues_url")
	val issuesUrl: String? = null,

	@SerializedName("full_name")
	val fullName: String? = null,

	@SerializedName("events_url")
	val eventsUrl: String? = null,

	@SerializedName("issue_events_url")
	val issueEventsUrl: String? = null,

	@SerializedName("languages_url")
	val languagesUrl: String? = null,

	@SerializedName("html_url")
	val htmlUrl: String? = null,

	@SerializedName("collaborators_url")
	val collaboratorsUrl: String? = null,

	@SerializedName("name")
	val name: String? = null,

	@SerializedName("pulls_url")
	val pullsUrl: String? = null,

	@SerializedName("hooks_url")
	val hooksUrl: String? = null,

	@SerializedName("assignees_url")
	val assigneesUrl: String? = null,

	@SerializedName("trees_url")
	val treesUrl: String? = null,

	@SerializedName("node_id")
	val nodeId: String? = null
)