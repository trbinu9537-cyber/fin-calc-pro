# Quick Reference Guide

## Calculator Usage Examples

### EMI Calculator

Calculate monthly installments for loans.

```kotlin
val emiCalculator = EMICalculator(
    principal = 1000000.0,      // Loan amount in ₹
    annualRate = 8.5,           // Interest rate in %
    tenureMonths = 240          // Loan tenure in months (20 years)
)

val result = emiCalculator.calculate()
if (result.success) {
    println("Monthly EMI: ₹${result.value}")
    println("Total Amount: ₹${result.details["totalAmount"]}")
    println("Total Interest: ₹${result.details["totalInterest"]}")
}
```

**Use Cases:**
- Home Loan EMI
- Personal Loan EMI
- Car Loan EMI
- Education Loan EMI

---

### SIP Calculator

Calculate returns from Systematic Investment Plans.

```kotlin
val sipCalculator = SIPCalculator(
    monthlyInvestment = 10000.0, // Monthly SIP amount in ₹
    annualRate = 12.0,           // Expected return in %
    tenureMonths = 120           // Investment period in months (10 years)
)

val result = sipCalculator.calculate()
if (result.success) {
    println("Maturity Amount: ₹${result.value}")
    println("Total Investment: ₹${result.details["totalInvestment"]}")
    println("Returns: ₹${result.details["returns"]}")
}
```

**Best For:**
- Mutual fund investments
- Long-term wealth creation
- Regular savings habit

---

### Fixed Deposit Calculator

Calculate maturity amount for fixed deposits.

```kotlin
val fdCalculator = FDCalculator(
    principal = 100000.0,         // Investment amount in ₹
    annualRate = 6.5,             // Interest rate in %
    tenureYears = 5.0,            // Tenure in years
    compoundingFrequency = 4      // Quarterly compounding
)

val result = fdCalculator.calculate()
if (result.success) {
    println("Maturity Amount: ₹${result.value}")
    println("Interest Earned: ₹${result.details["interest"]}")
}
```

**Compounding Frequencies:**
- `1` - Annually
- `2` - Half-yearly
- `4` - Quarterly
- `12` - Monthly

---

### PPF Calculator

Calculate Public Provident Fund maturity.

```kotlin
val ppfCalculator = PPFCalculator(
    yearlyInvestment = 150000.0, // Annual investment (max ₹1.5 lakh)
    annualRate = 7.1,            // Current PPF rate
    tenure = 15                  // Minimum 15 years
)

val result = ppfCalculator.calculate()
if (result.success) {
    println("Maturity Amount: ₹${result.value}")
    println("Total Investment: ₹${result.details["totalInvestment"]}")
    println("Interest Earned: ₹${result.details["interest"]}")
}
```

**Important:**
- Minimum investment: ₹500 per year
- Maximum investment: ₹1,50,000 per year
- Lock-in period: 15 years
- Partial withdrawal allowed after 7 years

---

### NSC Calculator

Calculate National Savings Certificate maturity.

```kotlin
val nscCalculator = NSCCalculator(
    principal = 100000.0,        // Investment amount
    annualRate = 7.7,            // Current NSC rate
    tenure = 5                   // Fixed 5 years
)

val result = nscCalculator.calculate()
if (result.success) {
    println("Maturity Amount: ₹${result.value}")
    println("Interest Earned: ₹${result.details["interest"]}")
}
```

**Features:**
- Fixed 5-year tenure
- Compound interest
- Tax benefit under Section 80C
- Interest taxable but reinvested

---

### SSY Calculator

Calculate Sukanya Samriddhi Yojana maturity.

```kotlin
val ssyCalculator = SSYCalculator(
    yearlyInvestment = 150000.0, // Annual deposit (max ₹1.5 lakh)
    annualRate = 8.2,            // Current SSY rate
    girlAge = 5                  // Girl's current age (0-10 years)
)

val result = ssyCalculator.calculate()
if (result.success) {
    println("Maturity Amount: ₹${result.value}")
    println("Maturity Age: ${result.details["maturityAge"]} years")
    println("Total Investment: ₹${result.details["totalInvestment"]}")
    println("Interest Earned: ₹${result.details["interest"]}")
}
```

**Rules:**
- Girl's age: 0-10 years at account opening
- Deposit for: 15 years
- Maturity: 21 years from account opening
- Minimum: ₹250 per year
- Maximum: ₹1,50,000 per year

---

### RD Calculator

