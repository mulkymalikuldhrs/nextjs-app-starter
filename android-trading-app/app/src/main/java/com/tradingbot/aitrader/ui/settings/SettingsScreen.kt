package com.tradingbot.aitrader.ui.settings

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tradingbot.aitrader.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen() {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            // Trading Settings
            SettingsSection(
                title = "Trading Settings",
                icon = Icons.Filled.TrendingUp
            ) {
                var autoTrading by remember { mutableStateOf(true) }
                var riskPerTrade by remember { mutableStateOf("2.0") }
                var maxPositions by remember { mutableStateOf("5") }
                
                SettingItem(
                    title = "Auto Trading",
                    subtitle = "Enable automated trading",
                    trailing = {
                        Switch(
                            checked = autoTrading,
                            onCheckedChange = { autoTrading = it }
                        )
                    }
                )
                
                SettingItem(
                    title = "Risk Per Trade",
                    subtitle = "Maximum risk percentage per trade",
                    trailing = {
                        OutlinedTextField(
                            value = riskPerTrade,
                            onValueChange = { riskPerTrade = it },
                            modifier = Modifier.width(80.dp),
                            suffix = { Text("%") }
                        )
                    }
                )
                
                SettingItem(
                    title = "Max Positions",
                    subtitle = "Maximum concurrent positions",
                    trailing = {
                        OutlinedTextField(
                            value = maxPositions,
                            onValueChange = { maxPositions = it },
                            modifier = Modifier.width(80.dp)
                        )
                    }
                )
            }
        }
        
        item {
            // AI Settings
            SettingsSection(
                title = "AI & ML Settings",
                icon = Icons.Filled.Psychology
            ) {
                var aiEnabled by remember { mutableStateOf(true) }
                var mlModel by remember { mutableStateOf("LSTM") }
                var confidenceThreshold by remember { mutableStateOf("75") }
                
                SettingItem(
                    title = "AI Predictions",
                    subtitle = "Enable AI-powered predictions",
                    trailing = {
                        Switch(
                            checked = aiEnabled,
                            onCheckedChange = { aiEnabled = it }
                        )
                    }
                )
                
                SettingItem(
                    title = "ML Model",
                    subtitle = "Primary machine learning model",
                    trailing = {
                        var expanded by remember { mutableStateOf(false) }
                        ExposedDropdownMenuBox(
                            expanded = expanded,
                            onExpandedChange = { expanded = !expanded }
                        ) {
                            OutlinedTextField(
                                value = mlModel,
                                onValueChange = {},
                                readOnly = true,
                                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                                modifier = Modifier.menuAnchor()
                            )
                            ExposedDropdownMenu(
                                expanded = expanded,
                                onDismissRequest = { expanded = false }
                            ) {
                                listOf("LSTM", "Random Forest", "SVM", "Ensemble").forEach { model ->
                                    DropdownMenuItem(
                                        text = { Text(model) },
                                        onClick = {
                                            mlModel = model
                                            expanded = false
                                        }
                                    )
                                }
                            }
                        }
                    }
                )
                
                SettingItem(
                    title = "Confidence Threshold",
                    subtitle = "Minimum AI confidence for trades",
                    trailing = {
                        OutlinedTextField(
                            value = confidenceThreshold,
                            onValueChange = { confidenceThreshold = it },
                            modifier = Modifier.width(80.dp),
                            suffix = { Text("%") }
                        )
                    }
                )
            }
        }
        
        item {
            // ICT/SMC Settings
            SettingsSection(
                title = "ICT/SMC Settings",
                icon = Icons.Filled.Analytics
            ) {
                var orderBlocksEnabled by remember { mutableStateOf(true) }
                var fvgEnabled by remember { mutableStateOf(true) }
                var liquidityEnabled by remember { mutableStateOf(true) }
                
                SettingItem(
                    title = "Order Blocks",
                    subtitle = "Detect and trade order blocks",
                    trailing = {
                        Switch(
                            checked = orderBlocksEnabled,
                            onCheckedChange = { orderBlocksEnabled = it }
                        )
                    }
                )
                
                SettingItem(
                    title = "Fair Value Gaps",
                    subtitle = "Identify fair value gaps",
                    trailing = {
                        Switch(
                            checked = fvgEnabled,
                            onCheckedChange = { fvgEnabled = it }
                        )
                    }
                )
                
                SettingItem(
                    title = "Liquidity Sweeps",
                    subtitle = "Monitor liquidity levels",
                    trailing = {
                        Switch(
                            checked = liquidityEnabled,
                            onCheckedChange = { liquidityEnabled = it }
                        )
                    }
                )
            }
        }
        
        item {
            // Notifications
            SettingsSection(
                title = "Notifications",
                icon = Icons.Filled.Notifications
            ) {
                var tradeNotifications by remember { mutableStateOf(true) }
                var alertNotifications by remember { mutableStateOf(true) }
                var newsNotifications by remember { mutableStateOf(false) }
                
                SettingItem(
                    title = "Trade Notifications",
                    subtitle = "Notify on trade execution",
                    trailing = {
                        Switch(
                            checked = tradeNotifications,
                            onCheckedChange = { tradeNotifications = it }
                        )
                    }
                )
                
                SettingItem(
                    title = "Alert Notifications",
                    subtitle = "Price and technical alerts",
                    trailing = {
                        Switch(
                            checked = alertNotifications,
                            onCheckedChange = { alertNotifications = it }
                        )
                    }
                )
                
                SettingItem(
                    title = "News Notifications",
                    subtitle = "Economic news updates",
                    trailing = {
                        Switch(
                            checked = newsNotifications,
                            onCheckedChange = { newsNotifications = it }
                        )
                    }
                )
            }
        }
        
        item {
            // Account & Security
            SettingsSection(
                title = "Account & Security",
                icon = Icons.Filled.Security
            ) {
                SettingItem(
                    title = "API Configuration",
                    subtitle = "Configure broker API settings",
                    onClick = { /* Navigate to API settings */ }
                )
                
                SettingItem(
                    title = "Backup Settings",
                    subtitle = "Export trading configuration",
                    onClick = { /* Export settings */ }
                )
                
                SettingItem(
                    title = "Reset to Defaults",
                    subtitle = "Restore default settings",
                    onClick = { /* Reset settings */ }
                )
            }
        }
    }
}

@Composable
fun SettingsSection(
    title: String,
    icon: ImageVector,
    content: @Composable ColumnScope.() -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            content()
        }
    }
}

@Composable
fun SettingItem(
    title: String,
    subtitle: String,
    trailing: @Composable (() -> Unit)? = null,
    onClick: (() -> Unit)? = null
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Medium
            )
            Text(
                text = subtitle,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
            )
        }
        trailing?.invoke()
    }
}

@Preview(showBackground = true)
@Composable
fun SettingsScreenPreview() {
    AITraderTheme {
        SettingsScreen()
    }
}
