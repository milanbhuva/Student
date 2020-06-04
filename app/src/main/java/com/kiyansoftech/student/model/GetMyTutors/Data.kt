package com.kiyansoftech.student.model.GetMyTutors


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("email")
    var email: String? = null,
    @SerializedName("name")
    var name: String? = null,
    @SerializedName("phone")
    var phone: String? = null,
    @SerializedName("tutor_id")
    var tutorId: String? = null
)