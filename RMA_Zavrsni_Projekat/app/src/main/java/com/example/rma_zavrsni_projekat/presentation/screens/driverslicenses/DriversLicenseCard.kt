package com.example.rma_zavrsni_projekat.presentation.screens.driverslicenses

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.rma_zavrsni_projekat.data.model.DriversLicence

@Composable
fun DriversLicenceCard(license: DriversLicence, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.onPrimary
        )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Institution: ${license.institution}")
            Text("Total: ${license.total}")
            Text("Date: ${"%02d".format(license.month)}/${license.year}")
        }
    }
}