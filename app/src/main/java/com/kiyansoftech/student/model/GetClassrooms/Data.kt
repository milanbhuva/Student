package com.kiyansoftech.student.model.GetClassrooms


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("discounted_price")
    var discountedPrice: String? = null,
    @SerializedName("id")
    var id: String? = null,
    @SerializedName("isActive")
    var isActive: String? = null,
    @SerializedName("price")
    var price: String? = null,
    @SerializedName("title")
    var title: String? = null
)