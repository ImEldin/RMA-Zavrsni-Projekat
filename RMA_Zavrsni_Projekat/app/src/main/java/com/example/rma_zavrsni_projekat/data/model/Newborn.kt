package com.example.rma_zavrsni_projekat.data.model

data class Newborn(
    val entity: String,
    val canton: String,
    val municipality: String,
    val institution: String,
    val year: Int,
    val month: Int,
    val dateUpdate: String,
    val maleTotal: Int,
    val femaleTotal: Int,
    val total: Int
)