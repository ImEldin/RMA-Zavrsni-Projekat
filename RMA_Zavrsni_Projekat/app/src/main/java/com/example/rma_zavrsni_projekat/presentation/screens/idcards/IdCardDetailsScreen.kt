package com.example.rma_zavrsni_projekat.presentation.screens.idcards


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
import com.example.rma_zavrsni_projekat.data.model.IdCard
import com.example.rma_zavrsni_projekat.presentation.components.InfoRow
import com.example.rma_zavrsni_projekat.presentation.components.SectionHeader
import com.example.rma_zavrsni_projekat.viewmodel.FavouriteIdCardViewModel
import com.example.rma_zavrsni_projekat.presentation.components.FavouriteHeartButton
import com.example.rma_zavrsni_projekat.data.local.entity.FavouriteIdCard
import com.example.rma_zavrsni_projekat.presentation.components.PieSlice
import com.example.rma_zavrsni_projekat.presentation.components.ShareButton
import com.example.rma_zavrsni_projekat.presentation.components.SimplePieData

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IdCardDetailsScreen(idCard: IdCard, navController: NavController, viewModel: FavouriteIdCardViewModel) {
    val favourites by viewModel.favourites.collectAsState()

    val isFavourite = favourites.any { fav ->
        fav.entity == idCard.entity &&
                fav.canton == idCard.canton &&
                fav.municipality == idCard.municipality &&
                fav.institution == idCard.institution &&
                fav.year == idCard.year &&
                fav.month == idCard.month &&
                fav.dateUpdate == idCard.dateUpdate &&
                fav.issuedFirstTimeMaleTotal == idCard.issuedFirstTimeMaleTotal &&
                fav.replacedMaleTotal == idCard.replacedMaleTotal &&
                fav.issuedFirstTimeFemaleTotal == idCard.issuedFirstTimeFemaleTotal &&
                fav.replacedFemaleTotal == idCard.replacedFemaleTotal &&
                fav.total == idCard.total
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "ID Card Details",
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
                    ShareButton(item = idCard)
                    FavouriteHeartButton(
                        isFavourite = isFavourite,
                        onToggleFavourite = { newValue ->
                            if (newValue) {
                                val fav = FavouriteIdCard(
                                    entity = idCard.entity,
                                    canton = idCard.canton,
                                    municipality = idCard.municipality,
                                    institution = idCard.institution,
                                    year = idCard.year,
                                    month = idCard.month,
                                    dateUpdate = idCard.dateUpdate,
                                    issuedFirstTimeMaleTotal = idCard.issuedFirstTimeMaleTotal,
                                    replacedMaleTotal = idCard.replacedMaleTotal,
                                    issuedFirstTimeFemaleTotal = idCard.issuedFirstTimeFemaleTotal,
                                    replacedFemaleTotal = idCard.replacedFemaleTotal,
                                    total = idCard.total
                                )
                                viewModel.add(fav)
                            } else {
                                val toRemove = favourites.find { fav ->
                                    fav.entity == idCard.entity &&
                                            fav.canton == idCard.canton &&
                                            fav.municipality == idCard.municipality &&
                                            fav.institution == idCard.institution &&
                                            fav.year == idCard.year &&
                                            fav.month == idCard.month &&
                                            fav.dateUpdate == idCard.dateUpdate &&
                                            fav.issuedFirstTimeMaleTotal == idCard.issuedFirstTimeMaleTotal &&
                                            fav.replacedMaleTotal == idCard.replacedMaleTotal &&
                                            fav.issuedFirstTimeFemaleTotal == idCard.issuedFirstTimeFemaleTotal &&
                                            fav.replacedFemaleTotal == idCard.replacedFemaleTotal &&
                                            fav.total == idCard.total
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
                        InfoRow(label = "Entity", value = idCard.entity)
                        InfoRow(label = "Canton", value = idCard.canton)
                        InfoRow(label = "Municipality", value = idCard.municipality)
                        InfoRow(label = "Institution", value = idCard.institution)
                        InfoRow(label = "Date", value = "${"%02d".format(idCard.month)}/${idCard.year}")
                        InfoRow(label = "Updated", value = idCard.dateUpdate)
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
                        InfoRow("Issued First Time (Male)", idCard.issuedFirstTimeMaleTotal.toString())
                        InfoRow("Replaced (Male)", idCard.replacedMaleTotal.toString())
                        InfoRow("Issued First Time (Female)", idCard.issuedFirstTimeFemaleTotal.toString())
                        InfoRow("Replaced (Female)", idCard.replacedFemaleTotal.toString())
                        InfoRow("Total", idCard.total.toString())
                    }
                }

                SectionHeader(title = "Charts")

                val pieData = listOf(
                    PieSlice("Issued First Time (Male)", idCard.issuedFirstTimeMaleTotal.toFloat(), Color(0xFF4A90E2)),
                    PieSlice("Replaced (Male)", idCard.replacedMaleTotal.toFloat(), Color(0xFF1976D2)),
                    PieSlice("Issued First Time (Female)", idCard.issuedFirstTimeFemaleTotal.toFloat(), Color(0xFFE94E77)),
                    PieSlice("Replaced (Female)", idCard.replacedFemaleTotal.toFloat(), Color(0xFFD81B60))
                )

                SimplePieData(slices = pieData)
            }
        }
    }
}
