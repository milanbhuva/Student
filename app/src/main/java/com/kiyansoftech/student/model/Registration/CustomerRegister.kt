package com.kiyansoftech.student.model.Registration


import com.google.gson.annotations.SerializedName

data class CustomerRegister(
    @SerializedName("message")
    var message: String? = null,
    @SerializedName("status")
    var status: Int? = null
)