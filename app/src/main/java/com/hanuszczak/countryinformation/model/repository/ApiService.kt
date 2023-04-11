package com.hanuszczak.countryinformation.model.repository

import com.hanuszczak.countryinformation.model.dto.CountryDto
import com.hanuszczak.countryinformation.util.Constants
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(Constants.BASE_URL)
    .build()

interface ApiService {
    @GET("all")
    suspend fun getAll(): List<CountryDto>
}

object CountryApi {
    val retrofitService: ApiService by lazy { retrofit.create(ApiService::class.java) }
}