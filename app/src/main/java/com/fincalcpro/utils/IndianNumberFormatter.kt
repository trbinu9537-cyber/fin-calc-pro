package com.fincalcpro.utils

import java.text.DecimalFormat

/**
 * Utility for formatting numbers according to Indian numbering system
 * Format: ₹1,50,000 (lakhs, crores)
 */
object IndianNumberFormatter {
    
    /**
     * Format amount in Indian numbering system without decimals
     * Examples: ₹1,50,000 | ₹10,00,000 | ₹1,00,00,000
     */
    fun format(amount: Double): String {
        return "₹${formatIndianNumber(amount.toLong())}"
    }
    
    /**
     * Format amount in Indian numbering system with 2 decimal places
     * Examples: ₹1,50,000.50 | ₹10,00,000.25
     */
    fun formatWithDecimal(amount: Double): String {
        val wholePart = amount.toLong()
        val decimalPart = ((amount - wholePart) * 100).toInt()
        
        return if (decimalPart > 0) {
            "₹${formatIndianNumber(wholePart)}.${String.format("%02d", decimalPart)}"
        } else {
            "₹${formatIndianNumber(wholePart)}"
        }
    }
    
    /**
     * Format plain number without currency symbol
     */
    fun formatPlain(amount: Double): String {
        return formatIndianNumber(amount.toLong())
    }
    
    /**
     * Internal function to format number in Indian style
     * Indian numbering: First comma after 3 digits, then after every 2 digits
     * Example: 1234567 -> 12,34,567
     */
    private fun formatIndianNumber(number: Long): String {
        val numStr = number.toString()
        val length = numStr.length
        
        if (length <= 3) {
            return numStr
        }
        
        val result = StringBuilder()
        var count = 0
        
        // Process from right to left
        for (i in length - 1 downTo 0) {
            if (count == 3 || (count > 3 && (count - 3) % 2 == 0)) {
                result.insert(0, ',')
            }
            result.insert(0, numStr[i])
            count++
        }
        
        return result.toString()
    }
    
    /**
     * Format percentage with decimals
     * Example: 12.5%
     */
    fun formatPercentage(value: Double): String {
        val df = DecimalFormat("#.##")
        return "${df.format(value)}%"
    }
    
    /**
     * Format years
     * Example: "5 Years" or "1 Year"
     */
    fun formatYears(years: Int): String {
        return if (years == 1) "$years Year" else "$years Years"
    }
    
    /**
     * Format months
     * Example: "12 Months" or "1 Month"
     */
    fun formatMonths(months: Int): String {
        return if (months == 1) "$months Month" else "$months Months"
    }
}
