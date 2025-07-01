package com.example.rma_zavrsni_projekat.data.network.dto

data class DriversLicenceResponseDto(
    val errors: List<String>,
    val result: List<DriversLicenceDto>,
    val success: Boolean
)
