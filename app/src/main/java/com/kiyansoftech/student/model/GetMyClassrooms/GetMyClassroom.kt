package com.kiyansoftech.student.model.GetMyClassrooms


import com.google.gson.annotations.SerializedName

data class GetMyClassroom(
    @SerializedName("data")
    var `data`: List<Data?>? = null,
    @SerializedName("message")
    var message: String? = null,
    @SerializedName("status")
    var status: Int? = null
)