package com.example.rma_zavrsni_projekat.data.network.dto

data class IdCardResponseDto(
    val errors: List<String>,
    val result: List<IdCardDto>,
    val success: Boolean
)