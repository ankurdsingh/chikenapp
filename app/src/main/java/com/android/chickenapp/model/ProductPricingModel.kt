package com.android.chickenapp.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ProductPricingModel(@Json(name ="base_price")
                               val basePrice: Double)
