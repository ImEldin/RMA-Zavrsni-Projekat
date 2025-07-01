package com.example.rma_zavrsni_projekat.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.rma_zavrsni_projekat.R
import com.example.rma_zavrsni_projekat.data.SelectedPreferences
import com.example.rma_zavrsni_projekat.presentation.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OnboardingScreen(navController: NavController) {
    val datasets = listOf("Newborns", "ID Cards", "Driver Licenses")
    val regions = mapOf(
        "Federation of B&H" to 1,
        "Republika Srpska" to 2,
        "Brčko District" to 3
    )

    var selectedDataset by remember { mutableStateOf("") }
    var selectedRegion by remember { mutableStateOf(regions.keys.first()) }

    var expandedDataset by remember { mutableStateOf(false) }
    var expandedRegion by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = 32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "App Logo",
            modifier = Modifier
                .size(300.dp)
        )

        Text(
            text = "Welcome to DataBIH",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.onPrimary
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "Explore official data in one place",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.padding(top = 4.dp)
        )

        Spacer(modifier = Modifier.height(26.dp))

        ExposedDropdownMenuBox(
            expanded = expandedDataset,
            onExpandedChange = { expandedDataset = !expandedDataset }
        ) {
            OutlinedTextField(
                value = selectedDataset,
                onValueChange = {},
                readOnly = true,
                placeholder = { Text("Select dataset") },
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedDataset)
                },
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth()
            )

            ExposedDropdownMenu(
                expanded = expandedDataset,
                onDismissRequest = { expandedDataset = false },
                modifier = Modifier.background(Color.White)
            ) {
                datasets.forEach { item ->
                    DropdownMenuItem(
                        text = {
                            Text(
                                text = item,
                                color = Color.Black,
                                modifier = Modifier.background(Color.White)
                            )
                        },
                        onClick = {
                            selectedDataset = item
                            expandedDataset = false
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.White)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        ExposedDropdownMenuBox(
            expanded = expandedRegion,
            onExpandedChange = { expandedRegion = !expandedRegion }
        ) {
            OutlinedTextField(
                value = selectedRegion,
                onValueChange = {},
                readOnly = true,
                placeholder = { Text("Select entity") },
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedRegion)
                },
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth()
            )

            ExposedDropdownMenu(
                expanded = expandedRegion,
                onDismissRequest = { expandedRegion = false },
                modifier = Modifier.background(Color.White)
            ) {
                regions.keys.forEach { item ->
                    DropdownMenuItem(
                        text = {
                            Text(
                                text = item,
                                color = Color.Black,
                                modifier = Modifier.background(Color.White)
                            )
                        },
                        onClick = {
                            selectedRegion = item
                            expandedRegion = false
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.White)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(40.dp))

        Button(
            enabled = selectedDataset.isNotBlank(),
            onClick = {
                SelectedPreferences.selectedEntityId = regions[selectedRegion] ?: 1

                when (selectedDataset) {
                    "Newborns" -> navController.navigate(Screen.Newborns.route)
                    "ID Cards" -> navController.navigate(Screen.IdCards.route)
                    "Driver Licenses" -> navController.navigate(Screen.DriversLicenses.route)
                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
        ) {
            Text("Continue", color = MaterialTheme.colorScheme.onPrimary, fontSize = 16.sp)
        }
    }
}
