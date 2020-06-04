package com.kiyansoftech.student.model.GetMyClassrooms


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("class_room_id")
    var classRoomId: String? = null,
    @SerializedName("discounted_price")
    var discountedPrice: String? = null,
    @SerializedName("id")
    var id: String? = null,
    @SerializedName("isActive")
    var isActive: String? = null,
    @SerializedName("price")
    var price: String? = null,
    @SerializedName("secreat_key")
    var secreatKey: String? = null,
    @SerializedName("title")
    var title: String? = null,
    @SerializedName("user_id")
    var userId: String? = null
)