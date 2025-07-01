package com.example.rma_zavrsni_projekat.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rma_zavrsni_projekat.data.local.entity.FavouriteNewborn
import com.example.rma_zavrsni_projekat.data.repository.FavouriteNewbornRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class FavouriteNewbornViewModel(
    private val repository: FavouriteNewbornRepository
) : ViewModel() {

    val favourites = repository.getAllFavourites()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun add(fav: FavouriteNewborn) {
        viewModelScope.launch {
            repository.addToFavourites(fav)
        }
    }

    fun remove(fav: FavouriteNewborn) {
        viewModelScope.launch {
            repository.removeFromFavourites(fav)
        }
    }
}