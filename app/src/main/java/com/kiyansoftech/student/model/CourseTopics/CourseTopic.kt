package com.kiyansoftech.student.model.CourseTopics


import com.google.gson.annotations.SerializedName

data class CourseTopic(
    @SerializedName("data")
    var `data`: List<Data?>? = null,
    @SerializedName("message")
    var message: String? = null,
    @SerializedName("status")
    var status: Int? = null
)