Calculate Recurring Deposit maturity.

```kotlin
val rdCalculator = RDCalculator(
    monthlyDeposit = 5000.0,     // Monthly deposit amount
    annualRate = 6.5,            // Interest rate
    tenureMonths = 60            // Tenure in months (5 years)
)

val result = rdCalculator.calculate()
if (result.success) {
    println("Maturity Amount: ₹${result.value}")
    println("Total Deposits: ₹${result.details["totalInvestment"]}")
    println("Interest Earned: ₹${result.details["interest"]}")
}
```

**Benefits:**
- Regular monthly savings
- Higher interest than savings account
- Disciplined saving habit
- Lower risk

---

### Lumpsum Calculator

Calculate one-time investment returns.

```kotlin
val lumpsumCalculator = LumpsumCalculator(
    principal = 100000.0,        // Investment amount
    annualRate = 12.0,           // Expected annual return
    tenureYears = 10             // Investment period in years
)

val result = lumpsumCalculator.calculate()
if (result.success) {
    println("Future Value: ₹${result.value}")
    println("Returns: ₹${result.details["returns"]}")
    println("Absolute Return: ${result.details["absoluteReturn"]}%")
}
```

**Use Cases:**
- Mutual fund lumpsum investment
- One-time bonus/windfall investment
- Comparing with SIP returns

---

## Error Handling

All calculators return a `CalculationResult` object with:

```kotlin
data class CalculationResult(
    val success: Boolean,           // Whether calculation succeeded
    val value: Double = 0.0,        // Primary result value
    val details: Map<String, Double> = emptyMap(), // Additional details
    val errorMessage: String? = null  // Error message if failed
)
```

**Example Error Handling:**

```kotlin
val calculator = EMICalculator(
    principal = -10000.0,  // Invalid: negative amount
    annualRate = 8.5,
    tenureMonths = 240
)

val result = calculator.calculate()
if (!result.success) {
    println("Error: ${result.errorMessage}")
}
```

---

## Common Formulas

### EMI Formula
```
EMI = [P × r × (1 + r)^n] / [(1 + r)^n - 1]

Where:
P = Principal loan amount
r = Monthly interest rate (Annual Rate / 12 / 100)
n = Loan tenure in months
```

### SIP Future Value
```
FV = P × [(1 + r)^n - 1] / r × (1 + r)

Where:
P = Monthly investment
r = Monthly return rate
n = Number of months
```

### Compound Interest
```
A = P × (1 + r/n)^(n×t)

Where:
A = Maturity amount
P = Principal
r = Annual interest rate
n = Compounding frequency per year
t = Time in years
```

---

## Testing

All calculators have comprehensive unit tests:

```bash
# Run all tests
./gradlew test

# Run specific calculator tests
./gradlew test --tests "*EMICalculatorTest"
./gradlew test --tests "*SIPCalculatorTest"
```

---

## Tips for Accurate Calculations

1. **EMI Loans:**
   - Use actual interest rates from your lender
   - Include processing fees in principal if applicable
   - Consider prepayment options

2. **SIP Investments:**
   - Use realistic return expectations (10-12% for equity)
   - Consider inflation while planning
   - Start early for maximum benefit

3. **Fixed Deposits:**
   - Check current bank rates
   - Consider tax implications on interest
   - Compare with other investment options

4. **Post Office Schemes:**
   - Rates are government-set, check latest rates
   - Consider tax benefits (PPF, NSC, SSY)
   - Understand lock-in periods

5. **General:**
   - All amounts should be in Indian Rupees (₹)
   - Rates should be annual percentages
   - Results are approximate, verify with institution

---

## FAQs

**Q: Which calculator should I use for home loan?**
A: Use the EMI Calculator with your loan amount, bank's interest rate, and tenure.

**Q: What's better - SIP or Lumpsum?**
A: Both calculators can help you compare. SIP is better for regular income earners, Lumpsum for one-time investments.

**Q: Are PPF calculations accurate?**
A: Yes, based on current government rates. Rates may change annually.

**Q: Can I use these calculators for planning?**
A: Yes, they provide accurate estimates. Always verify with your bank/financial institution.

**Q: What if I get an error message?**
A: Check input validations - amounts should be positive, rates reasonable, tenure appropriate.

---

## Support

For questions or issues:
- Open an issue on GitHub
- Email: trbinu9537@gmail.com
- Check documentation in `/docs` directory

---

**Last Updated:** December 2025
**Version:** 0.1.0
