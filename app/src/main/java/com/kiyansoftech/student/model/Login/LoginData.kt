package com.kiyansoftech.student.model.Login


import com.google.gson.annotations.SerializedName

data class LoginData(
    @SerializedName("create_on")
    var createOn: String? = null,
    @SerializedName("email")
    var email: String? = null,
    @SerializedName("fcm_token")
    var fcmToken: String? = null,
    @SerializedName("ip_address")
    var ipAddress: String? = null,
    @SerializedName("isActive")
    var isActive: String? = null,
    @SerializedName("isDelete")
    var isDelete: String? = null,
    @SerializedName("name")
    var name: String? = null,
    @SerializedName("password")
    var password: String? = null,
    @SerializedName("phone")
    var phone: String? = null,
    @SerializedName("update_on")
    var updateOn: String? = null,
    @SerializedName("user_id")
    var userId: String? = null,
    @SerializedName("Usertype")
    var usertype: String? = null
)