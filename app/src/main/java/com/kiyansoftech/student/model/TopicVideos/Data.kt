package com.kiyansoftech.student.model.TopicVideos


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("id")
    var id: String? = null,
    @SerializedName("isActive")
    var isActive: String? = null,
    @SerializedName("thumbnail_image")
    var thumbnailImage: String? = null,
    @SerializedName("topic_id")
    var topicId: String? = null,
    @SerializedName("video_description")
    var videoDescription: String? = null,
    @SerializedName("video_duration")
    var videoDuration: String? = null,
    @SerializedName("video_likes")
    var videoLikes: String? = null,
    @SerializedName("video_link")
    var videoLink: String? = null,
    @SerializedName("video_name")
    var videoName: String? = null,
    @SerializedName("video_reviews")
    var videoReviews: String? = null
)