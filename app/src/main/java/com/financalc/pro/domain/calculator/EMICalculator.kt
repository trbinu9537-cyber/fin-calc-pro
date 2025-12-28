package com.financalc.pro.domain.calculator

import com.financalc.pro.domain.model.Calculator
import com.financalc.pro.domain.model.CalculationResult
import kotlin.math.pow

/**
 * EMI (Equated Monthly Installment) Calculator
 * Used for Home Loan, Personal Loan, Car Loan, etc.
 */
class EMICalculator(
    private val principal: Double,
    private val annualRate: Double,
    private val tenureMonths: Int
) : Calculator {

    override fun validate(): Boolean {
        return principal > 0 && annualRate >= 0 && tenureMonths > 0
    }

    override fun calculate(): CalculationResult {
        if (!validate()) {
            return CalculationResult(
                success = false,
                errorMessage = "Invalid input: Principal must be positive, rate must be non-negative, tenure must be positive"
            )
        }

        if (annualRate == 0.0) {
            val emi = principal / tenureMonths
            val totalAmount = principal
            return CalculationResult(
                success = true,
                value = emi,
                details = mapOf(
                    "emi" to emi,
                    "totalAmount" to totalAmount,
                    "totalInterest" to 0.0,
                    "principal" to principal
                )
            )
        }

        val monthlyRate = annualRate / (12 * 100)
        val emi = (principal * monthlyRate * (1 + monthlyRate).pow(tenureMonths)) /
                ((1 + monthlyRate).pow(tenureMonths) - 1)
        val totalAmount = emi * tenureMonths
        val totalInterest = totalAmount - principal

        return CalculationResult(
            success = true,
            value = emi,
            details = mapOf(
                "emi" to emi,
                "totalAmount" to totalAmount,
                "totalInterest" to totalInterest,
                "principal" to principal
            )
        )
    }
}
