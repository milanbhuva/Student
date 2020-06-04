package com.kiyansoftech.student.model.MyCourses


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("course_description")
    var courseDescription: String? = null,
    @SerializedName("course_duration")
    var courseDuration: String? = null,
    @SerializedName("course_id")
    var courseId: String? = null,
    @SerializedName("course_name")
    var courseName: String? = null,
    @SerializedName("likes")
    var likes: String? = null,
    @SerializedName("reviews")
    var reviews: String? = null
)