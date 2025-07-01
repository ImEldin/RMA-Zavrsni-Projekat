package com.example.rma_zavrsni_projekat.presentation.screens.newborn

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.rma_zavrsni_projekat.data.model.Newborn

@Composable
fun NewbornCard(newborn: Newborn, onClick: () -> Unit) {
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
            Text("Institution: ${newborn.institution}")
            Text("Total: ${newborn.total}")
            Text("Date: ${"%02d".format(newborn.month)}/${newborn.year}")
        }
    }
}