package com.financalc.pro.domain.calculator

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class NSCCalculatorTest {

    @Test
    fun `calculate NSC maturity for valid inputs`() {
        val calculator = NSCCalculator(
            principal = 100000.0,
            annualRate = 7.7,
            tenure = 5
        )

        val result = calculator.calculate()

        assertThat(result.success).isTrue()
        assertThat(result.value).isGreaterThan(100000.0)
        assertThat(result.details["interest"]).isGreaterThan(0.0)
    }

    @Test
    fun `validate returns false for investment below minimum`() {
        val calculator = NSCCalculator(
            principal = 500.0,
            annualRate = 7.7,
            tenure = 5
        )

        assertThat(calculator.validate()).isFalse()
    }

    @Test
    fun `validate returns false for invalid tenure`() {
        val calculator = NSCCalculator(
            principal = 10000.0,
            annualRate = 7.7,
            tenure = 3
        )

        assertThat(calculator.validate()).isFalse()
    }
}
