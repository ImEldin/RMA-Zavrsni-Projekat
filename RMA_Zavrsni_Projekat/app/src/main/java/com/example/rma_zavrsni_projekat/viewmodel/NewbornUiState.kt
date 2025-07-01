package com.example.rma_zavrsni_projekat.viewmodel

import com.example.rma_zavrsni_projekat.data.model.Newborn

sealed class NewbornUiState {
    data object Loading : NewbornUiState()
    data class Success(val data: List<Newborn>) : NewbornUiState()
    data class Error(val message: String) : NewbornUiState()
}