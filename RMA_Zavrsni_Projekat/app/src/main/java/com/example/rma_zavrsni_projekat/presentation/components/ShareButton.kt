package com.example.rma_zavrsni_projekat.presentation.components

import android.content.Context
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.example.rma_zavrsni_projekat.data.model.DriversLicence
import com.example.rma_zavrsni_projekat.data.model.IdCard
import com.example.rma_zavrsni_projekat.data.model.Newborn
import com.example.rma_zavrsni_projekat.util.toShareText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import com.example.rma_zavrsni_projekat.util.shareData

@Composable
fun ShareButton(item: Any) {
    val context = LocalContext.current
    val shareText = when (item) {
        is Newborn -> item.toShareText()
        is IdCard -> item.toShareText()
        is DriversLicence -> item.toShareText()
        else -> "No data to share"
    }

    IconButton(onClick = { shareData(context, shareText) }) {
        Icon(imageVector = Icons.Default.Share, contentDescription = "Share")
    }
}

