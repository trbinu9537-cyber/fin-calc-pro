package com.financalc.pro.domain.calculator

import com.financalc.pro.domain.model.Calculator
import com.financalc.pro.domain.model.CalculationResult
import kotlin.math.pow

/**
 * SIP (Systematic Investment Plan) Calculator
 */
class SIPCalculator(
    private val monthlyInvestment: Double,
    private val annualRate: Double,
    private val tenureMonths: Int
) : Calculator {

    override fun validate(): Boolean {
        return monthlyInvestment > 0 && annualRate >= 0 && tenureMonths > 0
    }

    override fun calculate(): CalculationResult {
        if (!validate()) {
            return CalculationResult(
                success = false,
                errorMessage = "Invalid input: Monthly investment must be positive, rate must be non-negative, tenure must be positive"
            )
        }

        val monthlyRate = annualRate / (12 * 100)
        val futureValue = if (monthlyRate == 0.0) {
            monthlyInvestment * tenureMonths
        } else {
            monthlyInvestment * (((1 + monthlyRate).pow(tenureMonths) - 1) / monthlyRate) * (1 + monthlyRate)
        }

        val totalInvestment = monthlyInvestment * tenureMonths
        val returns = futureValue - totalInvestment

        return CalculationResult(
            success = true,
            value = futureValue,
            details = mapOf(
                "futureValue" to futureValue,
                "totalInvestment" to totalInvestment,
                "returns" to returns
            )
        )
    }
}
