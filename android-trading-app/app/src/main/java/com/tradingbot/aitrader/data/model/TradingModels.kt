package com.tradingbot.aitrader.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "trades")
data class Trade(
    @PrimaryKey val id: String,
    val symbol: String,
    val type: TradeType,
    val direction: TradeDirection,
    val entryPrice: Double,
    val exitPrice: Double? = null,
    val quantity: Double,
    val stopLoss: Double,
    val takeProfit: Double,
    val entryTime: Date,
    val exitTime: Date? = null,
    val status: TradeStatus,
    val profit: Double = 0.0,
    val riskRewardRatio: Double,
    val strategy: String, // ICT, SMC, etc.
    val timeframe: String,
    val notes: String = ""
)

enum class TradeType {
    MARKET, LIMIT, STOP
}

enum class TradeDirection {
    BUY, SELL
}

enum class TradeStatus {
    PENDING, ACTIVE, CLOSED, CANCELLED
}

@Entity(tableName = "market_analysis")
data class MarketAnalysis(
    @PrimaryKey val id: String,
    val symbol: String,
    val timeframe: String,
    val timestamp: Date,
    val marketStructure: MarketStructure,
    val orderBlocks: List<OrderBlock>,
    val fairValueGaps: List<FairValueGap>,
    val liquidityLevels: List<LiquidityLevel>,
    val aiPrediction: AIPrediction,
    val sentiment: MarketSentiment
)

data class OrderBlock(
    val id: String,
    val type: OrderBlockType,
    val highPrice: Double,
    val lowPrice: Double,
    val timestamp: Date,
    val strength: Double, // 0.0 to 1.0
    val tested: Boolean = false
)

enum class OrderBlockType {
    BULLISH, BEARISH, BREAKER_BULLISH, BREAKER_BEARISH
}

data class FairValueGap(
    val id: String,
    val highPrice: Double,
    val lowPrice: Double,
    val timestamp: Date,
    val filled: Boolean = false,
    val strength: Double
)

data class LiquidityLevel(
    val id: String,
    val price: Double,
    val type: LiquidityType,
    val timestamp: Date,
    val swept: Boolean = false
)

enum class LiquidityType {
    DAILY_HIGH, DAILY_LOW, WEEKLY_HIGH, WEEKLY_LOW,
    MONTHLY_HIGH, MONTHLY_LOW, EQUAL_HIGHS, EQUAL_LOWS
}

data class MarketStructure(
    val trend: Trend,
    val phase: MarketPhase,
    val strength: Double,
    val lastUpdate: Date
)

enum class Trend {
    BULLISH, BEARISH, SIDEWAYS
}

enum class MarketPhase {
    ACCUMULATION, MANIPULATION, DISTRIBUTION, RANGE
}

data class AIPrediction(
    val direction: TradeDirection,
    val confidence: Double, // 0.0 to 1.0
    val targetPrice: Double,
    val timeHorizon: String, // "1H", "4H", "1D", etc.
    val modelUsed: String, // "LSTM", "RandomForest", etc.
    val features: Map<String, Double>
)

data class MarketSentiment(
    val overall: Double, // -1.0 to 1.0 (bearish to bullish)
    val news: Double,
    val social: Double,
    val institutional: Double,
    val retail: Double,
    val lastUpdate: Date
)

@Entity(tableName = "performance_metrics")
data class PerformanceMetrics(
    @PrimaryKey val id: String,
    val date: Date,
    val totalTrades: Int,
    val winningTrades: Int,
    val losingTrades: Int,
    val winRate: Double,
    val profitFactor: Double,
    val sharpeRatio: Double,
    val maxDrawdown: Double,
    val totalReturn: Double,
    val dailyPnL: Double
)
