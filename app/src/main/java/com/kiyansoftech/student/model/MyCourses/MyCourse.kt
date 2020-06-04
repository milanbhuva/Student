package com.kiyansoftech.student.model.MyCourses


import com.google.gson.annotations.SerializedName

data class MyCourse(
    @SerializedName("data")
    var `data`: List<Data?>? = null,
    @SerializedName("message")
    var message: String? = null,
    @SerializedName("status")
    var status: Int? = null
)