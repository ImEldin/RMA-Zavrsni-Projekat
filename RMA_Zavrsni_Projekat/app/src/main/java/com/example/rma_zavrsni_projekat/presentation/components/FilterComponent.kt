package com.example.rma_zavrsni_projekat.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.rma_zavrsni_projekat.data.model.FilterState
import com.example.rma_zavrsni_projekat.ui.theme.FilterBlue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType


@Composable
fun FilterComponent(
    filterState: FilterState,
    onFilterChange: (FilterState) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = CardDefaults.cardElevation(2.dp),
        colors = CardDefaults.cardColors(
            containerColor = FilterBlue,
            contentColor = Color.Black
        )
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            OutlinedTextField(
                value = filterState.year,
                onValueChange = { onFilterChange(filterState.copy(year = it)) },
                label = { Text("Year", color = Color.Black) },
                modifier = Modifier.weight(1f),
                textStyle = LocalTextStyle.current.copy(color = Color.Black),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )

            OutlinedTextField(
                value = filterState.month,
                onValueChange = { onFilterChange(filterState.copy(month = it)) },
                label = { Text("Month", color = Color.Black) },
                modifier = Modifier.weight(1f),
                textStyle = LocalTextStyle.current.copy(color = Color.Black),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)

            )
        }
    }
}