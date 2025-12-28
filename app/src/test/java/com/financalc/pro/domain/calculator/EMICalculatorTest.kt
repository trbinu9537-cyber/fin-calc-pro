package com.financalc.pro.domain.calculator

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class EMICalculatorTest {

    @Test
    fun `calculate EMI for valid inputs`() {
        val calculator = EMICalculator(
            principal = 1000000.0,
            annualRate = 8.5,
            tenureMonths = 240
        )

        val result = calculator.calculate()

        assertThat(result.success).isTrue()
        assertThat(result.value).isGreaterThan(0.0)
        assertThat(result.details["totalInterest"]).isGreaterThan(0.0)
    }

    @Test
    fun `calculate EMI with zero interest rate`() {
        val calculator = EMICalculator(
            principal = 120000.0,
            annualRate = 0.0,
            tenureMonths = 12
        )

        val result = calculator.calculate()

        assertThat(result.success).isTrue()
        assertThat(result.value).isEqualTo(10000.0)
        assertThat(result.details["totalInterest"]).isEqualTo(0.0)
    }

    @Test
    fun `validate returns false for negative principal`() {
        val calculator = EMICalculator(
            principal = -1000.0,
            annualRate = 8.5,
            tenureMonths = 12
        )

        assertThat(calculator.validate()).isFalse()
    }

    @Test
    fun `validate returns false for negative tenure`() {
        val calculator = EMICalculator(
            principal = 100000.0,
            annualRate = 8.5,
            tenureMonths = -12
        )

        assertThat(calculator.validate()).isFalse()
    }

    @Test
    fun `calculate returns error for invalid inputs`() {
        val calculator = EMICalculator(
            principal = -1000.0,
            annualRate = 8.5,
            tenureMonths = 12
        )

        val result = calculator.calculate()

        assertThat(result.success).isFalse()
        assertThat(result.errorMessage).isNotNull()
    }
}
