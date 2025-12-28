package com.financalc.pro.domain.calculator

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class SIPCalculatorTest {

    @Test
    fun `calculate SIP returns for valid inputs`() {
        val calculator = SIPCalculator(
            monthlyInvestment = 10000.0,
            annualRate = 12.0,
            tenureMonths = 120
        )

        val result = calculator.calculate()

        assertThat(result.success).isTrue()
        assertThat(result.value).isGreaterThan(1200000.0) // Should be more than total investment
        assertThat(result.details["returns"]).isGreaterThan(0.0)
    }

    @Test
    fun `calculate SIP with zero interest rate`() {
        val calculator = SIPCalculator(
            monthlyInvestment = 5000.0,
            annualRate = 0.0,
            tenureMonths = 24
        )

        val result = calculator.calculate()

        assertThat(result.success).isTrue()
        assertThat(result.value).isEqualTo(120000.0) // 5000 * 24
        assertThat(result.details["returns"]).isEqualTo(0.0)
    }

    @Test
    fun `validate returns false for negative monthly investment`() {
        val calculator = SIPCalculator(
            monthlyInvestment = -1000.0,
            annualRate = 12.0,
            tenureMonths = 12
        )

        assertThat(calculator.validate()).isFalse()
    }

    @Test
    fun `validate returns false for zero tenure`() {
        val calculator = SIPCalculator(
            monthlyInvestment = 5000.0,
            annualRate = 12.0,
            tenureMonths = 0
        )

        assertThat(calculator.validate()).isFalse()
    }
}
