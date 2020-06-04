package com.kiyansoftech.student.model.TopicVideos


import com.google.gson.annotations.SerializedName

data class TopicVideo(
    @SerializedName("data")
    var `data`: List<Data?>? = null,
    @SerializedName("message")
    var message: String? = null,
    @SerializedName("status")
    var status: Int? = null
)