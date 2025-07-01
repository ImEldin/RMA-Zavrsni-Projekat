package com.example.rma_zavrsni_projekat.presentation.screens.newborn

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.rma_zavrsni_projekat.data.model.FilterState
import com.example.rma_zavrsni_projekat.viewmodel.NewbornUiState
import com.example.rma_zavrsni_projekat.viewmodel.NewbornViewModel
import com.example.rma_zavrsni_projekat.presentation.components.FilterComponent
import com.example.rma_zavrsni_projekat.presentation.navigation.Screen
import com.google.gson.Gson
import java.net.URLEncoder
import java.nio.charset.StandardCharsets
import androidx.compose.material.icons.filled.Favorite
import com.example.rma_zavrsni_projekat.viewmodel.factory.NewbornViewModelFactory

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewbornsScreen(
    navController: NavController,
    viewModel: NewbornViewModel = viewModel(factory = NewbornViewModelFactory)
) {
    val context = LocalContext.current
    val uiState by viewModel.uiState.collectAsState()
    var filterState by remember {
        mutableStateOf(FilterState(viewModel.year, viewModel.month))
    }

    LaunchedEffect(Unit) {
        viewModel.loadNewborns(context)
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "Newborns",
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
                    IconButton(onClick = { navController.navigate(Screen.FavouriteNewborns.route) }) {
                        Icon(
                            imageVector = Icons.Default.Favorite,
                            contentDescription = "Show favourites",
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )

        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxSize()
        ) {
            FilterComponent(filterState) { newFilter ->
                filterState = newFilter
                viewModel.updateFilter(newFilter.year, newFilter.month)
            }

            Spacer(modifier = Modifier.height(8.dp))

            when (uiState) {
                is NewbornUiState.Loading -> {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator()
                    }
                }

                is NewbornUiState.Error -> {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text(
                            text = "Error: ${(uiState as NewbornUiState.Error).message}",
                            color = MaterialTheme.colorScheme.error
                        )
                    }
                }

                is NewbornUiState.Success -> {
                    val filtered = (uiState as NewbornUiState.Success).data.filter {
                        (filterState.year.isEmpty() || it.year.toString() == filterState.year) &&
                                (filterState.month.isEmpty() || it.month.toString() == filterState.month)
                    }

                    if (filtered.isEmpty()) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(top = 48.dp),
                            contentAlignment = Alignment.TopCenter
                        ) {
                            Text(
                                text = "No results found for the selected filters.",
                                style = MaterialTheme.typography.bodyLarge,
                                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                            )
                        }
                    } else {
                        LazyColumn(
                            verticalArrangement = Arrangement.spacedBy(12.dp),
                            modifier = Modifier.fillMaxSize()
                        ) {
                            items(filtered) { newborn ->
                                NewbornCard(newborn = newborn) {
                                    val encoded = URLEncoder.encode(
                                        Gson().toJson(newborn),
                                        StandardCharsets.UTF_8.toString()
                                    )
                                    navController.navigate(Screen.NewbornDetails.createRoute(encoded))
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}