package com.example.rma_zavrsni_projekat.viewmodel

import com.example.rma_zavrsni_projekat.data.model.IdCard

sealed class IdCardUiState {
    data object Loading : IdCardUiState()
    data class Success(val data: List<IdCard>) : IdCardUiState()
    data class Error(val message: String) : IdCardUiState()
}