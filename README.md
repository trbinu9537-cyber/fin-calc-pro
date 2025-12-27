# 📱 Financial Calculator India Pro

A comprehensive Android financial calculator app with 30+ calculators for Indian banking, post office schemes, retirement planning, and mutual funds.

## ✨ Features

### 🏦 Bank Calculators
- **EMI Calculator** - Calculate loan EMIs with principal/interest breakdown and pie chart
- **Fixed Deposit (FD)** - Calculate FD returns with quarterly compounding
- **Recurring Deposit (RD)** - Post Office style RD calculator

### 🏛️ Post Office Schemes
- **PPF Calculator** - Public Provident Fund with year-wise breakdown
- **SSY** - Sukanya Samriddhi Yojana (21-year maturity)
- **SCSS** - Senior Citizens Savings Scheme
- **KVP** - Kisan Vikas Patra
- **NSC** - National Savings Certificate
- **MIS** - Monthly Income Scheme

### 🎯 Retirement & Tax
- **NPS** - National Pension System with annuity calculations
- **EPF** - Employee Provident Fund
- **APY** - Atal Pension Yojana
- **Gratuity** - Gratuity calculator (Act/Non-Act)
- **Income Tax** - Tax calculator (Old vs New regime)

### 📈 Mutual Funds & Investments
- **SIP Calculator** - Systematic Investment Plan with returns breakdown and chart
- **SWP** - Systematic Withdrawal Plan
- **ELSS** - Equity Linked Savings Scheme
- **Lumpsum** - One-time investment calculator
- **Capital Gains** - STCG/LTCG tax calculator

## 🎨 Technical Highlights

- **Jetpack Compose** - Modern declarative UI
- **Material 3** - Latest Material Design theming
- **Indian Number Formatting** - Lakhs and Crores format (₹1,50,000)
- **Interactive Charts** - MPAndroidChart integration
- **Navigation** - Compose Navigation with 19 screens
- **Clean Architecture** - Separation of UI, Engine, and Utils

## 📊 Project Statistics

- ✅ **19 Kotlin source files**
- ✅ **3 complete calculators** (EMI, SIP, PPF) with working logic
- ✅ **16 placeholder screens** ready for implementation
- ✅ **6 reusable components**
- ✅ **~3000+ lines of code**

## 🚀 Implementation Status

### ✅ Fully Implemented (3)
1. **EMI Calculator** - Complete with pie chart
2. **SIP Calculator** - Complete with pie chart
3. **PPF Calculator** - Complete with year-wise breakdown table

### 🔄 Coming Soon (16)
All other calculators have placeholder screens ready for implementation.

## 🛠️ Tech Stack

- **Language**: Kotlin
- **UI Framework**: Jetpack Compose
- **Design**: Material 3
- **Charts**: MPAndroidChart
- **Navigation**: Navigation Compose
- **Min SDK**: 24 (Android 7.0)
- **Target SDK**: 34 (Android 14)

## 📁 Project Structure

```
app/src/main/
├── java/com/fincalcpro/
│   ├── MainActivity.kt
│   ├── engine/
│   │   └── CalculatorEngine.kt (All financial formulas)
│   ├── navigation/
│   │   └── NavGraph.kt
│   ├── ui/
│   │   ├── theme/ (Colors, Typography, Theme)
│   │   ├── dashboard/ (Dashboard with all calculators)
│   │   ├── bank/ (Bank calculator screens)
│   │   ├── postoffice/ (Post Office calculator screens)
│   │   ├── retirement/ (Retirement calculator screens)
│   │   ├── mutualfunds/ (Mutual fund calculator screens)
│   │   └── components/ (Reusable UI components)
│   └── utils/
│       └── IndianNumberFormatter.kt
└── res/
    ├── values/ (strings, colors, themes)
    └── mipmap-*/ (App icons)
```

## 🎯 Key Features Implemented

### Calculator Engine
- ✅ EMI with compound interest
- ✅ SIP with monthly compounding
- ✅ PPF with annual compounding
- ✅ FD, RD, SSY, Gratuity, NPS, Lumpsum, Inflation formulas

### UI Components
- ✅ InputField with ₹ prefix
- ✅ PercentageInputField with % suffix
- ✅ ResultCard for displaying results
- ✅ CalculatorScaffold with TopBar and Calculate button
- ✅ Indian number formatting throughout

### Design
- ✅ Financial color palette (Emerald Green primary)
- ✅ Material 3 components
- ✅ Responsive grid layout (3 columns)
- ✅ Color-coded calculator categories
- ✅ Professional elevation and shadows

## 📖 Documentation

See [PROJECT_SETUP.md](PROJECT_SETUP.md) for detailed setup documentation.

## 🔮 Future Enhancements

- [ ] Implement remaining 16 calculators
- [ ] Add history/bookmarking with Room database
- [ ] PDF export and sharing
- [ ] Calculator comparison feature
- [ ] Widget support
- [ ] Dark theme refinement
- [ ] Unit tests for calculations
- [ ] UI tests

## 📄 License

All rights reserved.

---

**Built with ❤️ using Kotlin and Jetpack Compose**
