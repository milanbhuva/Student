package com.kiyansoftech.student.model.ChangePwd


import com.google.gson.annotations.SerializedName

data class ChangePassword(
    @SerializedName("message")
    var message: String? = null,
    @SerializedName("status")
    var status: Int? = null
)