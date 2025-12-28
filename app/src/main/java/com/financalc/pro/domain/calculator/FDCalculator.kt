package com.financalc.pro.domain.calculator

import com.financalc.pro.domain.model.Calculator
import com.financalc.pro.domain.model.CalculationResult
import kotlin.math.pow

/**
 * Fixed Deposit Calculator
 */
class FDCalculator(
    private val principal: Double,
    private val annualRate: Double,
    private val tenureYears: Double,
    private val compoundingFrequency: Int = 4 // Quarterly by default
) : Calculator {

    override fun validate(): Boolean {
        return principal > 0 && annualRate >= 0 && tenureYears > 0 && compoundingFrequency > 0
    }

    override fun calculate(): CalculationResult {
        if (!validate()) {
            return CalculationResult(
                success = false,
                errorMessage = "Invalid input parameters"
            )
        }

        val rate = annualRate / 100
        val maturityAmount = principal * (1 + rate / compoundingFrequency).pow(compoundingFrequency * tenureYears)
        val interest = maturityAmount - principal

        return CalculationResult(
            success = true,
            value = maturityAmount,
            details = mapOf(
                "maturityAmount" to maturityAmount,
                "principal" to principal,
                "interest" to interest
            )
        )
    }
}
