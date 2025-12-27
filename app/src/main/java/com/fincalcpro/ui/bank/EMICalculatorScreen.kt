package com.fincalcpro.ui.bank

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
fun EMICalculatorScreen(navController: NavController) {
    var loanAmount by remember { mutableStateOf("") }
    var interestRate by remember { mutableStateOf("") }
    var tenure by remember { mutableStateOf("") }
    var tenureInYears by remember { mutableStateOf(true) }
    var result by remember { mutableStateOf<CalculatorEngine.EMIResult?>(null) }
    
    CalculatorScaffold(
        title = "EMI Calculator",
        navController = navController,
        onCalculate = {
            val principal = loanAmount.toDoubleOrNull() ?: 0.0
            val rate = interestRate.toDoubleOrNull() ?: 0.0
            val period = tenure.toDoubleOrNull() ?: 0.0
            
            if (principal > 0 && rate > 0 && period > 0) {
                val tenureMonths = if (tenureInYears) (period * 12).toInt() else period.toInt()
                result = CalculatorEngine.calculateEMI(principal, rate, tenureMonths)
            }
        }
    ) { paddingValues ->
        ScrollableContent(paddingValues = paddingValues) {
            Text(
                text = "Calculate your Equated Monthly Installment (EMI) for home loans, car loans, or personal loans.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            
            InputField(
                value = loanAmount,
                onValueChange = { loanAmount = it },
                label = "Loan Amount",
                prefix = "₹"
            )
            
            PercentageInputField(
                value = interestRate,
                onValueChange = { interestRate = it },
                label = "Interest Rate (per annum)"
            )
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                InputField(
                    value = tenure,
                    onValueChange = { tenure = it },
                    label = "Loan Tenure",
                    prefix = "",
                    showPrefix = false,
                    modifier = Modifier.weight(1f)
                )
                
                Column(modifier = Modifier.weight(1f)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        FilterChip(
                            selected = tenureInYears,
                            onClick = { tenureInYears = true },
                            label = { Text("Years") },
                            modifier = Modifier.weight(1f)
                        )
                        FilterChip(
                            selected = !tenureInYears,
                            onClick = { tenureInYears = false },
                            label = { Text("Months") },
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
            }
            
            result?.let { emiResult ->
                Spacer(modifier = Modifier.height(8.dp))
                
                HighlightResultCard(
                    label = "Monthly EMI",
                    value = IndianNumberFormatter.format(emiResult.monthlyEMI)
                )
                
                ResultCard(
                    title = "Loan Details",
                    results = listOf(
                        "Principal Amount" to IndianNumberFormatter.format(emiResult.principal),
                        "Total Interest" to IndianNumberFormatter.format(emiResult.totalInterest),
                        "Total Payment" to IndianNumberFormatter.format(emiResult.totalPayment)
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
                            text = "Payment Breakdown",
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
                                    PieEntry(emiResult.principal.toFloat(), "Principal"),
                                    PieEntry(emiResult.totalInterest.toFloat(), "Interest")
                                )
                                
                                val dataSet = PieDataSet(entries, "").apply {
                                    colors = listOf(
                                        android.graphics.Color.rgb(16, 185, 129), // EmeraldGreen
                                        android.graphics.Color.rgb(59, 130, 246)  // Blue
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
