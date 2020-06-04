package com.kiyansoftech.student.model.GetMyTutors


import com.google.gson.annotations.SerializedName

data class GetMyTutor(
    @SerializedName("data")
    var `data`: List<Data?>? = null,
    @SerializedName("message")
    var message: String? = null,
    @SerializedName("status")
    var status: Int? = null
)