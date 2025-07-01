package com.example.rma_zavrsni_projekat.presentation.screens.newborn

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.rma_zavrsni_projekat.data.local.entity.FavouriteNewborn
import com.example.rma_zavrsni_projekat.data.model.Newborn
import com.example.rma_zavrsni_projekat.presentation.components.FavouriteHeartButton
import com.example.rma_zavrsni_projekat.presentation.components.InfoRow
import com.example.rma_zavrsni_projekat.presentation.components.PieSlice
import com.example.rma_zavrsni_projekat.presentation.components.SectionHeader
import com.example.rma_zavrsni_projekat.presentation.components.ShareButton
import com.example.rma_zavrsni_projekat.presentation.components.SimplePieData
import com.example.rma_zavrsni_projekat.viewmodel.FavouriteNewbornViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewbornDetailsScreen(newborn: Newborn, navController: NavController, viewModel: FavouriteNewbornViewModel) {
    val favourites by viewModel.favourites.collectAsState()

    val isFavourite = favourites.any { fav ->
        fav.entity == newborn.entity &&
                fav.canton == newborn.canton &&
                fav.municipality == newborn.municipality &&
                fav.institution == newborn.institution &&
                fav.year == newborn.year &&
                fav.month == newborn.month &&
                fav.dateUpdate == newborn.dateUpdate &&
                fav.maleTotal == newborn.maleTotal &&
                fav.femaleTotal == newborn.femaleTotal &&
                fav.total == newborn.total
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "Newborn Details",
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
                    ShareButton(item = newborn)
                    FavouriteHeartButton(
                        isFavourite = isFavourite,
                        onToggleFavourite = { newValue ->
                            if (newValue) {
                                val fav = FavouriteNewborn(
                                    entity = newborn.entity,
                                    canton = newborn.canton,
                                    municipality = newborn.municipality,
                                    institution = newborn.institution,
                                    year = newborn.year,
                                    month = newborn.month,
                                    dateUpdate = newborn.dateUpdate,
                                    maleTotal = newborn.maleTotal,
                                    femaleTotal = newborn.femaleTotal,
                                    total = newborn.total
                                )
                                viewModel.add(fav)
                            } else {
                                val toRemove = favourites.find { fav ->
                                    fav.entity == newborn.entity &&
                                            fav.canton == newborn.canton &&
                                            fav.municipality == newborn.municipality &&
                                            fav.institution == newborn.institution &&
                                            fav.year == newborn.year &&
                                            fav.month == newborn.month &&
                                            fav.dateUpdate == newborn.dateUpdate &&
                                            fav.maleTotal == newborn.maleTotal &&
                                            fav.femaleTotal == newborn.femaleTotal &&
                                            fav.total == newborn.total
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
                    Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        InfoRow(label = "Entity", value = newborn.entity)
                        InfoRow(label = "Canton", value = newborn.canton)
                        InfoRow(label = "Municipality", value = newborn.municipality)
                        InfoRow(label = "Institution", value = newborn.institution)
                        InfoRow(label = "Date", value = "${"%02d".format(newborn.month)}/${newborn.year}")
                        InfoRow(label = "Updated", value = newborn.dateUpdate)
                    }
                }

                SectionHeader(title = "Statistics")

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer
                    )
                ) {
                    Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        InfoRow(label = "Male", value = newborn.maleTotal.toString())
                        InfoRow(label = "Female", value = newborn.femaleTotal.toString())
                        InfoRow(label = "Total", value = newborn.total.toString())
                    }
                }

                SectionHeader(title = "Charts")

                val pieData = listOf(
                    PieSlice("Male", newborn.maleTotal.toFloat(), Color(0xFF4A90E2)),
                    PieSlice("Female", newborn.femaleTotal.toFloat(), Color(0xFFE94E77))
                )

                SimplePieData(slices = pieData, modifier = Modifier.padding(16.dp))
            }
        }
    }
}
