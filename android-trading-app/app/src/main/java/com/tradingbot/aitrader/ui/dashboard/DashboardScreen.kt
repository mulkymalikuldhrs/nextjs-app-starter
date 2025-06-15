package com.tradingbot.aitrader.ui.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tradingbot.aitrader.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen() {
    var botStatus by remember { mutableStateOf(true) }
    
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            // Header with Bot Status
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = if (botStatus) ProfitGreen else LossRed
                )
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "AI Trading Bot",
                            style = MaterialTheme.typography.headlineSmall,
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = if (botStatus) "ACTIVE" else "INACTIVE",
                            style = MaterialTheme.typography.bodyLarge,
                            color = Color.White
                        )
                    }
                    Switch(
                        checked = botStatus,
                        onCheckedChange = { botStatus = it },
                        colors = SwitchDefaults.colors(
                            checkedThumbColor = Color.White,
                            uncheckedThumbColor = Color.Gray
                        )
                    )
                }
            }
        }
        
        item {
            // Performance Metrics Row
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(getPerformanceMetrics()) { metric ->
                    MetricCard(metric = metric)
                }
            }
        }
        
        item {
            // Current Session Status
            SessionStatusCard()
        }
        
        item {
            // AI Predictions
            AIPredictionCard()
        }
        
        item {
            // Active Trades
            ActiveTradesCard()
        }
        
        item {
            // Market Structure
            MarketStructureCard()
        }
    }
}

@Composable
fun MetricCard(metric: PerformanceMetric) {
    Card(
        modifier = Modifier
            .width(140.dp)
            .height(100.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(
                imageVector = metric.icon,
                contentDescription = null,
                tint = metric.color,
                modifier = Modifier.size(24.dp)
            )
            Column {
                Text(
                    text = metric.value,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = metric.color
                )
                Text(
                    text = metric.label,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                )
            }
        }
    }
}

@Composable
fun SessionStatusCard() {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "Trading Sessions",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(12.dp))
            
            val sessions = listOf(
                SessionInfo("London", "14:00-17:00 WIB", true),
                SessionInfo("New York", "20:00-23:00 WIB", false),
                SessionInfo("Asian", "06:00-09:00 WIB", false)
            )
            
            sessions.forEach { session ->
                SessionRow(session = session)
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

@Composable
fun SessionRow(session: SessionInfo) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                text = session.name,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Medium
            )
            Text(
                text = session.time,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
            )
        }
        Box(
            modifier = Modifier
                .size(12.dp)
                .clip(RoundedCornerShape(6.dp))
                .background(if (session.isActive) ProfitGreen else NeutralGray)
        )
    }
}

@Composable
fun AIPredictionCard() {
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
                Text(
                    text = "AI Predictions",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
                Icon(
                    imageVector = Icons.Filled.Psychology,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )
            }
            Spacer(modifier = Modifier.height(12.dp))
            
            // EUR/USD Prediction
            PredictionRow(
                pair = "EUR/USD",
                direction = "BULLISH",
                confidence = 0.85f,
                target = "1.0950"
            )
            Spacer(modifier = Modifier.height(8.dp))
            
            // GBP/USD Prediction
            PredictionRow(
                pair = "GBP/USD",
                direction = "BEARISH",
                confidence = 0.72f,
                target = "1.2650"
            )
        }
    }
}

@Composable
fun PredictionRow(
    pair: String,
    direction: String,
    confidence: Float,
    target: String
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                text = pair,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Medium
            )
            Text(
                text = "Target: $target",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
            )
        }
        Column(
            horizontalAlignment = Alignment.End
        ) {
            Text(
                text = direction,
                style = MaterialTheme.typography.bodyMedium,
                color = if (direction == "BULLISH") ProfitGreen else LossRed,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "${(confidence * 100).toInt()}% confidence",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
            )
        }
    }
}

@Composable
fun ActiveTradesCard() {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "Active Trades",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(12.dp))
            
            if (getActiveTrades().isEmpty()) {
                Text(
                    text = "No active trades",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                )
            } else {
                getActiveTrades().forEach { trade ->
                    TradeRow(trade = trade)
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }
}

@Composable
fun TradeRow(trade: ActiveTrade) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                text = "${trade.symbol} ${trade.direction}",
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Medium
            )
            Text(
                text = "Entry: ${trade.entryPrice}",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
            )
        }
        Text(
            text = "${if (trade.pnl >= 0) "+" else ""}${trade.pnl}%",
            style = MaterialTheme.typography.bodyMedium,
            color = if (trade.pnl >= 0) ProfitGreen else LossRed,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun MarketStructureCard() {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "Market Structure (ICT/SMC)",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(12.dp))
            
            val structures = listOf(
                StructureInfo("Order Blocks", "3 Bullish, 2 Bearish", OrderBlockColor),
                StructureInfo("Fair Value Gaps", "2 Unfilled", FairValueGapColor),
                StructureInfo("Liquidity Levels", "5 Unswept", LiquidityLevelColor)
            )
            
            structures.forEach { structure ->
                StructureRow(structure = structure)
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

@Composable
fun StructureRow(structure: StructureInfo) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(12.dp)
                    .clip(RoundedCornerShape(6.dp))
                    .background(structure.color)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = structure.name,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Medium
            )
        }
        Text(
            text = structure.count,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
        )
    }
}

// Data classes
data class PerformanceMetric(
    val label: String,
    val value: String,
    val icon: ImageVector,
    val color: Color
)

data class SessionInfo(
    val name: String,
    val time: String,
    val isActive: Boolean
)

data class ActiveTrade(
    val symbol: String,
    val direction: String,
    val entryPrice: String,
    val pnl: Double
)

data class StructureInfo(
    val name: String,
    val count: String,
    val color: Color
)

// Sample data functions
fun getPerformanceMetrics(): List<PerformanceMetric> {
    return listOf(
        PerformanceMetric("Total P&L", "+$2,450", Icons.Filled.TrendingUp, ProfitGreen),
        PerformanceMetric("Win Rate", "68%", Icons.Filled.CheckCircle, ProfitGreen),
        PerformanceMetric("Sharpe Ratio", "1.85", Icons.Filled.Analytics, MaterialTheme.colorScheme.primary),
        PerformanceMetric("Drawdown", "-8.5%", Icons.Filled.TrendingDown, LossRed)
    )
}

fun getActiveTrades(): List<ActiveTrade> {
    return listOf(
        ActiveTrade("EUR/USD", "BUY", "1.0875", 2.3),
        ActiveTrade("GBP/USD", "SELL", "1.2720", -1.1)
    )
}

@Preview(showBackground = true)
@Composable
fun DashboardPreview() {
    AITraderTheme {
        DashboardScreen()
    }
}
