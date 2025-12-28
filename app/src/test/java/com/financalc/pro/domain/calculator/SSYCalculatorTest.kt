package com.financalc.pro.domain.calculator

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class SSYCalculatorTest {

    @Test
    fun `calculate SSY maturity for valid inputs`() {
        val calculator = SSYCalculator(
            yearlyInvestment = 150000.0,
            annualRate = 8.2,
            girlAge = 5
        )

        val result = calculator.calculate()

        assertThat(result.success).isTrue()
        assertThat(result.value).isGreaterThan(2250000.0)
        assertThat(result.details["maturityAge"]).isEqualTo(26.0)
    }

    @Test
    fun `validate returns false for investment below minimum`() {
        val calculator = SSYCalculator(
            yearlyInvestment = 200.0,
            annualRate = 8.2,
            girlAge = 5
        )

        assertThat(calculator.validate()).isFalse()
    }

    @Test
    fun `validate returns false for investment above maximum`() {
        val calculator = SSYCalculator(
            yearlyInvestment = 200000.0,
            annualRate = 8.2,
            girlAge = 5
        )

        assertThat(calculator.validate()).isFalse()
    }

    @Test
    fun `validate returns false for girl age above 10`() {
        val calculator = SSYCalculator(
            yearlyInvestment = 100000.0,
            annualRate = 8.2,
            girlAge = 15
        )

        assertThat(calculator.validate()).isFalse()
    }

    @Test
    fun `calculate SSY with minimum investment`() {
        val calculator = SSYCalculator(
            yearlyInvestment = 250.0,
            annualRate = 8.2,
            girlAge = 0
        )

        val result = calculator.calculate()

        assertThat(result.success).isTrue()
        assertThat(result.value).isGreaterThan(3750.0)
    }
}
