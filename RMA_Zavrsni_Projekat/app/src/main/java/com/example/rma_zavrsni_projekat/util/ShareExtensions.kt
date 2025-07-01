package com.example.rma_zavrsni_projekat.util

import com.example.rma_zavrsni_projekat.data.model.DriversLicence
import com.example.rma_zavrsni_projekat.data.model.IdCard
import com.example.rma_zavrsni_projekat.data.model.Newborn

fun Newborn.toShareText(): String = """
    Newborn Report:
    Entity: $entity
    Canton: $canton
    Municipality: $municipality
    Institution: $institution
    Year/Month: $year/$month
    Male: $maleTotal
    Female: $femaleTotal
    Total: $total
""".trimIndent()

fun IdCard.toShareText(): String = """
    ID Card Report:
    Entity: $entity
    Canton: $canton
    Municipality: $municipality
    Institution: $institution
    Year/Month: $year/$month
    Issued First Time Male: $issuedFirstTimeMaleTotal
    Replaced Male: $replacedMaleTotal
    Issued First Time Female: $issuedFirstTimeFemaleTotal
    Replaced Female: $replacedFemaleTotal
    Total: $total
""".trimIndent()

fun DriversLicence.toShareText(): String = """
    Driver's Licence Report:
    Entity: $entity
    Canton: $canton
    Municipality: $municipality
    Institution: $institution
    Year/Month: $year/$month
    Issued First Time Male: $issuedFirstTimeMaleTotal
    Replaced Male: $replacedMaleTotal
    Issued First Time Female: $issuedFirstTimeFemaleTotal
    Replaced Female: $replacedFemaleTotal
    Total: $total
""".trimIndent()