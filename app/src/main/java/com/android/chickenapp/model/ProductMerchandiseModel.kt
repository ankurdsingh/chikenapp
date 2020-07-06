package com.android.chickenapp.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ProductMerchandiseModel(
    @Json(name = "product_id")
    val productId: String,
    @Json(name = "merchandise_name")
    val name: String,
    @Json(name = "pr_image")
    val image: String,
    @Json(name = "product_delivery_type")
    val deliveryType: String
                                   )
