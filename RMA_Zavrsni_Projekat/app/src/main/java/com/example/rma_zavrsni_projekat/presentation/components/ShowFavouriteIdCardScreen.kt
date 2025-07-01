package com.example.rma_zavrsni_projekat.presentation.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.example.rma_zavrsni_projekat.data.local.DatabaseProvider
import com.example.rma_zavrsni_projekat.data.repository.FavouriteIdCardRepository
import com.example.rma_zavrsni_projekat.presentation.screens.idcards.FavouriteIdCardScreen

import com.example.rma_zavrsni_projekat.viewmodel.FavouriteIdCardViewModel


@Composable
fun ShowFavouriteIdCardScreen(navController: NavController) {
    val context = LocalContext.current
    val db = remember { DatabaseProvider.getDatabase(context) }
    val repo = remember { FavouriteIdCardRepository(db.favouriteIdCardDao()) }
    val viewModel = remember { FavouriteIdCardViewModel(repo) }

    FavouriteIdCardScreen(viewModel = viewModel, navController = navController)
}


