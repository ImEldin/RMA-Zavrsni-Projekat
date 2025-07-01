package com.example.rma_zavrsni_projekat.presentation.navigation

sealed class Screen(val route: String) {
    data object Onboarding : Screen("onboarding")
    data object Newborns : Screen("newborns")
    data object NewbornDetails : Screen("newborn_details/{newbornJson}") {
        fun createRoute(json: String): String = "newborn_details/$json"
    }
    data object IdCards : Screen("idcards")
    data object IdCardDetails : Screen("idcard_details/{idCardJson}") {
        fun createRoute(json: String): String = "idcard_details/$json"
    }
    data object DriversLicenses : Screen("drivers_licenses")
    data object DriversLicenseDetails : Screen("drivers_license_details/{licenseJson}") {
        fun createRoute(json: String): String = "drivers_license_details/$json"
    }
    data object FavouriteNewborns : Screen("favourites_newborn")
    data object FavouriteIdCards : Screen("favourites_idcards")
    data object FavouriteDriversLicences : Screen("favourites_driverslicences")
}