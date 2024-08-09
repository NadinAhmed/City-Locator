package com.nadin.city_locator.presentation.cityList

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.nadin.city_locator.presentation.cityList.composables.CityListItem


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CityListScreen(
    viewModel: CityListViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val context = LocalContext.current

    LaunchedEffect(key1 = state.intent) {
        state.intent?.let { context.startActivity(it) }
        viewModel.resetIntent()
    }

    Scaffold(
        topBar = {
            AnimatedContent(
                targetState = state.isSearchVisible && !state.isLoading,
                label = "search_field"
            ) {
                if (it) {
                    TextField(
                        value = state.searchQuery,
                        onValueChange = { viewModel.onEvent(CityListEvent.OnSearchValueChanged(it)) },
                        trailingIcon = {
                            IconButton(onClick = { viewModel.onEvent(CityListEvent.OnSearchHideClicked) }) {
                                Icon(
                                    imageVector = Icons.Default.Close,
                                    contentDescription = "Hide Search"
                                )
                            }
                        },
                        colors = TextFieldDefaults.colors(
                            focusedIndicatorColor = MaterialTheme.colorScheme.primary,
                            unfocusedIndicatorColor = MaterialTheme.colorScheme.primary,
                        ),
                        placeholder = { Text("Search") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .statusBarsPadding()
                            .padding(horizontal = 16.dp)
                    )
                } else {
                    TopAppBar(
                        title = {
                            Text(
                                text = "Cities List",
                                style = MaterialTheme.typography.titleLarge
                            )
                        },
                        actions = {
                            if (!state.isLoading) {
                                IconButton(onClick = {
                                    viewModel.onEvent(CityListEvent.OnSearchClicked)
                                }) {
                                    Icon(
                                        imageVector = Icons.Outlined.Search,
                                        contentDescription = "Search"
                                    )
                                }
                            }
                        },
                        colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.background)
                    )
                }
            }
        }
    ) { innerPadding ->
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
        ) {
            if (state.isLoading) {
                CircularProgressIndicator()
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(state.cities) {
                        CityListItem(
                            city = it,
                            onClick = { viewModel.onEvent(CityListEvent.OnCityClicked(city = it)) },
                            modifier = Modifier.height(70.dp),
                        )
                    }
                }
            }
        }
    }
}