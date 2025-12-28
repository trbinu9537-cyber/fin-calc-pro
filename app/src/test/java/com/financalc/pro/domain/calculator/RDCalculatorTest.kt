package com.financalc.pro.domain.calculator

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class RDCalculatorTest {

    @Test
    fun `calculate RD maturity for valid inputs`() {
        val calculator = RDCalculator(
            monthlyDeposit = 5000.0,
            annualRate = 6.5,
            tenureMonths = 60
        )

        val result = calculator.calculate()

        assertThat(result.success).isTrue()
        assertThat(result.value).isGreaterThan(300000.0)
        assertThat(result.details["interest"]).isGreaterThan(0.0)
    }

    @Test
    fun `calculate RD with zero interest`() {
        val calculator = RDCalculator(
            monthlyDeposit = 1000.0,
            annualRate = 0.0,
            tenureMonths = 12
        )

        val result = calculator.calculate()

        assertThat(result.success).isTrue()
        assertThat(result.value).isEqualTo(12000.0)
    }

    @Test
    fun `validate returns false for negative deposit`() {
        val calculator = RDCalculator(
            monthlyDeposit = -1000.0,
            annualRate = 6.5,
            tenureMonths = 12
        )

        assertThat(calculator.validate()).isFalse()
    }
}
