package com.android.chickenapp.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ProductModel(val statusCode: Int,
                        val statusMessage: String,
                        var data: ProductDataModel) {
}