# Build Setup and Configuration

This document provides detailed information about building, testing, and deploying the FinCalc Pro Android application.

## Prerequisites

### Required Software

1. **Android Studio**: Hedgehog (2023.1.1) or later
   - Download from: https://developer.android.com/studio

2. **Java Development Kit (JDK)**: Version 17 or later
   - Verify: `java -version`
   - Download from: https://adoptium.net/

3. **Android SDK**:
   - API Level 24 (Android 7.0) minimum
   - API Level 34 (Android 14) target
   - Installed via Android Studio SDK Manager

4. **Gradle**: Version 8.2 (included via wrapper)
   - Wrapper will download automatically

### Optional Tools

- **Git**: For version control
- **ktlint**: For Kotlin code style checking
- **detekt**: For static code analysis

## Project Structure

```
fin-calc-pro/
├── .github/
│   └── workflows/           # GitHub Actions CI/CD
├── app/
│   ├── src/
│   │   ├── main/           # Main source code
│   │   ├── test/           # Unit tests
│   │   └── androidTest/    # Instrumentation tests
│   ├── build.gradle.kts    # App-level build configuration
│   ├── proguard-rules.pro  # ProGuard/R8 rules
│   └── google-services.json # Firebase configuration
├── gradle/
│   └── wrapper/            # Gradle wrapper files
├── build.gradle.kts        # Project-level build configuration
├── settings.gradle.kts     # Project settings
└── gradle.properties       # Gradle properties
```

## Building the Project

### 1. Clone the Repository

```bash
git clone https://github.com/trbinu9537-cyber/fin-calc-pro.git
cd fin-calc-pro
```

### 2. Configure Firebase (Production Builds)

For production builds with analytics and crash reporting:

