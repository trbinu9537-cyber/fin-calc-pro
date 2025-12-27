package com.fincalcpro.ui.mutualfunds

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.fincalcpro.engine.CalculatorEngine
import com.fincalcpro.ui.components.*
import com.fincalcpro.utils.IndianNumberFormatter
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry

@Composable
fun SIPCalculatorScreen(navController: NavController) {
    var monthlyInvestment by remember { mutableStateOf("") }
    var expectedReturn by remember { mutableStateOf("") }
    var timePeriod by remember { mutableStateOf("") }
    var result by remember { mutableStateOf<CalculatorEngine.SIPResult?>(null) }
    
    CalculatorScaffold(
        title = "SIP Calculator",
        navController = navController,
        onCalculate = {
            val investment = monthlyInvestment.toDoubleOrNull() ?: 0.0
            val returnRate = expectedReturn.toDoubleOrNull() ?: 0.0
            val years = timePeriod.toIntOrNull() ?: 0
            
            if (investment > 0 && returnRate > 0 && years > 0) {
                result = CalculatorEngine.calculateSIP(investment, returnRate, years)
            }
        }
    ) { paddingValues ->
        ScrollableContent(paddingValues = paddingValues) {
            Text(
                text = "Calculate returns on your Systematic Investment Plan (SIP) in mutual funds.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            
            InputField(
                value = monthlyInvestment,
                onValueChange = { monthlyInvestment = it },
                label = "Monthly Investment",
                prefix = "₹"
            )
            
            PercentageInputField(
                value = expectedReturn,
                onValueChange = { expectedReturn = it },
                label = "Expected Return Rate (per annum)"
            )
            
            YearInputField(
                value = timePeriod,
                onValueChange = { timePeriod = it },
                label = "Investment Period"
            )
            
            result?.let { sipResult ->
                Spacer(modifier = Modifier.height(8.dp))
                
                HighlightResultCard(
                    label = "Expected Maturity Value",
                    value = IndianNumberFormatter.format(sipResult.totalValue)
                )
                
                ResultCard(
                    title = "Investment Summary",
                    results = listOf(
                        "Total Investment" to IndianNumberFormatter.format(sipResult.investedAmount),
                        "Estimated Returns" to IndianNumberFormatter.format(sipResult.estimatedReturns),
                        "Total Value" to IndianNumberFormatter.format(sipResult.totalValue)
                    )
                )
                
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = "Investment Breakdown",
                            style = MaterialTheme.typography.titleLarge,
                            color = MaterialTheme.colorScheme.primary
                        )
                        
                        AndroidView(
                            factory = { context ->
                                PieChart(context).apply {
                                    description.isEnabled = false
                                    setUsePercentValues(false)
                                    setEntryLabelTextSize(12f)
                                    legend.isEnabled = true
                                    setDrawHoleEnabled(true)
                                    setHoleColor(android.graphics.Color.TRANSPARENT)
                                    holeRadius = 40f
                                    transparentCircleRadius = 45f
                                }
                            },
                            update = { chart ->
                                val entries = listOf(
                                    PieEntry(sipResult.investedAmount.toFloat(), "Investment"),
                                    PieEntry(sipResult.estimatedReturns.toFloat(), "Returns")
                                )
                                
                                val dataSet = PieDataSet(entries, "").apply {
                                    colors = listOf(
                                        android.graphics.Color.rgb(14, 165, 233),  // SIPBlue
                                        android.graphics.Color.rgb(16, 185, 129)   // EmeraldGreen
                                    )
                                    valueTextSize = 12f
                                    valueTextColor = android.graphics.Color.BLACK
                                }
                                
                                chart.data = PieData(dataSet)
                                chart.invalidate()
                            },
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(top = 8.dp)
                        )
                    }
                }
            }
        }
    }
}
