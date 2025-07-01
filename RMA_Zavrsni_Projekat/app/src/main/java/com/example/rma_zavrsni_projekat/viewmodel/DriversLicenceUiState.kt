package com.example.rma_zavrsni_projekat.viewmodel

import com.example.rma_zavrsni_projekat.data.model.DriversLicence

sealed class DriversLicenceUiState {
    data object Loading : DriversLicenceUiState()
    data class Success(val data: List<DriversLicence>) : DriversLicenceUiState()
    data class Error(val message: String) : DriversLicenceUiState()
}