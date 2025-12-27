package com.fincalcpro.engine

import kotlin.math.pow

/**
 * Core calculation engine for all financial calculators
 * Implements Indian financial calculation logic
 */
object CalculatorEngine {

    // Data classes for results
    data class EMIResult(
        val monthlyEMI: Double,
        val totalInterest: Double,
        val totalPayment: Double,
        val principal: Double
    )

    data class FDResult(
        val maturityAmount: Double,
        val totalDeposit: Double,
        val totalInterest: Double
    )

    data class RDResult(
        val maturityAmount: Double,
        val totalDeposit: Double,
        val totalInterest: Double
    )

    data class SIPResult(
        val investedAmount: Double,
        val estimatedReturns: Double,
        val totalValue: Double
    )

    data class PPFResult(
        val maturityAmount: Double,
        val totalDeposit: Double,
        val totalInterest: Double,
        val yearWiseBreakdown: List<YearData>
    )

    data class YearData(
        val year: Int,
        val openingBalance: Double,
        val deposit: Double,
        val interest: Double,
        val closingBalance: Double
    )

    data class SSYResult(
        val maturityAmount: Double,
        val totalDeposit: Double,
        val totalInterest: Double,
        val maturityYear: Int
    )

    data class GratuityResult(
        val gratuityAmount: Double
    )

    data class NPSResult(
        val maturityCorpus: Double,
        val totalInvestment: Double,
        val totalReturns: Double,
        val annuityAmount: Double,
        val lumpsum: Double,
        val monthlyPension: Double
    )

    data class LumpsumResult(
        val futureValue: Double,
        val totalInvestment: Double,
        val totalReturns: Double
    )

    /**
     * EMI Calculator
     * Formula: EMI = P × r × (1 + r)^n / ((1 + r)^n - 1)
     * where P = principal, r = monthly interest rate, n = number of months
     */
    fun calculateEMI(principal: Double, annualRate: Double, tenureMonths: Int): EMIResult {
        val monthlyRate = annualRate / (12 * 100)
        val emi = if (monthlyRate > 0) {
            principal * monthlyRate * (1 + monthlyRate).pow(tenureMonths) /
                    ((1 + monthlyRate).pow(tenureMonths) - 1)
        } else {
            principal / tenureMonths
        }
        
        val totalPayment = emi * tenureMonths
        val totalInterest = totalPayment - principal
        
        return EMIResult(
            monthlyEMI = emi,
            totalInterest = totalInterest,
            totalPayment = totalPayment,
            principal = principal
        )
    }

    /**
     * FD Calculator (Cumulative - STDR)
     * Formula: A = P(1 + r/n)^(nt)
     * where P = principal, r = annual rate, n = compounding frequency, t = time in years
     */
    fun calculateFD(
        principal: Double,
        annualRate: Double,
        tenureMonths: Int,
        compoundingFrequency: Int = 4
    ): FDResult {
        val years = tenureMonths / 12.0
        val rate = annualRate / 100
        val amount = principal * (1 + rate / compoundingFrequency).pow(compoundingFrequency * years)
        
        return FDResult(
            maturityAmount = amount,
            totalDeposit = principal,
            totalInterest = amount - principal
        )
    }

    /**
     * RD Calculator (Post Office style - Quarterly compounding)
     * Formula: M = P × n × (n + 1) × (2n + 1) / 6 × (r / 400)
     * Approximation using future value of annuity with quarterly compounding
     */
    fun calculateRD(monthlyDeposit: Double, annualRate: Double, tenureMonths: Int): RDResult {
        val quarterlyRate = annualRate / (4 * 100)
        val quarters = tenureMonths / 3.0
        
        var maturityAmount = 0.0
        for (i in 1..tenureMonths) {
            val remainingQuarters = (tenureMonths - i) / 3.0
            maturityAmount += monthlyDeposit * (1 + quarterlyRate).pow(remainingQuarters)
        }
        
        val totalDeposit = monthlyDeposit * tenureMonths
        
        return RDResult(
            maturityAmount = maturityAmount,
            totalDeposit = totalDeposit,
            totalInterest = maturityAmount - totalDeposit
        )
    }

    /**
     * SIP Calculator
     * Formula: FV = P × ({[1 + i]^n - 1} / i) × (1 + i)
     * where P = monthly investment, i = monthly return rate, n = number of months
     */
    fun calculateSIP(monthlyInvestment: Double, expectedReturnRate: Double, years: Int): SIPResult {
        val months = years * 12
        val monthlyRate = expectedReturnRate / (12 * 100)
        
        val futureValue = if (monthlyRate > 0) {
            monthlyInvestment * (((1 + monthlyRate).pow(months) - 1) / monthlyRate) * (1 + monthlyRate)
        } else {
            monthlyInvestment * months
        }
        
        val investedAmount = monthlyInvestment * months
        
        return SIPResult(
            investedAmount = investedAmount,
            estimatedReturns = futureValue - investedAmount,
            totalValue = futureValue
        )
    }

