package com.financalc.pro.domain.calculator

import com.financalc.pro.domain.model.Calculator
import com.financalc.pro.domain.model.CalculationResult
import kotlin.math.pow

/**
 * RD (Recurring Deposit) Calculator
 * Monthly deposit scheme with compound interest
 */
class RDCalculator(
    private val monthlyDeposit: Double,
    private val annualRate: Double,
    private val tenureMonths: Int
) : Calculator {

    override fun validate(): Boolean {
        return monthlyDeposit > 0 && annualRate >= 0 && tenureMonths > 0
    }

    override fun calculate(): CalculationResult {
        if (!validate()) {
            return CalculationResult(
                success = false,
                errorMessage = "Invalid input parameters"
            )
        }

        // RD formula: M = P × [(1 + r)^n - 1] / [1 - (1 + r)^(-1/3)]
        // Where: M = Maturity amount, P = Monthly deposit, r = monthly rate, n = tenure in months
        
        val monthlyRate = annualRate / (12 * 100)
        
        val maturityAmount = if (monthlyRate == 0.0) {
            monthlyDeposit * tenureMonths
        } else {
            monthlyDeposit * (((1 + monthlyRate).pow(tenureMonths) - 1) / 
                            (1 - (1 + monthlyRate).pow(-1.0/3)))
        }

        val totalInvestment = monthlyDeposit * tenureMonths
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
