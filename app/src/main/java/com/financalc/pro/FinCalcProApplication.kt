package com.financalc.pro

import android.app.Application
import com.google.firebase.FirebaseApp
import com.google.firebase.crashlytics.FirebaseCrashlytics
import dagger.hilt.android.HiltAndroidApp

/**
 * Main Application class for FinCalc Pro
 * Initializes Hilt dependency injection and Firebase services
 */
@HiltAndroidApp
class FinCalcProApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        
        // Initialize Firebase
        FirebaseApp.initializeApp(this)
        
        // Enable Crashlytics in release builds
        FirebaseCrashlytics.getInstance().setCrashlyticsCollectionEnabled(!BuildConfig.DEBUG)
        
        // Set user identifier for better crash tracking
        if (!BuildConfig.DEBUG) {
            FirebaseCrashlytics.getInstance().setUserId("user_${System.currentTimeMillis()}")
        }
    }
}
