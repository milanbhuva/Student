package com.kiyansoftech.student.model.ForgotPwd


import com.google.gson.annotations.SerializedName

data class ForgotPassword(
    @SerializedName("message")
    var message: String? = null,
    @SerializedName("status")
    var status: Int? = null
)