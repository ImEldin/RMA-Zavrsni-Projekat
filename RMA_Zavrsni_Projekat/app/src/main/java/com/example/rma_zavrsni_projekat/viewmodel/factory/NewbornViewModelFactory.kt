package com.example.rma_zavrsni_projekat.viewmodel.factory

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.rma_zavrsni_projekat.data.repository.NewbornRepository
import com.example.rma_zavrsni_projekat.viewmodel.NewbornViewModel

val NewbornViewModelFactory: ViewModelProvider.Factory = viewModelFactory {
    initializer {
        val savedStateHandle = this.createSavedStateHandle()
        NewbornViewModel(NewbornRepository(), savedStateHandle)
    }
}