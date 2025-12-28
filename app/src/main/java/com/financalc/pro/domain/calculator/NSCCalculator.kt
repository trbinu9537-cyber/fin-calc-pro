package com.financalc.pro.domain.calculator

import com.financalc.pro.domain.model.Calculator
import com.financalc.pro.domain.model.CalculationResult
import kotlin.math.pow

/**
 * NSC (National Savings Certificate) Calculator
 * Fixed 5-year tenure with compound interest
 */
class NSCCalculator(
    private val principal: Double,
    private val annualRate: Double = 7.7, // Current NSC rate
    private val tenure: Int = 5 // NSC has fixed 5 years
) : Calculator {

    override fun validate(): Boolean {
        return principal >= 1000 && tenure == 5
    }

    override fun calculate(): CalculationResult {
        if (!validate()) {
            return CalculationResult(
                success = false,
                errorMessage = "NSC requires minimum investment of ₹1,000 with 5-year tenure"
            )
        }

        val rate = annualRate / 100
        val maturityAmount = principal * (1 + rate).pow(tenure)
        val interest = maturityAmount - principal

        return CalculationResult(
            success = true,
            value = maturityAmount,
            details = mapOf(
                "maturityAmount" to maturityAmount,
                "principal" to principal,
                "interest" to interest,
                "tenure" to tenure.toDouble()
            )
        )
    }
}
