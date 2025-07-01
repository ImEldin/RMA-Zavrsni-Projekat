package com.example.rma_zavrsni_projekat.data.network

import com.example.rma_zavrsni_projekat.data.network.dto.DriversLicenceResponseDto
import com.example.rma_zavrsni_projekat.data.network.dto.IdCardResponseDto
import com.example.rma_zavrsni_projekat.data.network.dto.RequestDto
import com.example.rma_zavrsni_projekat.data.network.dto.NewbornResponseDto
import retrofit2.http.Body
import retrofit2.http.POST


interface OpenDataApi {
    @POST("api/NewbornByBirthDate/list")
    suspend fun getNewborns(@Body request: RequestDto): NewbornResponseDto

    @POST("api/IssuedDLCards/list")
    suspend fun getDriverLicences(@Body request: RequestDto): DriversLicenceResponseDto

    @POST("api/IssuedIDCards/list")
    suspend fun getIdCards(@Body request: RequestDto): IdCardResponseDto
}