package com.kiyansoftech.student.model.BuyClassrooms


import com.google.gson.annotations.SerializedName

data class BuyClassroom(
    @SerializedName("message")
    var message: String? = null,
    @SerializedName("status")
    var status: Int? = null
)