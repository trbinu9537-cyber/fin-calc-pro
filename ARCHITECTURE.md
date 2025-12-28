# Architecture Documentation

## Overview

FinCalc Pro follows Clean Architecture principles with MVVM (Model-View-ViewModel) pattern to ensure a maintainable, testable, and scalable codebase.

## Architecture Layers

### 1. Presentation Layer (UI)

**Location**: `app/src/main/java/com/financalc/pro/ui/`

- **Activities**: Host fragments and handle navigation
- **Fragments**: Display UI and interact with ViewModels
- **ViewModels**: Hold UI state and business logic, survive configuration changes
- **View Binding**: Type-safe way to interact with views

**Key Components**:
- `MainActivity`: Main entry point with bottom navigation
- `HomeFragment`, `CalculatorsFragment`, `HistoryFragment`, `SettingsFragment`: Main screens
- `HomeViewModel`: Example ViewModel implementation

### 2. Domain Layer

**Location**: `app/src/main/java/com/financalc/pro/domain/`

- **Models**: Core business objects and interfaces
- **Calculators**: Pure business logic for financial calculations
- **Use Cases**: (Future) Specific business operations

**Key Components**:
- `Calculator`: Base interface for all calculator implementations
- `CalculationResult`: Wrapper for calculation results with error handling
- `EMICalculator`, `SIPCalculator`, `FDCalculator`, `PPFCalculator`: Specific calculator implementations

### 3. Data Layer

**Location**: `app/src/main/java/com/financalc/pro/data/`

- **Repositories**: Abstract data sources
- **Database**: Room database entities and DAOs
- **Models**: Data transfer objects

**(Future Implementation)**

### 4. Dependency Injection

**Location**: `app/src/main/java/com/financalc/pro/di/`

Uses Hilt/Dagger for dependency injection to:
- Provide loose coupling
- Enable easy testing with mocks
- Centralize object creation

**(Future Implementation)**

## Data Flow

```
User Interaction
    ↓
Fragment/Activity
    ↓
ViewModel (observes LiveData/Flow)
    ↓
Use Case / Calculator (Domain Layer)
    ↓
Repository (Data Layer)
    ↓
Data Source (Room DB / Network)
```

## Key Design Patterns

### 1. MVVM (Model-View-ViewModel)
- **View**: Activities and Fragments
- **ViewModel**: Holds UI state, survives configuration changes
- **Model**: Data layer (repositories, database)

### 2. Repository Pattern
- Abstracts data sources
- Provides clean API for data access
- Enables easy switching between local/remote data

### 3. Observer Pattern
- LiveData/Flow for reactive updates
- ViewModels expose observable state
- UI subscribes to state changes

### 4. Dependency Injection
- Hilt provides dependencies
- Improves testability
- Reduces boilerplate

## Testing Strategy

### Unit Tests
**Location**: `app/src/test/`

- Test business logic in isolation
- Mock dependencies
- Fast execution
- High coverage target (>80%)

**Current Tests**:
- Calculator logic tests (EMI, SIP, FD, PPF)
- ViewModel tests (future)

### Integration Tests
**Location**: `app/src/androidTest/`

- Test component interactions
- Test Room database operations
- Test navigation flows

### UI Tests
- Espresso for UI testing
- Test user workflows
- Validate UI state changes

## Code Organization

```
com.financalc.pro/
├── FinCalcProApplication.kt          # Application class
├── data/                              # Data layer
│   ├── local/                        # Room database
│   ├── remote/                       # API clients
│   └── repository/                   # Repository implementations
├── domain/                            # Business logic
│   ├── model/                        # Core models
│   ├── calculator/                   # Calculator implementations
│   └── usecase/                      # Use cases
├── di/                                # Dependency injection
│   ├── AppModule.kt
│   ├── DatabaseModule.kt
│   └── RepositoryModule.kt
├── ui/                                # Presentation layer
│   ├── MainActivity.kt
│   ├── home/                         # Home feature
│   ├── calculators/                  # Calculators feature
│   ├── history/                      # History feature
│   └── settings/                     # Settings feature
└── utils/                             # Utility classes
    ├── Constants.kt
    ├── Extensions.kt
    └── FormatUtils.kt
```

## Best Practices

### 1. Single Responsibility Principle
- Each class has one reason to change
- Calculators focus only on calculations
- ViewModels focus only on UI state

### 2. Dependency Inversion
- Depend on abstractions, not concretions
- Use interfaces for calculators
- Inject dependencies

### 3. Immutability
- Use `val` over `var` where possible
- Use data classes for models
- Prefer functional programming

### 4. Error Handling
- Wrap results in `CalculationResult`
- Validate inputs before processing
- Provide meaningful error messages

### 5. Code Documentation
- Document public APIs
- Explain complex algorithms
- Keep comments up to date

## Performance Optimizations

### 1. Memory Management
- ViewBinding prevents memory leaks
- Clean up resources in `onDestroyView`
- Use lifecycle-aware components

### 2. Database
- Room for efficient local storage
- Index frequently queried columns
- Use coroutines for async operations

### 3. UI Rendering
- RecyclerView for lists
- ViewHolder pattern
- DiffUtil for efficient updates

### 4. Build Optimization
- R8 code shrinking
- Resource shrinking
- ProGuard obfuscation

## Security Considerations

### 1. Code Obfuscation
- R8/ProGuard for release builds
- Protect business logic
- Remove debug code

### 2. Data Protection
- Encrypted SharedPreferences
- No sensitive data in logs
- Secure Firebase configuration

### 3. Input Validation
- Validate all user inputs
- Sanitize data before processing
- Prevent injection attacks

## Future Enhancements

1. **Offline Support**: Room database for calculation history
2. **Cloud Sync**: Firebase Realtime Database for cross-device sync
3. **Analytics**: Track calculator usage patterns
4. **Machine Learning**: Suggest personalized financial plans
5. **Widgets**: Quick access to favorite calculators
6. **Notifications**: Remind about investments, EMIs
7. **Multi-language**: Support Hindi, Tamil, and other Indian languages
8. **Dark Mode**: System-wide theme support
9. **Export**: PDF/Excel export for calculations
10. **Comparison**: Compare different investment options

## Resources

- [Android Architecture Guide](https://developer.android.com/topic/architecture)
- [MVVM Pattern](https://developer.android.com/topic/libraries/architecture/viewmodel)
- [Hilt Dependency Injection](https://developer.android.com/training/dependency-injection/hilt-android)
- [Room Persistence](https://developer.android.com/training/data-storage/room)
- [Kotlin Coroutines](https://kotlinlang.org/docs/coroutines-overview.html)
