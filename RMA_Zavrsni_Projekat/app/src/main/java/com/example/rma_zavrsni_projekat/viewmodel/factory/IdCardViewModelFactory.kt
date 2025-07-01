package com.example.rma_zavrsni_projekat.viewmodel.factory

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.rma_zavrsni_projekat.data.repository.IdCardRepository
import com.example.rma_zavrsni_projekat.viewmodel.IdCardViewModel

val IdCardViewModelFactory: ViewModelProvider.Factory = viewModelFactory {
    initializer {
        val savedStateHandle = createSavedStateHandle()
        IdCardViewModel(IdCardRepository(), savedStateHandle)
    }
}