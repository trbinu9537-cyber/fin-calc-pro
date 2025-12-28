# Security Policy

## Supported Versions

We currently support the following versions with security updates:

| Version | Supported          |
| ------- | ------------------ |
| 1.0.x   | :white_check_mark: |
| < 1.0   | :x:                |

## Reporting a Vulnerability

We take security vulnerabilities seriously. If you discover a security vulnerability in FinCalc Pro, please follow these steps:

### 1. Do Not Create a Public Issue

Please do not create a public GitHub issue for security vulnerabilities, as this could put users at risk.

### 2. Report Privately

Send an email to **trbinu9537@gmail.com** with the following information:

- Description of the vulnerability
- Steps to reproduce the issue
- Potential impact
- Suggested fix (if any)

### 3. Response Timeline

- We will acknowledge receipt of your vulnerability report within 48 hours
- We will provide a more detailed response within 5 business days
- We will work to validate and reproduce the issue
- We will develop and test a fix
- We will release a security update and publicly disclose the vulnerability

### 4. Disclosure Policy

- We request that you do not publicly disclose the vulnerability until we have had a chance to address it
- We will credit you for the discovery (unless you prefer to remain anonymous)
- We will publicly acknowledge your responsible disclosure in our release notes

## Security Best Practices

### For Users

1. **Download Only from Official Sources**
   - Download the app only from Google Play Store or official GitHub releases
   - Verify the app signature before installation

2. **Keep the App Updated**
   - Enable automatic updates in Google Play Store
   - Install security updates promptly

3. **Review App Permissions**
   - The app only requests necessary permissions
   - Review and understand why each permission is needed

### For Developers

1. **Code Security**
   - Never commit secrets, API keys, or passwords to version control
   - Use environment variables for sensitive configuration
   - Enable ProGuard/R8 obfuscation for release builds

2. **Dependency Management**
   - Keep dependencies up to date
   - Regularly scan for vulnerabilities using dependency checking tools
   - Review dependency licenses

3. **Data Protection**
   - Encrypt sensitive data at rest
   - Use HTTPS for all network communications
   - Implement proper input validation
   - Sanitize user inputs to prevent injection attacks

4. **Firebase Configuration**
   - Secure Firebase rules
   - Use Firebase App Check
   - Monitor Firebase security alerts

5. **App Signing**
   - Protect your keystore file
   - Use strong passwords
   - Store keystore securely (not in version control)
   - Use GitHub Secrets for CI/CD

## Security Features in the App

### Current Implementation

1. **Code Obfuscation**: R8 shrinking and obfuscation enabled
2. **HTTPS Only**: All network communications use HTTPS
3. **Input Validation**: All calculator inputs are validated
4. **Crash Reporting**: Firebase Crashlytics for monitoring
5. **No Sensitive Data Storage**: App doesn't store sensitive user data

### Planned Enhancements

1. **Encrypted SharedPreferences**: For storing user preferences securely
2. **Certificate Pinning**: For enhanced network security
3. **Root Detection**: Warn users if device is rooted
4. **Tamper Detection**: Detect if app has been modified
5. **Secure Backup**: Encrypted backups of calculation history

## Common Vulnerabilities We Protect Against

### 1. Code Injection
- All user inputs are validated and sanitized
- No dynamic code execution

### 2. Data Leakage
- No sensitive data in logs (removed in release builds)
- Proper ProGuard rules to protect against reverse engineering

### 3. Insecure Data Storage
- No plain text storage of sensitive information
- Use of encrypted storage for future features

### 4. Insecure Communication
- HTTPS only for all network requests
- Certificate validation enabled

### 5. Improper Session Handling
- No authentication currently (future feature)
- Will implement secure session management if needed

## Security Audit

We welcome security audits and penetration testing. If you wish to conduct a security audit:

1. Contact us at trbinu9537@gmail.com
2. Provide details about your testing methodology
3. Coordinate testing to avoid disruption
4. Share findings responsibly

## Third-Party Dependencies

We rely on the following major dependencies:

- **AndroidX**: Google's official Android libraries
- **Firebase**: Google's backend platform
- **Hilt/Dagger**: Dependency injection (Google)
- **Room**: Database (Google)

All dependencies are regularly updated and monitored for security vulnerabilities.

## Security Checklist for Contributors

Before submitting code:

- [ ] No hardcoded secrets or API keys
- [ ] Input validation for all user inputs
- [ ] No SQL injection vulnerabilities
- [ ] No XSS vulnerabilities
- [ ] Proper error handling (no sensitive data in error messages)
- [ ] Dependencies are up to date
- [ ] Code follows secure coding practices
- [ ] ProGuard rules updated if needed

## Contact

For security-related questions or concerns:
- Email: trbinu9537@gmail.com
- Please use "SECURITY" in the subject line

Thank you for helping keep FinCalc Pro and our users safe!
