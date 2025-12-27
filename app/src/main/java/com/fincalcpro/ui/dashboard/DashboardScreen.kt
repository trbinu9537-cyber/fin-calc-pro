package com.fincalcpro.ui.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.fincalcpro.navigation.Screen
import com.fincalcpro.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Financial Calculator India Pro") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(MaterialTheme.colorScheme.background)
                .verticalScroll(rememberScrollState())
        ) {
            // Bank Schemes Section
            CalculatorSection(
                title = "Bank Schemes",
                items = bankCalculators,
                navController = navController
            )
            
            Spacer(modifier = Modifier.height(8.dp))
            
            // Post Office Schemes Section
            CalculatorSection(
                title = "Post Office Schemes",
                items = postOfficeCalculators,
                navController = navController
            )
            
            Spacer(modifier = Modifier.height(8.dp))
            
            // Retirement & Tax Section
            CalculatorSection(
                title = "Retirement & Tax",
                items = retirementCalculators,
                navController = navController
            )
            
            Spacer(modifier = Modifier.height(8.dp))
            
            // Mutual Funds & Stocks Section
            CalculatorSection(
                title = "Mutual Funds & Investments",
                items = mutualFundCalculators,
                navController = navController
            )
            
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun CalculatorSection(
    title: String,
    items: List<DashboardItem>,
    navController: NavController
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface)
            .padding(vertical = 16.dp)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )
        
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = Modifier
                .fillMaxWidth()
                .height((items.size / 3 + if (items.size % 3 > 0) 1 else 0) * 120.dp),
            contentPadding = PaddingValues(horizontal = 8.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(items) { item ->
                DashboardCard(
                    item = item,
                    onClick = { navController.navigate(item.route) }
                )
            }
        }
    }
}

@Composable
fun DashboardCard(
    item: DashboardItem,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .clickable(onClick = onClick),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(
            containerColor = item.color ?: MaterialTheme.colorScheme.surface
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = item.icon,
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            Text(
                text = item.title,
                style = MaterialTheme.typography.bodySmall,
                textAlign = TextAlign.Center,
                color = if (item.color != null) CardWhite else MaterialTheme.colorScheme.onSurface,
                maxLines = 2,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

// Bank Calculators
private val bankCalculators = listOf(
    DashboardItem(
        title = "EMI",
        route = Screen.EMI.route,
        icon = "🏦",
        color = Blue
    ),
    DashboardItem(
        title = "Fixed Deposit",
        route = Screen.FD.route,
        icon = "💎",
        color = EmeraldGreen
    ),
    DashboardItem(
        title = "Recurring Deposit",
        route = Screen.RD.route,
        icon = "📊",
        color = BlueDark
    )
)

// Post Office Calculators
private val postOfficeCalculators = listOf(
    DashboardItem(
        title = "PPF",
        route = Screen.PPF.route,
        icon = "🏛️",
        color = PPFGold
    ),
    DashboardItem(
        title = "SSY",
        route = Screen.SSY.route,
        icon = "👧",
        color = SSYPink
    ),
    DashboardItem(
        title = "SCSS",
        route = Screen.SCSS.route,
        icon = "👴",
        color = SCSSPurple
    ),
    DashboardItem(
        title = "KVP",
        route = Screen.KVP.route,
        icon = "📜",
        color = WarningOrange
    ),
    DashboardItem(
        title = "NSC",
        route = Screen.NSC.route,
        icon = "🎖️",
        color = SuccessGreen
    ),
    DashboardItem(
        title = "MIS",
        route = Screen.MIS.route,
        icon = "💵",
        color = Blue
    )
)

// Retirement & Tax Calculators
private val retirementCalculators = listOf(
    DashboardItem(
        title = "NPS",
        route = Screen.NPS.route,
        icon = "🏛️",
        color = EmeraldGreen
    ),
    DashboardItem(
        title = "EPF",
        route = Screen.EPF.route,
        icon = "💼",
        color = Blue
    ),
    DashboardItem(
        title = "APY",
        route = Screen.APY.route,
        icon = "🛡️",
        color = BlueDark
    ),
    DashboardItem(
        title = "Gratuity",
        route = Screen.Gratuity.route,
        icon = "🎁",
        color = SuccessGreen
    ),
    DashboardItem(
        title = "Income Tax",
        route = Screen.IncomeTax.route,
        icon = "💸",
        color = ErrorRed
    )
)

// Mutual Funds & Investment Calculators
private val mutualFundCalculators = listOf(
    DashboardItem(
        title = "SIP",
        route = Screen.SIP.route,
        icon = "📈",
        color = SIPBlue
    ),
    DashboardItem(
        title = "SWP",
        route = Screen.SWP.route,
        icon = "📉",
        color = Blue
    ),
    DashboardItem(
        title = "ELSS",
        route = Screen.ELSS.route,
        icon = "🌱",
        color = ELSSGreen
    ),
    DashboardItem(
        title = "Lumpsum",
        route = Screen.Lumpsum.route,
        icon = "💰",
        color = PPFGold
    ),
    DashboardItem(
        title = "Capital Gains",
        route = Screen.CapitalGains.route,
        icon = "📊",
        color = EmeraldGreen
    )
)
