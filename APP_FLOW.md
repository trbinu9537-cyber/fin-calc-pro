# App Flow Visualization

## 📱 Application Navigation Flow

```
┌─────────────────────────────────────────────────────────────┐
│                      MainActivity                            │
│                   (Jetpack Compose)                          │
└──────────────────────────┬──────────────────────────────────┘
                           │
                           ▼
┌─────────────────────────────────────────────────────────────┐
│                    Dashboard Screen                          │
│  ┌────────────────────────────────────────────────────────┐ │
│  │              🏦 Bank Schemes                           │ │
│  │  ┌──────┐  ┌──────┐  ┌──────┐                        │ │
│  │  │ EMI  │  │  FD  │  │  RD  │                        │ │
│  │  │  🏦  │  │  💎  │  │  📊  │                        │ │
│  │  └──┬───┘  └──────┘  └──────┘                        │ │
│  └─────┼────────────────────────────────────────────────┘ │
│        │                                                    │
│  ┌─────┼────────────────────────────────────────────────┐ │
│  │     │     🏛️ Post Office Schemes                    │ │
│  │  ┌──▼───┐  ┌──────┐  ┌──────┐                        │ │
│  │  │ PPF  │  │ SSY  │  │ SCSS │                        │ │
│  │  │  🏛️  │  │  👧  │  │  👴  │                        │ │
│  │  └──┬───┘  └──────┘  └──────┘                        │ │
│  │  ┌──┼───┐  ┌──────┐  ┌──────┐                        │ │
│  │  │ KVP  │  │ NSC  │  │ MIS  │                        │ │
│  │  │  📜  │  │  🎖️  │  │  💵  │                        │ │
│  │  └──────┘  └──────┘  └──────┘                        │ │
│  └────────────────────────────────────────────────────────┘ │
│                                                              │
│  ┌────────────────────────────────────────────────────────┐ │
│  │              🎯 Retirement & Tax                       │ │
│  │  ┌──────┐  ┌──────┐  ┌──────┐  ┌──────┐  ┌──────┐   │ │
│  │  │ NPS  │  │ EPF  │  │ APY  │  │Gratu │  │ Tax  │   │ │
│  │  │  🏛️  │  │  💼  │  │  🛡️  │  │  🎁  │  │  💸  │   │ │
│  │  └──────┘  └──────┘  └──────┘  └──────┘  └──────┘   │ │
│  └────────────────────────────────────────────────────────┘ │
│                                                              │
│  ┌────────────────────────────────────────────────────────┐ │
│  │         📈 Mutual Funds & Investments                  │ │
│  │  ┌──────┐  ┌──────┐  ┌──────┐                        │ │
│  │  │ SIP  │  │ SWP  │  │ ELSS │                        │ │
│  │  │  📈  │  │  📉  │  │  🌱  │                        │ │
│  │  └──┬───┘  └──────┘  └──────┘                        │ │
│  │  ┌──┼───┐  ┌──────┐                                  │ │
│  │  │Lump  │  │CapGn │                                  │ │
│  │  │  💰  │  │  📊  │                                  │ │
│  │  └──────┘  └──────┘                                  │ │
│  └────────────────────────────────────────────────────────┘ │
└─────────────────────────────────────────────────────────────┘
                           │
        ┌──────────────────┼──────────────────┐
        │                  │                  │
        ▼                  ▼                  ▼
┌──────────────┐  ┌──────────────┐  ┌──────────────┐
│EMI Calculator│  │SIP Calculator│  │PPF Calculator│
│              │  │              │  │              │
│ [Inputs]     │  │ [Inputs]     │  │ [Inputs]     │
│ • Loan Amt   │  │ • Monthly    │  │ • Yearly     │
│ • Rate %     │  │   Investment │  │   Deposit    │
│ • Tenure     │  │ • Return %   │  │ • Rate %     │
│              │  │ • Years      │  │ • Extension  │
│[Calculate]   │  │              │  │              │
│   Button     │  │[Calculate]   │  │[Calculate]   │
│              │  │   Button     │  │   Button     │
│ [Results]    │  │              │  │              │
│ • Monthly EMI│  │ [Results]    │  │ [Results]    │
│ • Total Int  │  │ • Invested   │  │ • Maturity   │
│ • Total Pay  │  │ • Returns    │  │ • Interest   │
│              │  │ • Total      │  │              │
│ [Pie Chart]  │  │              │  │ [Year Table] │
│ Principal vs │  │ [Pie Chart]  │  │ • Opening    │
│ Interest     │  │ Investment vs│  │ • Deposit    │
│              │  │ Returns      │  │ • Interest   │
│              │  │              │  │ • Closing    │
└──────────────┘  └──────────────┘  └──────────────┘
```

## 🎨 UI Component Hierarchy

```
CalculatorScaffold (Reusable)
├── TopAppBar
│   ├── Back Button
│   └── Title
├── ScrollableContent
│   ├── InputField (with ₹ prefix)
│   ├── PercentageInputField (with % suffix)
│   ├── YearInputField (with Years suffix)
│   ├── ResultCard
│   │   ├── Title
│   │   └── ResultRow items
│   ├── HighlightResultCard
│   │   ├── Label
│   │   └── Highlighted Value
│   └── Chart (AndroidView with MPAndroidChart)
└── Calculate Button (Bottom)
```

## 🔢 Calculation Engine Data Flow

