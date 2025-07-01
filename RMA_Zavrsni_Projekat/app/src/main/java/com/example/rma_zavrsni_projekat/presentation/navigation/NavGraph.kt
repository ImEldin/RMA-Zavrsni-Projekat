package com.example.rma_zavrsni_projekat.presentation.navigation


import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.rma_zavrsni_projekat.data.local.DatabaseProvider
import com.example.rma_zavrsni_projekat.data.model.DriversLicence
import com.example.rma_zavrsni_projekat.data.model.IdCard
import com.example.rma_zavrsni_projekat.data.model.Newborn
import com.example.rma_zavrsni_projekat.data.repository.FavouriteDriversLicenceRepository
import com.example.rma_zavrsni_projekat.data.repository.FavouriteIdCardRepository
import com.example.rma_zavrsni_projekat.data.repository.FavouriteNewbornRepository
import com.example.rma_zavrsni_projekat.presentation.components.ShowFavouriteDriversLicenceScreen
import com.example.rma_zavrsni_projekat.presentation.components.ShowFavouriteIdCardScreen
import com.example.rma_zavrsni_projekat.presentation.components.ShowFavouriteNewbornScreen
import com.example.rma_zavrsni_projekat.presentation.screens.OnboardingScreen
import com.example.rma_zavrsni_projekat.presentation.screens.driverslicenses.DriversLicenseDetailsScreen
import com.example.rma_zavrsni_projekat.presentation.screens.driverslicenses.DriversLicenseScreen
import com.example.rma_zavrsni_projekat.presentation.screens.idcards.IdCardDetailsScreen
import com.example.rma_zavrsni_projekat.presentation.screens.idcards.IdCardsScreen
import com.example.rma_zavrsni_projekat.presentation.screens.newborn.NewbornsScreen
import com.example.rma_zavrsni_projekat.presentation.screens.newborn.NewbornDetailsScreen
import com.example.rma_zavrsni_projekat.viewmodel.FavouriteDriversLicenceViewModel
import com.example.rma_zavrsni_projekat.viewmodel.FavouriteIdCardViewModel
import com.example.rma_zavrsni_projekat.viewmodel.FavouriteNewbornViewModel
import com.google.gson.Gson
import java.net.URLDecoder
import java.nio.charset.StandardCharsets

@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.Onboarding.route) {
        composable(Screen.Onboarding.route) {
            OnboardingScreen(navController = navController)
        }
        composable(Screen.Newborns.route) {
            NewbornsScreen(navController)
        }
        composable(Screen.NewbornDetails.route) { backStackEntry ->
            val json = backStackEntry.arguments?.getString("newbornJson")
            val decodedJson = URLDecoder.decode(json ?: "", StandardCharsets.UTF_8.toString())
            val newborn = Gson().fromJson(decodedJson, Newborn::class.java)

            val context = navController.context
            val db = remember { DatabaseProvider.getDatabase(context) }
            val repo = remember { FavouriteNewbornRepository(db.favouriteNewbornDao()) }
            val viewModel = remember { FavouriteNewbornViewModel(repo) }

            NewbornDetailsScreen(
                newborn = newborn,
                navController = navController,
                viewModel = viewModel
            )
        }
        composable(Screen.IdCards.route) {
            IdCardsScreen(navController)
        }
        composable(Screen.IdCardDetails.route) { backStackEntry ->
            val json = backStackEntry.arguments?.getString("idCardJson")
            val decodedJson = URLDecoder.decode(json ?: "", StandardCharsets.UTF_8.toString())
            val idCard = Gson().fromJson(decodedJson, IdCard::class.java)

            val context = navController.context
            val db = remember { DatabaseProvider.getDatabase(context) }
            val repo = remember { FavouriteIdCardRepository(db.favouriteIdCardDao()) }
            val viewModel = remember { FavouriteIdCardViewModel(repo) }

            IdCardDetailsScreen(idCard = idCard, navController = navController, viewModel = viewModel)
        }
        composable(Screen.DriversLicenses.route) {
            DriversLicenseScreen(navController = navController)
        }
        composable(Screen.DriversLicenseDetails.route) { backStackEntry ->
            val json = backStackEntry.arguments?.getString("licenseJson")
            val decodedJson = URLDecoder.decode(json ?: "", StandardCharsets.UTF_8.toString())
            val license = Gson().fromJson(decodedJson, DriversLicence::class.java)

            val context = navController.context
            val db = remember { DatabaseProvider.getDatabase(context) }
            val repo =
                remember { FavouriteDriversLicenceRepository(db.favouriteDriversLicenceDao()) }
            val viewModel = remember { FavouriteDriversLicenceViewModel(repo) }

            DriversLicenseDetailsScreen(
                license = license,
                navController = navController,
                viewModel = viewModel
            )
        }

        composable(Screen.FavouriteNewborns.route) {
            ShowFavouriteNewbornScreen(navController = navController)
        }
        composable(Screen.FavouriteIdCards.route) {
            ShowFavouriteIdCardScreen(navController = navController)
        }

        composable(Screen.FavouriteDriversLicences.route) {
            ShowFavouriteDriversLicenceScreen(navController = navController)
        }
    }
}