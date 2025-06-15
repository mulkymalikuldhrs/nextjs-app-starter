package com.tradingbot.aitrader.ui.trading

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tradingbot.aitrader.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TradingScreen() {
    var selectedTab by remember { mutableStateOf(0) }
    val tabs = listOf("Positions", "Orders", "History")
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Trading Pairs Header
        Card(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = "Trading Pairs",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(12.dp))
                
                // Major Pairs
                TradingPairRow("EUR/USD", "1.0875", "+0.0023", true)
                Spacer(modifier = Modifier.height(8.dp))
                TradingPairRow("GBP/USD", "1.2720", "-0.0045", false)
                Spacer(modifier = Modifier.height(8.dp))
                TradingPairRow("USD/JPY", "149.85", "+0.25", true)
            }
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Tab Row
        TabRow(selectedTabIndex = selectedTab) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    selected = selectedTab == index,
                    onClick = { selectedTab = index },
                    text = { Text(title) }
                )
            }
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Tab Content
        when (selectedTab) {
            0 -> PositionsTab()
            1 -> OrdersTab()
            2 -> HistoryTab()
        }
    }
}

@Composable
fun TradingPairRow(
    symbol: String,
    price: String,
    change: String,
    isPositive: Boolean
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                text = symbol,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Medium
            )
            Text(
                text = "Major Pair",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
            )
        }
        Column(
            horizontalAlignment = Alignment.End
        ) {
            Text(
                text = price,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = change,
                style = MaterialTheme.typography.bodySmall,
                color = if (isPositive) ProfitGreen else LossRed,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Composable
fun PositionsTab() {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(getCurrentPositions()) { position ->
            PositionCard(position = position)
        }
    }
}

@Composable
fun PositionCard(position: Position) {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "${position.symbol} ${position.direction}",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Size: ${position.size}",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                    )
                }
                Column(
                    horizontalAlignment = Alignment.End
                ) {
                    Text(
                        text = "${if (position.pnl >= 0) "+" else ""}$${position.pnl}",
                        style = MaterialTheme.typography.titleMedium,
                        color = if (position.pnl >= 0) ProfitGreen else LossRed,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "${if (position.pnlPercent >= 0) "+" else ""}${position.pnlPercent}%",
                        style = MaterialTheme.typography.bodySmall,
                        color = if (position.pnlPercent >= 0) ProfitGreen else LossRed
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(12.dp))
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = "Entry: ${position.entryPrice}",
                        style = MaterialTheme.typography.bodySmall
                    )
                    Text(
                        text = "Current: ${position.currentPrice}",
                        style = MaterialTheme.typography.bodySmall
                    )
                }
                Column(
                    horizontalAlignment = Alignment.End
                ) {
                    Text(
                        text = "SL: ${position.stopLoss}",
                        style = MaterialTheme.typography.bodySmall,
                        color = LossRed
                    )
                    Text(
                        text = "TP: ${position.takeProfit}",
                        style = MaterialTheme.typography.bodySmall,
                        color = ProfitGreen
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(12.dp))
            
            // Strategy Tag
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(4.dp))
                    .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.1f))
                    .padding(horizontal = 8.dp, vertical = 4.dp)
            ) {
                Text(
                    text = position.strategy,
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}

@Composable
fun OrdersTab() {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(getPendingOrders()) { order ->
            OrderCard(order = order)
        }
    }
}

@Composable
fun OrderCard(order: PendingOrder) {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "${order.symbol} ${order.direction}",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "${order.type} Order",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                    )
                }
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(4.dp))
                        .background(WarningOrange.copy(alpha = 0.1f))
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = "PENDING",
                        style = MaterialTheme.typography.labelSmall,
                        color = WarningOrange,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(12.dp))
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Price: ${order.price}",
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = "Size: ${order.size}",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

@Composable
fun HistoryTab() {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(getTradeHistory()) { trade ->
            HistoryCard(trade = trade)
        }
    }
}

@Composable
fun HistoryCard(trade: HistoryTrade) {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "${trade.symbol} ${trade.direction}",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = trade.date,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                    )
                }
                Text(
                    text = "${if (trade.profit >= 0) "+" else ""}$${trade.profit}",
                    style = MaterialTheme.typography.titleMedium,
                    color = if (trade.profit >= 0) ProfitGreen else LossRed,
                    fontWeight = FontWeight.Bold
                )
            }
            
            Spacer(modifier = Modifier.height(8.dp))
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Entry: ${trade.entryPrice}",
                    style = MaterialTheme.typography.bodySmall
                )
                Text(
                    text = "Exit: ${trade.exitPrice}",
                    style = MaterialTheme.typography.bodySmall
                )
                Text(
                    text = "RR: ${trade.riskReward}",
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}

// Data classes
data class Position(
    val symbol: String,
    val direction: String,
    val size: String,
    val entryPrice: String,
    val currentPrice: String,
    val stopLoss: String,
    val takeProfit: String,
    val pnl: Double,
    val pnlPercent: Double,
    val strategy: String
)

data class PendingOrder(
    val symbol: String,
    val direction: String,
    val type: String,
    val price: String,
    val size: String
)

data class HistoryTrade(
    val symbol: String,
    val direction: String,
    val entryPrice: String,
    val exitPrice: String,
    val profit: Double,
    val riskReward: String,
    val date: String
)

// Sample data functions
fun getCurrentPositions(): List<Position> {
    return listOf(
        Position(
            symbol = "EUR/USD",
            direction = "BUY",
            size = "0.1 lots",
            entryPrice = "1.0875",
            currentPrice = "1.0890",
            stopLoss = "1.0850",
            takeProfit = "1.0925",
            pnl = 150.0,
            pnlPercent = 2.3,
            strategy = "ICT Order Block"
        ),
        Position(
            symbol = "GBP/USD",
            direction = "SELL",
            size = "0.05 lots",
            entryPrice = "1.2720",
            currentPrice = "1.2710",
            stopLoss = "1.2750",
            takeProfit = "1.2670",
            pnl = 50.0,
            pnlPercent = 1.1,
            strategy = "SMC Liquidity Sweep"
        )
    )
}

fun getPendingOrders(): List<PendingOrder> {
    return listOf(
        PendingOrder("USD/JPY", "BUY", "LIMIT", "149.50", "0.1 lots"),
        PendingOrder("AUD/USD", "SELL", "STOP", "0.6580", "0.05 lots")
    )
}

fun getTradeHistory(): List<HistoryTrade> {
    return listOf(
        HistoryTrade("EUR/USD", "BUY", "1.0850", "1.0900", 500.0, "1:2", "2024-06-14"),
        HistoryTrade("GBP/USD", "SELL", "1.2750", "1.2700", 250.0, "1:1.5", "2024-06-13"),
        HistoryTrade("USD/JPY", "BUY", "149.20", "149.10", -100.0, "1:1", "2024-06-12")
    )
}

@Preview(showBackground = true)
@Composable
fun TradingScreenPreview() {
    AITraderTheme {
        TradingScreen()
    }
}
