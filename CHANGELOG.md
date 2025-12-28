# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [Unreleased]

### Added
- Initial Android project setup with Kotlin
- MVVM architecture with ViewModel and LiveData
- Hilt dependency injection configuration
- Navigation Component with bottom navigation
- Firebase Analytics integration
- Firebase Crashlytics for crash reporting
- Firebase Performance Monitoring
- ProGuard/R8 code obfuscation and minification
- App signing configuration for release builds

### Calculators Implemented
- EMI Calculator (for Home Loan, Personal Loan, Car Loan, etc.)
- SIP (Systematic Investment Plan) Calculator
- Lumpsum Investment Calculator
- Fixed Deposit (FD) Calculator
- Recurring Deposit (RD) Calculator
- Public Provident Fund (PPF) Calculator
- National Savings Certificate (NSC) Calculator
- Sukanya Samriddhi Yojana (SSY) Calculator

### Testing
- JUnit 4 for unit testing
- Google Truth for test assertions
- Comprehensive unit tests for all calculator implementations
- GitHub Actions CI/CD workflow for automated testing

### Documentation
- Comprehensive README with project overview
- BUILD_SETUP.md for build and configuration guide
- ARCHITECTURE.md for technical architecture details
- CONTRIBUTING.md for contribution guidelines
- DEVELOPMENT_NOTES.md for environment-specific notes
- MIT License

### Build & CI/CD
- Gradle 8.2 with Kotlin DSL
- GitHub Actions workflows for build, test, and release
- Automated APK generation
- Test result and lint report uploads

## [1.0.0] - TBD

### Planned Features
- Complete set of 30+ financial calculators
- Room database for calculation history
- Calculation history management
- Export calculations to PDF
- Share functionality
- UI themes (Light/Dark mode)
- Multi-language support (Hindi, Tamil, etc.)
- App widgets
- Cloud sync for calculation history
- Advanced tax calculators
- Comparison tools for investments

### Known Issues
- UI for calculator inputs needs enhancement
- History fragment not yet implemented
- Settings fragment needs implementation
- Remaining 20+ calculators to be added

---

## Version History

### Version 1.0.0 (Planned)
- First public release
- 30+ financial calculators
- Complete documentation
- Production-ready build

### Version 0.1.0 (Current - Initial Development)
- Project foundation
- Core architecture
- 8 calculator implementations
- Basic UI structure
- Testing infrastructure
- CI/CD pipeline
