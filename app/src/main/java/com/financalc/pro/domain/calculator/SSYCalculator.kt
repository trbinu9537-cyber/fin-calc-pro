package com.financalc.pro.domain.calculator

import com.financalc.pro.domain.model.Calculator
import com.financalc.pro.domain.model.CalculationResult
import kotlin.math.pow

/**
 * Sukanya Samriddhi Yojana (SSY) Calculator
 * Government scheme for girl child education
 */
class SSYCalculator(
    private val yearlyInvestment: Double,
    private val annualRate: Double = 8.2, // Current SSY rate
    private val girlAge: Int
) : Calculator {

    override fun validate(): Boolean {
        return yearlyInvestment in 250.0..150000.0 && girlAge in 0..10
    }

    override fun calculate(): CalculationResult {
        if (!validate()) {
            return CalculationResult(
                success = false,
                errorMessage = "SSY requires investment between ₹250-₹1,50,000 per year, girl's age should be 0-10 years"
            )
        }

        val depositYears = 15 // Can deposit for 15 years
        val maturityYears = 21 // Matures when girl turns 21

        var amount = 0.0
        val rate = annualRate / 100

        // Deposit for 15 years
        for (year in 1..depositYears) {
            amount = (amount + yearlyInvestment) * (1 + rate)
        }

        // Continue compounding till maturity (21 years from account opening)
        val remainingYears = maturityYears - depositYears
        amount *= (1 + rate).pow(remainingYears)

        val totalInvestment = yearlyInvestment * depositYears
        val interest = amount - totalInvestment

        return CalculationResult(
            success = true,
            value = amount,
            details = mapOf(
                "maturityAmount" to amount,
                "totalInvestment" to totalInvestment,
                "interest" to interest,
                "maturityAge" to (girlAge + maturityYears).toDouble()
            )
        )
    }
}
