package com.financalc.pro.domain.calculator

import com.financalc.pro.domain.model.Calculator
import com.financalc.pro.domain.model.CalculationResult
import kotlin.math.pow

/**
 * Lumpsum Investment Calculator
 * One-time investment with compound interest
 */
class LumpsumCalculator(
    private val principal: Double,
    private val annualRate: Double,
    private val tenureYears: Int
) : Calculator {

    override fun validate(): Boolean {
        return principal > 0 && annualRate >= 0 && tenureYears > 0
    }

    override fun calculate(): CalculationResult {
        if (!validate()) {
            return CalculationResult(
                success = false,
                errorMessage = "Invalid input parameters"
            )
        }

        val rate = annualRate / 100
        val futureValue = principal * (1 + rate).pow(tenureYears)
        val returns = futureValue - principal

        return CalculationResult(
            success = true,
            value = futureValue,
            details = mapOf(
                "futureValue" to futureValue,
                "investment" to principal,
                "returns" to returns,
                "absoluteReturn" to ((returns / principal) * 100)
            )
        )
    }
}
