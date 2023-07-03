package com.example.module_3_lesson_4_hw_2_compose

import com.google.gson.annotations.SerializedName

data class ResponseMain(
    @SerializedName("bpi") val bpi: Bpi
)

data class Bpi(
    @SerializedName("USD") val usd: Currency,
    @SerializedName("GBP") val gbp: Currency,
    @SerializedName("EUR") val eur: Currency
)

data class Currency(
    @SerializedName("code") val code: String,
    @SerializedName("symbol") val symbol: String,
    @SerializedName("rate") val rate: String,
    @SerializedName("description") val description: String,
    @SerializedName("rate_float") val rateFloat: Float
)
