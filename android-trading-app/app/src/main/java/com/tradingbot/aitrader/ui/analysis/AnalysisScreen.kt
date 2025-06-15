package com.tradingbot.aitrader.ui.analysis

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tradingbot.aitrader.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnalysisScreen() {
    var selectedTimeframe by remember { mutableStateOf("H1") }
    val timeframes = listOf("M15", "H1", "H4", "D1", "W1")
    
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            // Timeframe Selector
            Card(
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = "Timeframe Analysis",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(timeframes) { timeframe ->
                            FilterChip(
                                onClick = { selectedTimeframe = timeframe },
                                label = { Text(timeframe) },
                                selected = selectedTimeframe == timeframe
                            )
                        }
                    }
                }
            }
        }
        
        item {
            // Market Structure
            MarketStructureCard()
        }
        
        item {
            // ICT Concepts
            ICTConceptsCard()
        }
        
        item {
            // AI Predictions
            AIPredictionsCard()
        }
        
        item {
            // Killzone Status
            KillzoneStatusCard()
        }
        
        item {
            // Sentiment Analysis
            SentimentAnalysisCard()
        }
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
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Market Structure",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
                Icon(
                    imageVector = Icons.Filled.TrendingUp,
                    contentDescription = null,
                    tint = ProfitGreen
                )
            }
            Spacer(modifier = Modifier.height(12.dp))
            
            // Current Trend
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Current Trend",
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Medium
                )
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(4.dp))
                        .background(ProfitGreen.copy(alpha = 0.1f))
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = "BULLISH",
                        style = MaterialTheme.typography.labelSmall,
                        color = ProfitGreen,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(8.dp))
            
            // Market Phase
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Market Phase",
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Medium
                )
                Text(
                    text = "MANIPULATION",
                    style = MaterialTheme.typography.bodyMedium,
                    color = WarningOrange,
                    fontWeight = FontWeight.Bold
                )
            }
            
            Spacer(modifier = Modifier.height(8.dp))
            
            // Structure Strength
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Structure Strength",
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Medium
                )
                Text(
                    text = "85%",
                    style = MaterialTheme.typography.bodyMedium,
                    color = ProfitGreen,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
fun ICTConceptsCard() {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "ICT/SMC Concepts",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(12.dp))
            
            val concepts = listOf(
                ICTConcept("Order Blocks", "3 Active", OrderBlockColor, "2 Bullish, 1 Bearish"),
                ICTConcept("Fair Value Gaps", "2 Unfilled", FairValueGapColor, "H1: 1.0885-1.0890"),
                ICTConcept("Breaker Blocks", "1 Active", MaterialTheme.colorScheme.secondary, "Bullish at 1.0860"),
                ICTConcept("Liquidity Levels", "5 Unswept", LiquidityLevelColor, "PDH, PWH, Equal Highs"),
                ICTConcept("Displacement", "Strong", ProfitGreen, "15min candle displacement")
            )
            
            concepts.forEach { concept ->
                ICTConceptRow(concept = concept)
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

@Composable
fun ICTConceptRow(concept: ICTConcept) {
    Column {
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
                        .background(concept.color)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = concept.name,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Medium
                )
            }
            Text(
                text = concept.count,
                style = MaterialTheme.typography.bodyMedium,
                color = concept.color,
                fontWeight = FontWeight.Bold
            )
        }
        if (concept.details.isNotEmpty()) {
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = concept.details,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                modifier = Modifier.padding(start = 20.dp)
            )
        }
    }
}

@Composable
fun AIPredictionsCard() {
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
            
            val predictions = listOf(
                AIPrediction("LSTM Neural Network", "BULLISH", 0.85f, "1.0950"),
                AIPrediction("Random Forest", "BULLISH", 0.72f, "1.0925"),
                AIPrediction("SVM Model", "NEUTRAL", 0.58f, "1.0880"),
                AIPrediction("Ensemble", "BULLISH", 0.78f, "1.0940")
            )
            
            predictions.forEach { prediction ->
                AIPredictionRow(prediction = prediction)
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

@Composable
fun AIPredictionRow(prediction: AIPrediction) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                text = prediction.model,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Medium
            )
            Text(
                text = "Target: ${prediction.target}",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
            )
        }
        Column(
            horizontalAlignment = Alignment.End
        ) {
            Text(
                text = prediction.direction,
                style = MaterialTheme.typography.bodyMedium,
                color = when (prediction.direction) {
                    "BULLISH" -> ProfitGreen
                    "BEARISH" -> LossRed
                    else -> NeutralGray
                },
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "${(prediction.confidence * 100).toInt()}%",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
            )
        }
    }
}

@Composable
fun KillzoneStatusCard() {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "Killzone Status",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(12.dp))
            
            val killzones = listOf(
                KillzoneInfo("London Killzone", "14:00-17:00 WIB", true, "High volatility expected"),
                KillzoneInfo("New York Killzone", "20:00-23:00 WIB", false, "Trend continuation phase"),
                KillzoneInfo("Asian Killzone", "06:00-09:00 WIB", false, "Range trading period")
            )
            
            killzones.forEach { killzone ->
                KillzoneRow(killzone = killzone)
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

@Composable
fun KillzoneRow(killzone: KillzoneInfo) {
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = killzone.name,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Medium
                )
                Text(
                    text = killzone.time,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                )
            }
            Box(
                modifier = Modifier
                    .size(12.dp)
                    .clip(RoundedCornerShape(6.dp))
                    .background(if (killzone.isActive) ProfitGreen else NeutralGray)
            )
        }
        if (killzone.description.isNotEmpty()) {
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = killzone.description,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
            )
        }
    }
}

@Composable
fun SentimentAnalysisCard() {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "Sentiment Analysis",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(12.dp))
            
            val sentiments = listOf(
                SentimentInfo("Overall Market", 0.65f, "Bullish"),
                SentimentInfo("News Sentiment", 0.45f, "Neutral"),
                SentimentInfo("Social Media", 0.78f, "Very Bullish"),
                SentimentInfo("Institutional Flow", 0.55f, "Bullish"),
                SentimentInfo("Retail Sentiment", 0.35f, "Bearish")
            )
            
            sentiments.forEach { sentiment ->
                SentimentRow(sentiment = sentiment)
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

@Composable
fun SentimentRow(sentiment: SentimentInfo) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = sentiment.name,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Medium
        )
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = sentiment.label,
                style = MaterialTheme.typography.bodySmall,
                color = when {
                    sentiment.value >= 0.6f -> ProfitGreen
                    sentiment.value <= 0.4f -> LossRed
                    else -> WarningOrange
                },
                fontWeight = FontWeight.Medium
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "${(sentiment.value * 100).toInt()}%",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
            )
        }
    }
}

// Data classes
data class ICTConcept(
    val name: String,
    val count: String,
    val color: Color,
    val details: String
)

data class AIPrediction(
    val model: String,
    val direction: String,
    val confidence: Float,
    val target: String
)

data class KillzoneInfo(
    val name: String,
    val time: String,
    val isActive: Boolean,
    val description: String
)

data class SentimentInfo(
    val name: String,
    val value: Float,
    val label: String
)

@Preview(showBackground = true)
@Composable
fun AnalysisScreenPreview() {
    AITraderTheme {
        AnalysisScreen()
    }
}
