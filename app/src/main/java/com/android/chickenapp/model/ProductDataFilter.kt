package com.android.chickenapp.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ProductDataFilter(val title: String,
                             val type: String
                             )
