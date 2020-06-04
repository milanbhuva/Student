package com.kiyansoftech.student.model.Login


import com.google.gson.annotations.SerializedName

data class StudentLogin(
    @SerializedName("data")
    var `data`: LoginData? = null,
    @SerializedName("message")
    var message: String? = null,
    @SerializedName("status")
    var status: Int? = null
)