package com.example.rma_zavrsni_projekat.util

import android.content.Context
import android.content.Intent

fun shareData(context: Context, textToShare: String) {
    val sendIntent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT, textToShare)
        type = "text/plain"
    }
    val shareIntent = Intent.createChooser(sendIntent, null)
    context.startActivity(shareIntent)
}