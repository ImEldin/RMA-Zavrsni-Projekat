package com.example.rma_zavrsni_projekat.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rma_zavrsni_projekat.data.local.entity.FavouriteDriversLicence
import com.example.rma_zavrsni_projekat.data.repository.FavouriteDriversLicenceRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class FavouriteDriversLicenceViewModel(
    private val repository: FavouriteDriversLicenceRepository
) : ViewModel() {

    val favourites = repository.getAllFavourites()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun add(fav: FavouriteDriversLicence) {
        viewModelScope.launch {
            repository.addToFavourites(fav)
        }
    }

    fun remove(fav: FavouriteDriversLicence) {
        viewModelScope.launch {
            repository.removeFromFavourites(fav)
        }
    }
}