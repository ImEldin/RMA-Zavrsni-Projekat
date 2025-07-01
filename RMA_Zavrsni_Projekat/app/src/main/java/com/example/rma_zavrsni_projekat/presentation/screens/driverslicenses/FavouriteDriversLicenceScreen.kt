package com.example.rma_zavrsni_projekat.presentation.screens.driverslicenses

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.rma_zavrsni_projekat.viewmodel.FavouriteDriversLicenceViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavouriteDriversLicenceScreen(
    viewModel: FavouriteDriversLicenceViewModel,
    navController: NavController
) {
    val favourites by viewModel.favourites.collectAsState()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "Favourite Drivers Licences",
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
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            )
        },
        content = { padding ->
            if (favourites.isEmpty()) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        "No favorites saved yet.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding)
                        .padding(horizontal = 12.dp, vertical = 8.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(favourites) { fav ->
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            colors = CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.primaryContainer
                            ),
                            elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                            shape = MaterialTheme.shapes.medium
                        ) {
                            Column(modifier = Modifier.padding(16.dp)) {
                                Text(
                                    "General Information",
                                    style = MaterialTheme.typography.titleMedium,
                                    color = MaterialTheme.colorScheme.primary,
                                    modifier = Modifier.padding(bottom = 8.dp)
                                )

                                InfoRow(label = "Entity", value = fav.entity)
                                InfoRow(label = "Canton", value = fav.canton)
                                InfoRow(label = "Municipality", value = fav.municipality)
                                InfoRow(label = "Institution", value = fav.institution)
                                InfoRow(label = "Year", value = fav.year.toString())
                                InfoRow(label = "Month", value = fav.month.toString())
                                InfoRow(label = "Updated", value = fav.dateUpdate)

                                Spacer(modifier = Modifier.height(12.dp))

                                Text(
                                    "Statistics",
                                    style = MaterialTheme.typography.titleMedium,
                                    color = MaterialTheme.colorScheme.primary,
                                    modifier = Modifier.padding(bottom = 8.dp)
                                )

                                InfoRow(label = "Issued First Time Male", value = fav.issuedFirstTimeMaleTotal.toString())
                                InfoRow(label = "Replaced Male", value = fav.replacedMaleTotal.toString())
                                InfoRow(label = "Issued First Time Female", value = fav.issuedFirstTimeFemaleTotal.toString())
                                InfoRow(label = "Replaced Female", value = fav.replacedFemaleTotal.toString())
                                InfoRow(label = "Total", value = fav.total.toString())
                            }
                        }
                    }
                    item {
                        Spacer(modifier = Modifier.height(12.dp))
                    }
                }
            }
        }
    )
}

@Composable
fun InfoRow(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 2.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSecondaryContainer
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onPrimaryContainer
        )
    }
}
