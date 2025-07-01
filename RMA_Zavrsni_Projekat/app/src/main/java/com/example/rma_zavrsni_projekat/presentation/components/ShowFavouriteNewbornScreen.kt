package com.example.rma_zavrsni_projekat.presentation.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.example.rma_zavrsni_projekat.data.local.DatabaseProvider
import com.example.rma_zavrsni_projekat.data.repository.FavouriteNewbornRepository
import com.example.rma_zavrsni_projekat.presentation.screens.newborn.FavouriteNewbornScreen
import com.example.rma_zavrsni_projekat.viewmodel.FavouriteNewbornViewModel

@Composable
fun ShowFavouriteNewbornScreen(navController: NavController) {
    val context = LocalContext.current
    val db = remember { DatabaseProvider.getDatabase(context) }
    val repo = remember { FavouriteNewbornRepository(db.favouriteNewbornDao()) }
    val viewModel = remember { FavouriteNewbornViewModel(repo) }

    FavouriteNewbornScreen(viewModel = viewModel, navController = navController)
}