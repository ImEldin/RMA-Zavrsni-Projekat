package com.example.rma_zavrsni_projekat.data.network.dto

import com.example.rma_zavrsni_projekat.data.model.Newborn

data class NewbornDto(
    val entity: String,
    val canton: String?,
    val municipality: String,
    val institution: String,
    val year: Int,
    val month: Int,
    val dateUpdate: String,
    val maleTotal: Int,
    val femaleTotal: Int,
    val total: Int
) {
    fun toNewborn(): Newborn {
        return Newborn(
            entity = entity,
            canton = canton ?: "-",
            municipality = municipality,
            institution = institution,
            year = year,
            month = month,
            dateUpdate = dateUpdate,
            maleTotal = maleTotal,
            femaleTotal = femaleTotal,
            total = total
        )
    }
}