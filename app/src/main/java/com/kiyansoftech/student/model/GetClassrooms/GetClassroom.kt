package com.kiyansoftech.student.model.GetClassrooms


import com.google.gson.annotations.SerializedName

data class GetClassroom(
    @SerializedName("Data")
    var `data`: List<Data?>? = null,
    @SerializedName("message")
    var message: String? = null,
    @SerializedName("status")
    var status: Int? = null
)