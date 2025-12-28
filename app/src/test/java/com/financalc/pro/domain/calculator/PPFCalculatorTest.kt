package com.financalc.pro.domain.calculator

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class PPFCalculatorTest {

    @Test
    fun `calculate PPF maturity for valid inputs`() {
        val calculator = PPFCalculator(
            yearlyInvestment = 150000.0,
            annualRate = 7.1,
            tenure = 15
        )

        val result = calculator.calculate()

        assertThat(result.success).isTrue()
        assertThat(result.value).isGreaterThan(2250000.0) // Total investment
        assertThat(result.details["interest"]).isGreaterThan(0.0)
    }

    @Test
    fun `validate returns false for investment below minimum`() {
        val calculator = PPFCalculator(
            yearlyInvestment = 400.0,
            annualRate = 7.1,
            tenure = 15
        )

        assertThat(calculator.validate()).isFalse()
    }

    @Test
    fun `validate returns false for investment above maximum`() {
        val calculator = PPFCalculator(
            yearlyInvestment = 200000.0,
            annualRate = 7.1,
            tenure = 15
        )

        assertThat(calculator.validate()).isFalse()
    }

    @Test
    fun `validate returns false for tenure less than 15 years`() {
        val calculator = PPFCalculator(
            yearlyInvestment = 100000.0,
            annualRate = 7.1,
            tenure = 10
        )

        assertThat(calculator.validate()).isFalse()
    }

    @Test
    fun `calculate PPF with minimum investment`() {
        val calculator = PPFCalculator(
            yearlyInvestment = 500.0,
            annualRate = 7.1,
            tenure = 15
        )

        val result = calculator.calculate()

        assertThat(result.success).isTrue()
        assertThat(result.value).isGreaterThan(7500.0)
    }
}
