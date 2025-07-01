package com.example.rma_zavrsni_projekat.viewmodel.factory

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.rma_zavrsni_projekat.data.repository.DriversLicenceRepository
import com.example.rma_zavrsni_projekat.viewmodel.DriversLicenceViewModel

val DriversLicenceViewModelFactory: ViewModelProvider.Factory = viewModelFactory {
    initializer {
        val savedStateHandle = createSavedStateHandle()
        DriversLicenceViewModel(DriversLicenceRepository(), savedStateHandle)
    }
}