package com.android.chickenapp.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ProductDataProductsModel(
    @Json(name = "product_merchantdising")
    val productMerchandise: ProductMerchandiseModel,
    @Json(name = "product_pricing")
    val productPricing: ProductPricingModel
//    @Json(name = "product_master")
//    val productPricing: ProductMasterModel
                              )
