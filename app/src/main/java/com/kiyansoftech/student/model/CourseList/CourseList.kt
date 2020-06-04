package com.kiyansoftech.student.model.CourseList


import com.google.gson.annotations.SerializedName

data class CourseList(
    @SerializedName("Data")
    var `data`: List<Data?>? = null,
    @SerializedName("message")
    var message: String? = null,
    @SerializedName("status")
    var status: Int? = null
)