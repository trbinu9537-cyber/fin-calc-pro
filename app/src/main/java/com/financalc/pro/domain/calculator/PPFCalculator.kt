package com.financalc.pro.domain.calculator

import com.financalc.pro.domain.model.Calculator
import com.financalc.pro.domain.model.CalculationResult
import kotlin.math.pow

/**
 * PPF (Public Provident Fund) Calculator
 * Tenure is fixed at 15 years, rate is as per government norms
 */
class PPFCalculator(
    private val yearlyInvestment: Double,
    private val annualRate: Double = 7.1, // Current PPF rate
    private val tenure: Int = 15 // PPF has minimum 15 years lock-in
) : Calculator {

    override fun validate(): Boolean {
        return yearlyInvestment in 500.0..150000.0 && tenure >= 15
    }

    override fun calculate(): CalculationResult {
        if (!validate()) {
            return CalculationResult(
                success = false,
                errorMessage = "PPF investment must be between ₹500 and ₹1,50,000 per year, minimum tenure is 15 years"
            )
        }

        var maturityAmount = 0.0
        val rate = annualRate / 100

        // PPF compounds annually
        for (year in 1..tenure) {
            maturityAmount = (maturityAmount + yearlyInvestment) * (1 + rate)
        }

        val totalInvestment = yearlyInvestment * tenure
        val interest = maturityAmount - totalInvestment

        return CalculationResult(
            success = true,
            value = maturityAmount,
            details = mapOf(
                "maturityAmount" to maturityAmount,
                "totalInvestment" to totalInvestment,
                "interest" to interest
            )
        )
    }
}
