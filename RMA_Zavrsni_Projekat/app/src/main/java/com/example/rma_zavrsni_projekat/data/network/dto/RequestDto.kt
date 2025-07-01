package com.example.rma_zavrsni_projekat.data.network.dto

data class RequestDto(
    val updateDate: String? = null,
    val entityId: Int = 0,
    val cantonId: Int = 0,
    val municipalityId: Int = 0,
    val year: String = "",
    val month: String = ""
)