package com.example.rma_zavrsni_projekat.viewmodel

import android.content.Context
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rma_zavrsni_projekat.data.SelectedPreferences
import com.example.rma_zavrsni_projekat.data.network.dto.RequestDto
import com.example.rma_zavrsni_projekat.data.repository.NewbornRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class NewbornViewModel(
    private val repository: NewbornRepository = NewbornRepository(),
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _uiState = MutableStateFlow<NewbornUiState>(NewbornUiState.Loading)
    val uiState: StateFlow<NewbornUiState> = _uiState

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

    fun updateFilter(newYear: String, newMonth: String) {
        year = newYear
        month = newMonth
    }

    fun loadNewborns(
        context: Context,
        updateDate: String? = null,
        entityId: Int = SelectedPreferences.selectedEntityId,
        cantonId: Int = 0,
        municipalityId: Int = 0,
    ) {
        viewModelScope.launch {
            _uiState.value = NewbornUiState.Loading

            val request = RequestDto(
                updateDate = updateDate,
                entityId = entityId,
                cantonId = cantonId,
                municipalityId = municipalityId,
                year = year,
                month = month,
            )

            val result = repository.fetchNewborns(context, request)

            _uiState.value = result.fold(
                onSuccess = { NewbornUiState.Success(it) },
                onFailure = { NewbornUiState.Error(it.message ?: "Unknown error") }
            )
        }
    }
}