1. Go to [Firebase Console](https://console.firebase.google.com/)
2. Create a new project or select existing
3. Add Android app with package name: `com.financalc.pro`
4. Download `google-services.json`
5. Replace the dummy file at `app/google-services.json`

For development/testing, the dummy configuration is sufficient.

### 3. Build Commands

#### Debug Build
```bash
./gradlew assembleDebug
```
Output: `app/build/outputs/apk/debug/app-debug.apk`

#### Release Build
```bash
./gradlew assembleRelease
```
Output: `app/build/outputs/apk/release/app-release.apk`

Note: Release builds require signing configuration (see below)

#### Clean Build
```bash
./gradlew clean assembleDebug
```

### 4. Install on Device

```bash
# Debug build
./gradlew installDebug

# Release build
./gradlew installRelease
```

## Testing

### Unit Tests

```bash
# Run all unit tests
./gradlew test

# Run tests for specific build variant
./gradlew testDebugUnitTest

# Run with coverage report
./gradlew test jacocoTestReport
```

Test reports: `app/build/reports/tests/`
Coverage reports: `app/build/reports/jacoco/`

### Instrumentation Tests

```bash
# Run all Android tests
./gradlew connectedAndroidTest

# Run on specific device
adb devices  # List devices
./gradlew connectedAndroidTest -Pandroid.testInstrumentationRunnerArguments.device=<device-id>
```

Test reports: `app/build/reports/androidTests/`

### Continuous Testing

```bash
# Watch mode - runs tests on file changes
./gradlew test --continuous
```

## Code Quality

### Lint Checks

```bash
# Run lint checks
./gradlew lint

# Generate lint report
./gradlew lintDebug
```

Lint reports: `app/build/reports/lint-results-debug.html`

### Code Style (ktlint)

```bash
# Check code style
./gradlew ktlintCheck

# Auto-format code
./gradlew ktlintFormat
```

### Static Analysis (detekt)

```bash
# Run static analysis
./gradlew detekt
```

## App Signing

### Development Signing

Debug builds are automatically signed with a debug keystore.

### Production Signing

#### 1. Generate Release Keystore

```bash
keytool -genkey -v -keystore release.keystore \
  -alias release \
  -keyalg RSA \
  -keysize 2048 \
  -validity 10000
```

#### 2. Configure Signing

Option A: Environment Variables
```bash
export KEYSTORE_FILE=/path/to/release.keystore
export KEYSTORE_PASSWORD=your_keystore_password
export KEY_ALIAS=release
export KEY_PASSWORD=your_key_password
```

Option B: gradle.properties (NOT recommended for version control)
```properties
KEYSTORE_FILE=/path/to/release.keystore
KEYSTORE_PASSWORD=your_keystore_password
KEY_ALIAS=release
KEY_PASSWORD=your_key_password
```

#### 3. Build Signed Release

```bash
./gradlew assembleRelease
```

**Security Warning**: Never commit keystore files or passwords to version control!

## Optimization

### APK Size Reduction

The project includes several optimizations:

1. **R8 Code Shrinking**: Removes unused code
2. **Resource Shrinking**: Removes unused resources
3. **ProGuard Rules**: Obfuscates code
4. **Vector Drawables**: Reduces image sizes
5. **WebP Images**: More efficient than PNG/JPEG

Check APK size:
```bash
./gradlew assembleRelease
ls -lh app/build/outputs/apk/release/app-release.apk
```

Analyze APK:
```bash
# Open APK Analyzer in Android Studio
Build > Analyze APK...
```

### Build Performance

Enable build optimizations in `gradle.properties`:
```properties
org.gradle.parallel=true
org.gradle.caching=true
org.gradle.configureondemand=true
android.enableBuildCache=true
```

## CI/CD with GitHub Actions

The project includes automated workflows:

### Workflows

1. **Build Workflow** (`.github/workflows/android-ci.yml`)
   - Triggered on push/PR
   - Runs lint checks
   - Runs unit tests
   - Builds debug APK
   - Uploads artifacts

2. **Test Workflow**
   - Runs unit tests with coverage
   - Uploads coverage reports

3. **Release Workflow**
   - Builds release APK (on main branch)
   - Requires signing secrets

### GitHub Secrets

Configure these secrets in repository settings:

- `KEYSTORE_FILE`: Base64 encoded keystore
- `KEYSTORE_PASSWORD`: Keystore password
- `KEY_ALIAS`: Key alias
- `KEY_PASSWORD`: Key password

Encode keystore:
```bash
base64 -i release.keystore | pbcopy  # macOS
base64 -i release.keystore           # Linux
```

## Troubleshooting

### Common Issues

#### 1. Gradle Sync Failed

```bash
# Clear Gradle cache
./gradlew clean
rm -rf .gradle
rm -rf app/build

# Invalidate caches in Android Studio
File > Invalidate Caches / Restart...
```

#### 2. Build Failed - Missing SDK

```bash
# Install missing SDK components
sdkmanager "platforms;android-34"
sdkmanager "build-tools;34.0.0"
```

#### 3. AAPT2 Errors

```bash
# Disable AAPT2 (not recommended)
android.enableAapt2=false  # in gradle.properties
```

#### 4. Dependency Resolution Failed

```bash
# Check dependencies
./gradlew dependencies

# Force refresh dependencies
./gradlew --refresh-dependencies
```

#### 5. Out of Memory

```bash
# Increase heap size in gradle.properties
org.gradle.jvmargs=-Xmx4096m -XX:MaxMetaspaceSize=512m
```

### Debugging Build Issues

```bash
# Run with stacktrace
./gradlew assembleDebug --stacktrace

# Run with debug info
./gradlew assembleDebug --debug

# Run with build scan
./gradlew assembleDebug --scan
```

## Build Variants

The app has two build types:

### Debug
- Development builds
- Debugging enabled
- No obfuscation
- Firebase in debug mode
- Package suffix: `.debug`

### Release
- Production builds
- Debugging disabled
- R8 obfuscation enabled
- Resource shrinking
- Firebase crash reporting enabled
- Requires signing

Access build variants:
```bash
# List all variants
./gradlew tasks --all | grep assemble
```

## Dependencies

### Updating Dependencies

Check for updates:
```bash
./gradlew dependencyUpdates
```

Update in `app/build.gradle.kts`:
```kotlin
// Example: Update Material Design
implementation("com.google.android.material:material:1.11.0")
```

### Security Scanning

Check for vulnerabilities:
```bash
./gradlew dependencyCheckAnalyze
```

## Performance Profiling

### Build Performance

```bash
# Profile build
./gradlew assembleDebug --profile

# Report: build/reports/profile/
```

### Runtime Performance

Use Android Studio Profiler:
1. Run > Profile 'app'
2. Analyze CPU, Memory, Network, Battery

## Release Checklist

Before releasing:

- [ ] Update version in `app/build.gradle.kts`
- [ ] Update `CHANGELOG.md`
- [ ] Run all tests: `./gradlew test connectedAndroidTest`
- [ ] Run lint: `./gradlew lint`
- [ ] Build release APK: `./gradlew assembleRelease`
- [ ] Test on multiple devices/emulators
- [ ] Verify ProGuard rules
- [ ] Check APK size
- [ ] Update screenshots
- [ ] Tag release in Git

## Resources

- [Android Build Configuration](https://developer.android.com/studio/build)
- [Gradle User Guide](https://docs.gradle.org/current/userguide/userguide.html)
- [R8 Shrinking Guide](https://developer.android.com/studio/build/shrink-code)
- [App Signing](https://developer.android.com/studio/publish/app-signing)
- [GitHub Actions for Android](https://github.com/actions/setup-java)
