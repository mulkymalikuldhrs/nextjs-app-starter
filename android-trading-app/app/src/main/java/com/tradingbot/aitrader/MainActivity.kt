package com.tradingbot.aitrader

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.tradingbot.aitrader.ui.dashboard.DashboardScreen
import com.tradingbot.aitrader.ui.trading.TradingScreen
import com.tradingbot.aitrader.ui.analysis.AnalysisScreen
import com.tradingbot.aitrader.ui.settings.SettingsScreen
import com.tradingbot.aitrader.ui.theme.AITraderTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AITraderTheme {
                AITraderApp()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AITraderApp() {
    val navController = rememberNavController()
    
    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController = navController)
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "dashboard",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("dashboard") {
                DashboardScreen()
            }
            composable("trading") {
                TradingScreen()
            }
            composable("analysis") {
                AnalysisScreen()
            }
            composable("settings") {
                SettingsScreen()
            }
        }
    }
}

@Composable
fun BottomNavigationBar(navController: NavController) {
    val items = listOf(
        BottomNavItem("dashboard", "Dashboard", Icons.Filled.Dashboard),
        BottomNavItem("trading", "Trading", Icons.Filled.TrendingUp),
        BottomNavItem("analysis", "Analysis", Icons.Filled.Analytics),
        BottomNavItem("settings", "Settings", Icons.Filled.Settings)
    )
    
    NavigationBar {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        
        items.forEach { item ->
            NavigationBarItem(
                icon = { Icon(item.icon, contentDescription = item.title) },
                label = { Text(item.title) },
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.startDestinationId)
                        launchSingleTop = true
                    }
                }
            )
        }
    }
}

data class BottomNavItem(
    val route: String,
    val title: String,
    val icon: ImageVector
)

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AITraderTheme {
        AITraderApp()
    }
}
