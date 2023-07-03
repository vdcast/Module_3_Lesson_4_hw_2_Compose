package com.example.module_3_lesson_4_hw_2_compose

import retrofit2.Call
import retrofit2.http.GET

interface API {
    @GET("v1/bpi/currentprice.json")
    fun getCurrentPrice(): Call<ResponseMain>
}