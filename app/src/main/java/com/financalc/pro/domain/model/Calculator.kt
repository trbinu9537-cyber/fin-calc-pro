package com.financalc.pro.domain.model

/**
 * Base interface for all calculators
 */
interface Calculator {
    fun calculate(): CalculationResult
    fun validate(): Boolean
}

/**
 * Result wrapper for calculations
 */
data class CalculationResult(
    val success: Boolean,
    val value: Double = 0.0,
    val details: Map<String, Double> = emptyMap(),
    val errorMessage: String? = null
)
