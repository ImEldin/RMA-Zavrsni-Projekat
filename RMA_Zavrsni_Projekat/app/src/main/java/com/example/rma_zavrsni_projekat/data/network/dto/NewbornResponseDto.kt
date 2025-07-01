package com.example.rma_zavrsni_projekat.data.network.dto

import com.google.gson.annotations.SerializedName

data class NewbornResponseDto(
    val errors: List<String>,
    val result: List<NewbornDto>,
    val success: Boolean
)