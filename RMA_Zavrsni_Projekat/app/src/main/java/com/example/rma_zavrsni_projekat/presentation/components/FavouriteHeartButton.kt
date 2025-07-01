package com.example.rma_zavrsni_projekat.presentation.components

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color

@Composable
fun FavouriteHeartButton(
    isFavourite: Boolean,
    onToggleFavourite: (Boolean) -> Unit
) {
    IconButton(onClick = { onToggleFavourite(!isFavourite) }) {
        if (isFavourite) {
            Icon(
                imageVector = Icons.Filled.Favorite,
                contentDescription = "Unfavourite",
                tint = Color.Red
            )
        } else {
            Icon(
                imageVector = Icons.Outlined.FavoriteBorder,
                contentDescription = "Favourite",
                tint = Color.Gray
            )
        }
    }
}