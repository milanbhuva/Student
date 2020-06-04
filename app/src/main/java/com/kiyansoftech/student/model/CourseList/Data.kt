package com.kiyansoftech.student.model.CourseList


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("course_description")
    var courseDescription: String? = null,
    @SerializedName("course_duration")
    var courseDuration: String? = null,
    @SerializedName("course_name")
    var courseName: String? = null,
    @SerializedName("create_on")
    var createOn: String? = null,
    @SerializedName("discounted_price")
    var discountedPrice: String? = null,
    @SerializedName("id")
    var id: String? = null,
    @SerializedName("image")
    var image: String? = null,
    @SerializedName("isActive")
    var isActive: String? = null,
    @SerializedName("likes")
    var likes: String? = null,
    @SerializedName("price")
    var price: String? = null,
    @SerializedName("reviews")
    var reviews: String? = null
)