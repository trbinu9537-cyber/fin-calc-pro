package com.fincalcpro.ui.dashboard

import androidx.compose.ui.graphics.Color

data class DashboardItem(
    val title: String,
    val route: String,
    val icon: String = "💰", // Unicode emoji as placeholder for icons
    val color: Color? = null,
    val description: String = ""
)
