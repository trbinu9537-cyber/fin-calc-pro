package com.fincalcpro.ui.postoffice

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.fincalcpro.engine.CalculatorEngine
import com.fincalcpro.ui.components.*
import com.fincalcpro.utils.IndianNumberFormatter

@Composable
fun PPFCalculatorScreen(navController: NavController) {
    var yearlyDeposit by remember { mutableStateOf("") }
    var interestRate by remember { mutableStateOf("7.1") }
    var extendPPF by remember { mutableStateOf(false) }
    var result by remember { mutableStateOf<CalculatorEngine.PPFResult?>(null) }
    
    CalculatorScaffold(
        title = "PPF Calculator",
        navController = navController,
        onCalculate = {
            val deposit = yearlyDeposit.toDoubleOrNull() ?: 0.0
            val rate = interestRate.toDoubleOrNull() ?: 7.1
            val years = if (extendPPF) 20 else 15
            
            if (deposit > 0 && rate > 0) {
                result = CalculatorEngine.calculatePPF(deposit, rate, years)
            }
        }
    ) { paddingValues ->
        ScrollableContent(paddingValues = paddingValues) {
            Text(
                text = "Public Provident Fund (PPF) - Long-term savings scheme with tax benefits. Minimum: ₹500/year, Maximum: ₹1,50,000/year.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            
            InputField(
                value = yearlyDeposit,
                onValueChange = { yearlyDeposit = it },
                label = "Yearly Deposit",
                prefix = "₹"
            )
            
            PercentageInputField(
                value = interestRate,
                onValueChange = { interestRate = it },
                label = "Interest Rate (per annum)"
            )
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
            ) {
                Text(
                    text = "Extend PPF by 5 years (20 years total)",
                    style = MaterialTheme.typography.bodyMedium
                )
                Switch(
                    checked = extendPPF,
                    onCheckedChange = { extendPPF = it }
                )
            }
            
            result?.let { ppfResult ->
                Spacer(modifier = Modifier.height(8.dp))
                
                HighlightResultCard(
                    label = "Maturity Amount",
                    value = IndianNumberFormatter.format(ppfResult.maturityAmount)
                )
                
                ResultCard(
                    title = "PPF Summary",
                    results = listOf(
                        "Total Deposits" to IndianNumberFormatter.format(ppfResult.totalDeposit),
                        "Total Interest Earned" to IndianNumberFormatter.format(ppfResult.totalInterest),
                        "Maturity Value" to IndianNumberFormatter.format(ppfResult.maturityAmount)
                    )
                )
                
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = "Year-wise Breakdown",
                            style = MaterialTheme.typography.titleLarge,
                            color = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.padding(bottom = 16.dp)
                        )
                        
                        // Table Header
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "Year",
                                style = MaterialTheme.typography.labelMedium,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.weight(0.8f)
                            )
                            Text(
                                text = "Opening",
                                style = MaterialTheme.typography.labelMedium,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.weight(1.5f)
                            )
                            Text(
                                text = "Deposit",
                                style = MaterialTheme.typography.labelMedium,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.weight(1.5f)
                            )
                            Text(
                                text = "Interest",
                                style = MaterialTheme.typography.labelMedium,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.weight(1.5f)
                            )
                            Text(
                                text = "Closing",
                                style = MaterialTheme.typography.labelMedium,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.weight(1.5f)
                            )
                        }
                        
                        Divider()
                        
                        // Show first 5 and last 5 years
                        val displayYears = if (ppfResult.yearWiseBreakdown.size > 10) {
                            ppfResult.yearWiseBreakdown.take(5) + 
                            listOf(null) + // separator
                            ppfResult.yearWiseBreakdown.takeLast(5)
                        } else {
                            ppfResult.yearWiseBreakdown
                        }
                        
                        displayYears.forEach { yearData ->
                            if (yearData == null) {
                                // Separator
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 4.dp),
                                    horizontalArrangement = Arrangement.Center
                                ) {
                                    Text(
                                        text = "...",
                                        style = MaterialTheme.typography.bodySmall
                                    )
                                }
                            } else {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 4.dp),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text(
                                        text = "${yearData.year}",
                                        style = MaterialTheme.typography.bodySmall,
                                        modifier = Modifier.weight(0.8f)
                                    )
                                    Text(
                                        text = formatCompact(yearData.openingBalance),
                                        style = MaterialTheme.typography.bodySmall,
                                        modifier = Modifier.weight(1.5f)
                                    )
                                    Text(
                                        text = formatCompact(yearData.deposit),
                                        style = MaterialTheme.typography.bodySmall,
                                        modifier = Modifier.weight(1.5f)
                                    )
                                    Text(
                                        text = formatCompact(yearData.interest),
                                        style = MaterialTheme.typography.bodySmall,
                                        modifier = Modifier.weight(1.5f)
                                    )
                                    Text(
                                        text = formatCompact(yearData.closingBalance),
                                        style = MaterialTheme.typography.bodySmall,
                                        modifier = Modifier.weight(1.5f)
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

private fun formatCompact(amount: Double): String {
    return when {
        amount >= 10000000 -> "%.1fCr".format(amount / 10000000)
        amount >= 100000 -> "%.1fL".format(amount / 100000)
        amount >= 1000 -> "%.1fK".format(amount / 1000)
        else -> "%.0f".format(amount)
    }
}
