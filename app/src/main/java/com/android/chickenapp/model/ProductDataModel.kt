package com.android.chickenapp.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ProductDataModel(val title:String,
                            @Json(name= "info_badge")val infoBadge:String,
                            @Json(name= "info_message")val infoMessage: String,
                            val filters:List<ProductDataFilter>,
                            val products:List<ProductDataProductsModel>
                            )
