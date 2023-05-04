package com.azakkymakarim.tugas9.api

import com.azakkymakarim.tugas9.ResponseCat
import retrofit2.Call
import retrofit2.http.GET

interface APIService {
    @GET("breeds")
    fun getBreeds() : Call<ResponseCat>
}