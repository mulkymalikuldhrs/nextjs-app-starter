package com.mulky.assistant.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.mulky.assistant.presentation.theme.MulkyAssistantTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MulkyAssistantTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    var selectedTab by remember { mutableStateOf(0) }
    
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Mulky Assistant") },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        },
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    selected = selectedTab == 0,
                    onClick = { selectedTab = 0 },
                    icon = { /* TODO: Add chart icon */ },
                    label = { Text(stringResource(id = com.mulky.assistant.R.string.tab_charts)) }
                )
                NavigationBarItem(
                    selected = selectedTab == 1,
                    onClick = { selectedTab = 1 },
                    icon = { /* TODO: Add analysis icon */ },
                    label = { Text(stringResource(id = com.mulky.assistant.R.string.tab_analysis)) }
                )
                NavigationBarItem(
                    selected = selectedTab == 2,
                    onClick = { selectedTab = 2 },
                    icon = { /* TODO: Add journal icon */ },
                    label = { Text(stringResource(id = com.mulky.assistant.R.string.tab_journal)) }
                )
                NavigationBarItem(
                    selected = selectedTab == 3,
                    onClick = { selectedTab = 3 },
                    icon = { /* TODO: Add settings icon */ },
                    label = { Text(stringResource(id = com.mulky.assistant.R.string.tab_settings)) }
                )
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            when (selectedTab) {
                0 -> ChartScreen()
                1 -> AnalysisScreen()
                2 -> JournalScreen()
                3 -> SettingsScreen()
            }
        }
    }
}

@Composable
fun ChartScreen() {
    // TODO: Implement chart screen with TradingView integration
    Text("Chart Screen - Coming Soon")
}

@Composable
fun AnalysisScreen() {
    // TODO: Implement analysis screen with ICT & SMC patterns
    Text("Analysis Screen - Coming Soon")
}

@Composable
fun JournalScreen() {
    // TODO: Implement trading journal
    Text("Journal Screen - Coming Soon")
}

@Composable
fun SettingsScreen() {
    // TODO: Implement settings
    Text("Settings Screen - Coming Soon")
}
