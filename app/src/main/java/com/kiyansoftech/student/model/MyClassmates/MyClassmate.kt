package com.kiyansoftech.student.model.MyClassmates


import com.google.gson.annotations.SerializedName

data class MyClassmate(
    @SerializedName("data")
    var `data`: List<Data>? = null,
    @SerializedName("message")
    var message: String? = null,
    @SerializedName("status")
    var status: Int? = null
)