```
User Input → InputField Component → State Management
                                           │
                                           ▼
                              [Calculate Button Clicked]
                                           │
                                           ▼
                             CalculatorEngine.calculate___()
                                           │
                            ┌──────────────┴──────────────┐
                            │                             │
                    Mathematical Formula          Result Data Class
                    (Indian Standards)                    │
                            │                             │
                            └──────────────┬──────────────┘
                                           │
                                           ▼
                              IndianNumberFormatter.format()
                                           │
                                           ▼
                              ResultCard / Chart Display
```

## 🎯 Complete vs Placeholder Screens

### ✅ Complete (3 screens)
1. **EMI Calculator**
   - Full calculation logic
   - Input validation
   - Result display
   - Pie chart visualization

2. **SIP Calculator**
   - Full calculation logic
   - Input validation
   - Result display
   - Pie chart visualization

3. **PPF Calculator**
   - Full calculation logic
   - Input validation
   - Result display with year-wise breakdown
   - Table visualization

### 🔄 Placeholder (16 screens)
- FD, RD (Bank)
- SSY, SCSS, KVP, NSC, MIS (Post Office)
- NPS, EPF, APY, Gratuity, IncomeTax (Retirement)
- SWP, ELSS, Lumpsum, CapitalGains (Mutual Funds)

Each placeholder shows:
- Calculator icon (emoji)
- "Coming Soon" message
- Calculator name
- Brief description

## 📦 Module Structure

```
app
├── build.gradle.kts (Dependencies)
├── proguard-rules.pro
└── src/main
    ├── AndroidManifest.xml
    ├── java/com/fincalcpro
    │   ├── MainActivity.kt (Entry point)
    │   ├── engine
    │   │   └── CalculatorEngine.kt (Business logic)
    │   ├── navigation
    │   │   └── NavGraph.kt (Routing)
    │   ├── ui
    │   │   ├── bank (Bank screens)
    │   │   ├── components (Reusable UI)
    │   │   ├── dashboard (Home screen)
    │   │   ├── mutualfunds (MF screens)
    │   │   ├── postoffice (PO screens)
    │   │   ├── retirement (Retirement screens)
    │   │   └── theme (Material 3 theme)
    │   └── utils
    │       └── IndianNumberFormatter.kt (Formatting)
    └── res
        ├── mipmap-* (Icons)
        └── values
            ├── colors.xml
            ├── strings.xml
            └── themes.xml
```

## 🔐 Technology Stack

```
┌─────────────────────────────────────────┐
│         Jetpack Compose                 │
│    (Declarative UI Framework)           │
├─────────────────────────────────────────┤
│         Material 3                      │
│    (Design System)                      │
├─────────────────────────────────────────┤
│    Navigation Compose                   │
│    (Screen Navigation)                  │
├─────────────────────────────────────────┤
│      MPAndroidChart                     │
│    (Chart Visualization)                │
├─────────────────────────────────────────┤
│         Kotlin                          │
│    (Programming Language)               │
├─────────────────────────────────────────┤
│      Android SDK 24-34                  │
│    (Platform)                           │
└─────────────────────────────────────────┘
```

## 📊 Data Flow Example: EMI Calculator

```
1. User Opens EMI Calculator
   ↓
2. EMICalculatorScreen Displays
   ├── InputField: Loan Amount (₹5,00,000)
   ├── InputField: Interest Rate (8.5%)
   └── InputField: Tenure (5 years → 60 months)
   ↓
3. User Clicks "Calculate" Button
   ↓
4. CalculatorEngine.calculateEMI(500000, 8.5, 60)
   ↓
5. Formula Execution:
   monthly_rate = 8.5 / (12 * 100) = 0.00708
   emi = P × r × (1+r)^n / ((1+r)^n - 1)
   emi = 500000 × 0.00708 × 1.5237 / 0.5237
   emi ≈ ₹10,286
   ↓
6. Returns EMIResult:
   - monthlyEMI: 10286
   - totalInterest: 117160
   - totalPayment: 617160
   - principal: 500000
   ↓
7. IndianNumberFormatter.format() Applied
   - ₹10,286 → "₹10,286"
   - ₹1,17,160 → "₹1,17,160"
   - ₹6,17,160 → "₹6,17,160"
   ↓
8. UI Updates:
   ├── HighlightResultCard: "₹10,286"
   ├── ResultCard with 3 rows
   └── Pie Chart: 81% Principal, 19% Interest
```

## 🎨 Color Scheme

```
Financial Color Palette:

Primary (Money/Success)    Secondary (Interactive)
┌──────────────────┐       ┌──────────────────┐
│  Emerald Green   │       │       Blue       │
│    #10B981       │       │     #3B82F6      │
└──────────────────┘       └──────────────────┘

Background             Card/Surface
┌──────────────────┐  ┌──────────────────┐
│   Light Gray     │  │      White       │
│    #F8FAFC       │  │    #FFFFFF       │
└──────────────────┘  └──────────────────┘

Scheme-Specific Colors:
┌──────────┬──────────┬──────────┬──────────┐
│   PPF    │   SSY    │  SCSS    │   SIP    │
│  Gold    │   Pink   │  Purple  │   Blue   │
│ #EAB308  │ #EC4899  │ #8B5CF6  │ #0EA5E9 │
└──────────┴──────────┴──────────┴──────────┘
```
