# FinCalc Pro 📱💰

A comprehensive, production-ready Android financial calculator app with 30+ calculators tailored for Indian banking, post office schemes, retirement planning, and mutual funds.

[![Android CI/CD](https://github.com/trbinu9537-cyber/fin-calc-pro/actions/workflows/android-ci.yml/badge.svg)](https://github.com/trbinu9537-cyber/fin-calc-pro/actions/workflows/android-ci.yml)
[![API](https://img.shields.io/badge/API-24%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=24)
[![Kotlin](https://img.shields.io/badge/Kotlin-1.9.22-blue.svg)](https://kotlinlang.org)

## 🌟 Features

### Implemented Calculators (8 of 30+)

#### 💳 Loan Calculators
- **EMI Calculator** - Calculate Equated Monthly Installments for:
  - Home Loans
  - Personal Loans
  - Car Loans
  - Education Loans

#### 📈 Investment Calculators
- **Fixed Deposit (FD) Calculator** - Calculate maturity amount with compound interest
- **Recurring Deposit (RD) Calculator** - Monthly deposit scheme calculator
- **Lumpsum Calculator** - One-time investment returns

#### 🏛️ Post Office Schemes
- **Public Provident Fund (PPF)** - 15-year investment scheme
- **National Savings Certificate (NSC)** - 5-year fixed tenure certificate
- **Sukanya Samriddhi Yojana (SSY)** - Savings scheme for girl child

#### 📊 Mutual Funds  
- **SIP Calculator** - Systematic Investment Plan calculator
- **Lumpsum Investment Calculator** - One-time mutual fund investment

### Planned Calculators (22+ more)

#### Additional Post Office Schemes
- Senior Citizen Savings Scheme (SCSS)
- Kisan Vikas Patra (KVP)
- Post Office Time Deposit
- Post Office Monthly Income Scheme (MIS)

#### Retirement Planning
- National Pension System (NPS)
- Employee Provident Fund (EPF)
- Retirement Corpus Calculator
- Atal Pension Yojana

#### Additional Mutual Funds
- SWP (Systematic Withdrawal Plan) Calculator
- ELSS Calculator

#### Tax Calculators
- Income Tax Calculator
- GST Calculator
- Capital Gains Tax Calculator

#### Interest Calculators
- Simple Interest Calculator
- Compound Interest Calculator

#### And More...
- Gratuity Calculator
- Home Loan Prepayment Calculator
- Loan Comparison Tools
- Investment Comparison Tools

## 🏗️ Architecture

The app follows clean architecture principles with MVVM pattern:

- **Presentation Layer**: Activities, Fragments, ViewModels
- **Domain Layer**: Business logic, Calculator implementations
- **Data Layer**: Room Database, Repositories
- **Dependency Injection**: Hilt/Dagger

## 🛠️ Tech Stack

- **Language**: Kotlin
- **UI**: Material Design 3, View Binding
- **Architecture**: MVVM with Clean Architecture
- **Dependency Injection**: Hilt
- **Database**: Room
- **Asynchronous**: Coroutines, Flow
- **Navigation**: Navigation Component
- **Analytics**: Firebase Analytics
- **Crash Reporting**: Firebase Crashlytics
- **Performance Monitoring**: Firebase Performance

## 📋 Prerequisites

- Android Studio Hedgehog (2023.1.1) or later
- JDK 17 or later
- Android SDK with API 24 or higher
- Gradle 8.2 or later

## 🚀 Getting Started

### 1. Clone the Repository

```bash
git clone https://github.com/trbinu9537-cyber/fin-calc-pro.git
cd fin-calc-pro
```

### 2. Open in Android Studio

1. Launch Android Studio
2. Select "Open an Existing Project"
3. Navigate to the cloned repository
4. Wait for Gradle sync to complete

### 3. Configure Firebase (Optional)

For production builds with analytics and crash reporting:

1. Create a Firebase project at [Firebase Console](https://console.firebase.google.com/)
2. Add your Android app to the project
3. Download `google-services.json`
4. Replace the dummy file at `app/google-services.json`

### 4. Build and Run

#### Debug Build
```bash
./gradlew assembleDebug
```

#### Run Tests
```bash
./gradlew test
```

#### Run Lint Checks
```bash
./gradlew lint
```

#### Release Build
```bash
./gradlew assembleRelease
```

## 🧪 Testing

The project includes comprehensive unit tests:

```bash
# Run all tests
./gradlew test

# Run tests with coverage report
./gradlew test jacocoTestReport
```

Test coverage reports are generated in `app/build/reports/jacoco/`

## 🔧 Development

### Code Style

The project follows Kotlin coding conventions. Run ktlint for code style checks:

```bash
./gradlew ktlintCheck
```

### Project Structure

```
app/
├── src/
│   ├── main/
│   │   ├── java/com/financalc/pro/
│   │   │   ├── data/          # Data layer (repositories, database)
│   │   │   ├── domain/        # Business logic and calculators
│   │   │   ├── ui/            # UI components (Activities, Fragments)
│   │   │   ├── di/            # Dependency injection modules
│   │   │   └── utils/         # Utility classes
│   │   └── res/               # Resources (layouts, strings, etc.)
│   ├── test/                  # Unit tests
│   └── androidTest/           # Instrumentation tests
└── build.gradle.kts
```

## 📦 Release Process

### Setting up Keystore

1. Create a keystore for app signing:
```bash
keytool -genkey -v -keystore release.keystore -alias release -keyalg RSA -keysize 2048 -validity 10000
```

2. Set environment variables for CI/CD:
```bash
export KEYSTORE_FILE=/path/to/release.keystore
export KEYSTORE_PASSWORD=your_keystore_password
export KEY_ALIAS=release
export KEY_PASSWORD=your_key_password
```

### GitHub Secrets

For automated builds via GitHub Actions, add these secrets:
- `KEYSTORE_FILE`: Base64 encoded keystore file
- `KEYSTORE_PASSWORD`: Keystore password
- `KEY_ALIAS`: Key alias
- `KEY_PASSWORD`: Key password

## 🔒 Security Features

- ProGuard/R8 code obfuscation and minification
- Secure keystore management
- Firebase Crashlytics for error tracking
- Input validation for all calculators
- No sensitive data storage

## 📊 Performance Optimizations

- R8 full mode for maximum APK size reduction
- Resource shrinking
- Build caching enabled
- Gradle parallel execution
- Optimized image assets

## 🤝 Contributing

Contributions are welcome! Please read [CONTRIBUTING.md](CONTRIBUTING.md) for details on our code of conduct and the process for submitting pull requests.

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 👥 Authors

- **trbinu9537-cyber** - *Initial work*

## 🙏 Acknowledgments

- Material Design guidelines
- Android Jetpack components
- Firebase services
- Indian government schemes documentation

## 📞 Support

For support, email trbinu9537@gmail.com or open an issue in the GitHub repository.

## 🗺️ Roadmap

- [ ] Add more calculators (Insurance, Loan comparison)
- [ ] Multi-language support (Hindi, Tamil, etc.)
- [ ] Dark mode theme
- [ ] Export calculations to PDF
- [ ] Cloud sync for calculation history
- [ ] Widget support
- [ ] Offline mode improvements

---

Made with ❤️ for Indian financial planning
