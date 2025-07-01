package com.example.rma_zavrsni_projekat.data.network.dto

import com.example.rma_zavrsni_projekat.data.model.IdCard

data class IdCardDto(
    val entity: String,
    val canton: String?,
    val municipality: String,
    val institution: String,
    val year: Int,
    val month: Int,
    val dateUpdate: String,
    val issuedFirstTimeMaleTotal: Int,
    val replacedMaleTotal: Int,
    val issuedFirstTimeFemaleTotal: Int,
    val replacedFemaleTotal: Int,
    val total: Int
){
    fun toIdCard():IdCard {
        return IdCard(
            entity = entity,
            canton = canton ?: "-",
            municipality = municipality,
            institution = institution,
            year = year,
            month = month,
            dateUpdate = dateUpdate,
            issuedFirstTimeMaleTotal = issuedFirstTimeMaleTotal,
            replacedMaleTotal = replacedMaleTotal,
            issuedFirstTimeFemaleTotal = issuedFirstTimeFemaleTotal,
            replacedFemaleTotal = replacedFemaleTotal,
            total = total
        )
    }
}

