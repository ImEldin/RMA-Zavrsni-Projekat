package com.example.rma_zavrsni_projekat.presentation.screens.driverslicenses

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.rma_zavrsni_projekat.data.model.DriversLicence
import com.example.rma_zavrsni_projekat.presentation.components.FavouriteHeartButton
import com.example.rma_zavrsni_projekat.presentation.components.InfoRow
import com.example.rma_zavrsni_projekat.presentation.components.SectionHeader
import com.example.rma_zavrsni_projekat.viewmodel.FavouriteDriversLicenceViewModel
import com.example.rma_zavrsni_projekat.data.local.entity.FavouriteDriversLicence
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import com.example.rma_zavrsni_projekat.presentation.components.PieSlice
import com.example.rma_zavrsni_projekat.presentation.components.ShareButton
import com.example.rma_zavrsni_projekat.presentation.components.SimplePieData

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DriversLicenseDetailsScreen(license: DriversLicence, navController: NavController, viewModel: FavouriteDriversLicenceViewModel) {
    val favourites by viewModel.favourites.collectAsState()

    val isFavourite = favourites.any { fav ->
        fav.entity == license.entity &&
                fav.canton == license.canton &&
                fav.municipality == license.municipality &&
                fav.institution == license.institution &&
                fav.year == license.year &&
                fav.month == license.month &&
                fav.dateUpdate == license.dateUpdate &&
                fav.issuedFirstTimeMaleTotal == license.issuedFirstTimeMaleTotal &&
                fav.replacedMaleTotal == license.replacedMaleTotal &&
                fav.issuedFirstTimeFemaleTotal == license.issuedFirstTimeFemaleTotal &&
                fav.replacedFemaleTotal == license.replacedFemaleTotal &&
                fav.total == license.total
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "Driver's Licence Details",
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                },
                actions = {
                    ShareButton(item = license)
                    FavouriteHeartButton(
                        isFavourite = isFavourite,
                        onToggleFavourite = { newValue ->
                            if (newValue) {
                                val fav = FavouriteDriversLicence(
                                    entity = license.entity,
                                    canton = license.canton,
                                    municipality = license.municipality,
                                    institution = license.institution,
                                    year = license.year,
                                    month = license.month,
                                    dateUpdate = license.dateUpdate,
                                    issuedFirstTimeMaleTotal = license.issuedFirstTimeMaleTotal,
                                    replacedMaleTotal = license.replacedMaleTotal,
                                    issuedFirstTimeFemaleTotal = license.issuedFirstTimeFemaleTotal,
                                    replacedFemaleTotal = license.replacedFemaleTotal,
                                    total = license.total
                                )
                                viewModel.add(fav)
                            } else {
                                val toRemove = favourites.find { fav ->
                                    fav.entity == license.entity &&
                                            fav.canton == license.canton &&
                                            fav.municipality == license.municipality &&
                                            fav.institution == license.institution &&
                                            fav.year == license.year &&
                                            fav.month == license.month &&
                                            fav.dateUpdate == license.dateUpdate &&
                                            fav.issuedFirstTimeMaleTotal == license.issuedFirstTimeMaleTotal &&
                                            fav.replacedMaleTotal == license.replacedMaleTotal &&
                                            fav.issuedFirstTimeFemaleTotal == license.issuedFirstTimeFemaleTotal &&
                                            fav.replacedFemaleTotal == license.replacedFemaleTotal &&
                                            fav.total == license.total
                                }
                                if (toRemove != null) {
                                    viewModel.remove(toRemove)
                                }
                            }
                        }
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            )

        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            item {
                SectionHeader(title = "General Info")

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer
                    )
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        InfoRow(label = "Entity", value = license.entity)
                        InfoRow(label = "Canton", value = license.canton)
                        InfoRow(label = "Municipality", value = license.municipality)
                        InfoRow(label = "Institution", value = license.institution)
                        InfoRow(label = "Date", value = "${"%02d".format(license.month)}/${license.year}")
                        InfoRow(label = "Updated", value = license.dateUpdate)
                    }
                }

                SectionHeader(title = "Statistics")

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer
                    )
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        InfoRow("Issued First Time (Male)", license.issuedFirstTimeMaleTotal.toString())
                        InfoRow("Replaced (Male)", license.replacedMaleTotal.toString())
                        InfoRow("Issued First Time (Female)", license.issuedFirstTimeFemaleTotal.toString())
                        InfoRow("Replaced (Female)", license.replacedFemaleTotal.toString())
                        InfoRow("Total", license.total.toString())
                    }
                }

                SectionHeader(title = "Charts")

                val pieData = listOf(
                    PieSlice("Issued First Time (Male)", license.issuedFirstTimeMaleTotal.toFloat(), Color(0xFF4A90E2)),
                    PieSlice("Replaced (Male)", license.replacedMaleTotal.toFloat(), Color(0xFF1976D2)),
                    PieSlice("Issued First Time (Female)", license.issuedFirstTimeFemaleTotal.toFloat(), Color(0xFFE94E77)),
                    PieSlice("Replaced (Female)", license.replacedFemaleTotal.toFloat(), Color(0xFFD81B60))
                )

                SimplePieData(slices = pieData)
            }
        }
    }
}