    /**
     * PPF Calculator (15 years lock-in, option to extend by 5 years)
     * Interest is compounded annually
     */
    fun calculatePPF(yearlyDeposit: Double, rate: Double = 7.1, years: Int = 15): PPFResult {
        val yearWiseData = mutableListOf<YearData>()
        var balance = 0.0
        
        for (year in 1..years) {
            val openingBalance = balance
            val deposit = yearlyDeposit
            val interest = (openingBalance + deposit) * rate / 100
            balance = openingBalance + deposit + interest
            
            yearWiseData.add(
                YearData(
                    year = year,
                    openingBalance = openingBalance,
                    deposit = deposit,
                    interest = interest,
                    closingBalance = balance
                )
            )
        }
        
        return PPFResult(
            maturityAmount = balance,
            totalDeposit = yearlyDeposit * years,
            totalInterest = balance - (yearlyDeposit * years),
            yearWiseBreakdown = yearWiseData
        )
    }

    /**
     * SSY Calculator (Sukanya Samriddhi Yojana)
     * Deposits allowed for 15 years, interest continues until year 21
     */
    fun calculateSSY(yearlyDeposit: Double, rate: Double = 8.2, girlChildAge: Int): SSYResult {
        var balance = 0.0
        
        // Deposits for first 15 years
        for (year in 1..15) {
            balance += yearlyDeposit
            balance += balance * rate / 100
        }
        
        // Interest only for remaining years until maturity (21 years total)
        for (year in 16..21) {
            balance += balance * rate / 100
        }
        
        return SSYResult(
            maturityAmount = balance,
            totalDeposit = yearlyDeposit * 15,
            totalInterest = balance - (yearlyDeposit * 15),
            maturityYear = girlChildAge + 21
        )
    }

    /**
     * Gratuity Calculator
     * If covered by Act: (15 * lastDrawnSalary * years) / 26
     * If not covered: (15 * lastDrawnSalary * years) / 30
     */
    fun calculateGratuity(
        basicPlusDA: Double,
        yearsOfService: Double,
        isCoveredByAct: Boolean = true
    ): GratuityResult {
        val divisor = if (isCoveredByAct) 26 else 30
        val gratuityAmount = (15 * basicPlusDA * yearsOfService) / divisor
        
        return GratuityResult(gratuityAmount = gratuityAmount)
    }

    /**
     * NPS Calculator
     * Calculates retirement corpus and pension options
     */
    fun calculateNPS(
        monthlyContribution: Double,
        expectedReturn: Double,
        years: Int,
        annuityPercentage: Double = 40.0
    ): NPSResult {
        val months = years * 12
        val monthlyRate = expectedReturn / (12 * 100)
        
        // Calculate maturity corpus
        val corpus = if (monthlyRate > 0) {
            monthlyContribution * (((1 + monthlyRate).pow(months) - 1) / monthlyRate) * (1 + monthlyRate)
        } else {
            monthlyContribution * months
        }
        
        val totalInvestment = monthlyContribution * months
        val annuityAmount = corpus * annuityPercentage / 100
        val lumpsum = corpus - annuityAmount
        
        // Assuming 6% annuity return for monthly pension
        val monthlyPension = (annuityAmount * 0.06) / 12
        
        return NPSResult(
            maturityCorpus = corpus,
            totalInvestment = totalInvestment,
            totalReturns = corpus - totalInvestment,
            annuityAmount = annuityAmount,
            lumpsum = lumpsum,
            monthlyPension = monthlyPension
        )
    }

    /**
     * Lumpsum Calculator
     * Formula: FV = PV × (1 + r)^n
     */
    fun calculateLumpsum(principal: Double, expectedReturn: Double, years: Int): LumpsumResult {
        val rate = expectedReturn / 100
        val futureValue = principal * (1 + rate).pow(years)
        
        return LumpsumResult(
            futureValue = futureValue,
            totalInvestment = principal,
            totalReturns = futureValue - principal
        )
    }

    /**
     * Inflation Calculator
     * Formula: FutureValue = PresentValue * (1 + inflationRate/100)^years
     */
    fun calculateInflationAdjustedValue(
        presentValue: Double,
        inflationRate: Double,
        years: Int
    ): Double {
        val rate = inflationRate / 100
        return presentValue * (1 + rate).pow(years)
    }
}
