package com.example.rma_zavrsni_projekat.viewmodel

import android.content.Context
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rma_zavrsni_projekat.data.SelectedPreferences
import com.example.rma_zavrsni_projekat.data.network.dto.RequestDto
import com.example.rma_zavrsni_projekat.data.repository.DriversLicenceRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DriversLicenceViewModel(
    private val repository: DriversLicenceRepository = DriversLicenceRepository(),
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _uiState = MutableStateFlow<DriversLicenceUiState>(DriversLicenceUiState.Loading)
    val uiState: StateFlow<DriversLicenceUiState> = _uiState

    var year: String
        get() = savedStateHandle["year"] ?: ""
        set(value) {
            savedStateHandle["year"] = value
        }

    var month: String
        get() = savedStateHandle["month"] ?: ""
        set(value) {
            savedStateHandle["month"] = value
        }

    fun updateFilter(year: String, month: String) {
        this.year = year
        this.month = month
    }

    fun loadDriversLicences(
        context: Context,
        updateDate: String? = null,
        entityId: Int = SelectedPreferences.selectedEntityId,
        cantonId: Int = 0,
        municipalityId: Int = 0,
    ) {
        viewModelScope.launch {
            _uiState.value = DriversLicenceUiState.Loading

            val request = RequestDto(
                updateDate = updateDate,
                entityId = entityId,
                cantonId = cantonId,
                municipalityId = municipalityId,
                year = year,
                month = month,
            )

            val result = repository.fetchDriversLicences(context, request)

            _uiState.value = result.fold(
                onSuccess = { DriversLicenceUiState.Success(it) },
                onFailure = { DriversLicenceUiState.Error(it.message ?: "Unknown error") }
            )
        }
    }
}