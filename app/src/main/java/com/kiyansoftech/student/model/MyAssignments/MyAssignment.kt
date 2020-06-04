package com.kiyansoftech.student.model.MyAssignments


import com.google.gson.annotations.SerializedName

data class MyAssignment(
    @SerializedName("data")
    var `data`: List<Any?>? = null,
    @SerializedName("message")
    var message: String? = null,
    @SerializedName("status")
    var status: Int? = null
)