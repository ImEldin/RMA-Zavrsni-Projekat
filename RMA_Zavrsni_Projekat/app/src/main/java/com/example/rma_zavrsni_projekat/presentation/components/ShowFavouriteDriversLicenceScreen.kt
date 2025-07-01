package com.example.rma_zavrsni_projekat.presentation.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.example.rma_zavrsni_projekat.data.local.DatabaseProvider
import com.example.rma_zavrsni_projekat.data.repository.FavouriteDriversLicenceRepository
import com.example.rma_zavrsni_projekat.presentation.screens.driverslicenses.FavouriteDriversLicenceScreen
import com.example.rma_zavrsni_projekat.viewmodel.FavouriteDriversLicenceViewModel

@Composable
fun ShowFavouriteDriversLicenceScreen(navController: NavController) {
    val context = LocalContext.current
    val db = remember { DatabaseProvider.getDatabase(context) }
    val repo = remember { FavouriteDriversLicenceRepository(db.favouriteDriversLicenceDao()) }
    val viewModel = remember { FavouriteDriversLicenceViewModel(repo) }

    FavouriteDriversLicenceScreen(viewModel = viewModel, navController = navController)
}