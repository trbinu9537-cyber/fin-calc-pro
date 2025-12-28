package com.financalc.pro.domain.calculator

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class LumpsumCalculatorTest {

    @Test
    fun `calculate lumpsum returns for valid inputs`() {
        val calculator = LumpsumCalculator(
            principal = 100000.0,
            annualRate = 12.0,
            tenureYears = 10
        )

        val result = calculator.calculate()

        assertThat(result.success).isTrue()
        assertThat(result.value).isGreaterThan(100000.0)
        assertThat(result.details["returns"]).isGreaterThan(0.0)
    }

    @Test
    fun `calculate lumpsum with zero interest`() {
        val calculator = LumpsumCalculator(
            principal = 50000.0,
            annualRate = 0.0,
            tenureYears = 5
        )

        val result = calculator.calculate()

        assertThat(result.success).isTrue()
        assertThat(result.value).isEqualTo(50000.0)
        assertThat(result.details["returns"]).isEqualTo(0.0)
    }

    @Test
    fun `validate returns false for negative principal`() {
        val calculator = LumpsumCalculator(
            principal = -10000.0,
            annualRate = 12.0,
            tenureYears = 5
        )

        assertThat(calculator.validate()).isFalse()
    }

    @Test
    fun `calculate absolute return percentage`() {
        val calculator = LumpsumCalculator(
            principal = 100000.0,
            annualRate = 10.0,
            tenureYears = 5
        )

        val result = calculator.calculate()

        assertThat(result.success).isTrue()
        assertThat(result.details["absoluteReturn"]).isGreaterThan(0.0)
    }
}
