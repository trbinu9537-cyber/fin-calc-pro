package com.financalc.pro.domain.calculator

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class FDCalculatorTest {

    @Test
    fun `calculate FD maturity for valid inputs`() {
        val calculator = FDCalculator(
            principal = 100000.0,
            annualRate = 6.5,
            tenureYears = 5.0,
            compoundingFrequency = 4
        )

        val result = calculator.calculate()

        assertThat(result.success).isTrue()
        assertThat(result.value).isGreaterThan(100000.0)
        assertThat(result.details["interest"]).isGreaterThan(0.0)
    }

    @Test
    fun `validate returns false for negative principal`() {
        val calculator = FDCalculator(
            principal = -10000.0,
            annualRate = 6.5,
            tenureYears = 5.0
        )

        assertThat(calculator.validate()).isFalse()
    }

    @Test
    fun `calculate with monthly compounding`() {
        val calculator = FDCalculator(
            principal = 50000.0,
            annualRate = 7.0,
            tenureYears = 3.0,
            compoundingFrequency = 12
        )

        val result = calculator.calculate()

        assertThat(result.success).isTrue()
        assertThat(result.details["maturityAmount"]).isGreaterThan(50000.0)
    }
}
