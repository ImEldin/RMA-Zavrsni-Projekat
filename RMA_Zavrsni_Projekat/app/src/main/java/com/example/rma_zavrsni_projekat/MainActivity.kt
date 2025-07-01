package com.example.rma_zavrsni_projekat


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.rma_zavrsni_projekat.presentation.navigation.AppNavGraph
import com.example.rma_zavrsni_projekat.ui.theme.RMA_Zavrsni_ProjekatTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RMA_Zavrsni_ProjekatTheme {
                val navController = rememberNavController()
                AppNavGraph(navController)
            }
        }
    }
}
