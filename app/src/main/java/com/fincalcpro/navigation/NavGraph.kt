package com.fincalcpro.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.fincalcpro.ui.dashboard.DashboardScreen
import com.fincalcpro.ui.bank.EMICalculatorScreen
import com.fincalcpro.ui.bank.FDCalculatorScreen
import com.fincalcpro.ui.bank.RDCalculatorScreen
import com.fincalcpro.ui.postoffice.PPFCalculatorScreen
import com.fincalcpro.ui.postoffice.SSYCalculatorScreen
import com.fincalcpro.ui.postoffice.SCSSCalculatorScreen
import com.fincalcpro.ui.postoffice.KVPCalculatorScreen
import com.fincalcpro.ui.postoffice.NSCCalculatorScreen
import com.fincalcpro.ui.postoffice.MISCalculatorScreen
import com.fincalcpro.ui.retirement.NPSCalculatorScreen
import com.fincalcpro.ui.retirement.EPFCalculatorScreen
import com.fincalcpro.ui.retirement.APYCalculatorScreen
import com.fincalcpro.ui.retirement.GratuityCalculatorScreen
import com.fincalcpro.ui.retirement.IncomeTaxCalculatorScreen
import com.fincalcpro.ui.mutualfunds.SIPCalculatorScreen
import com.fincalcpro.ui.mutualfunds.SWPCalculatorScreen
import com.fincalcpro.ui.mutualfunds.ELSSCalculatorScreen
import com.fincalcpro.ui.mutualfunds.LumpsumCalculatorScreen
import com.fincalcpro.ui.mutualfunds.CapitalGainsScreen

sealed class Screen(val route: String) {
    object Dashboard : Screen("dashboard")
    object EMI : Screen("emi")
    object FD : Screen("fd")
    object RD : Screen("rd")
    object PPF : Screen("ppf")
    object SSY : Screen("ssy")
    object SCSS : Screen("scss")
    object KVP : Screen("kvp")
    object NSC : Screen("nsc")
    object MIS : Screen("mis")
    object NPS : Screen("nps")
    object EPF : Screen("epf")
    object APY : Screen("apy")
    object Gratuity : Screen("gratuity")
    object IncomeTax : Screen("income_tax")
    object SIP : Screen("sip")
    object SWP : Screen("swp")
    object ELSS : Screen("elss")
    object Lumpsum : Screen("lumpsum")
    object CapitalGains : Screen("capital_gains")
}

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Dashboard.route
    ) {
        composable(Screen.Dashboard.route) {
            DashboardScreen(navController = navController)
        }
        
        // Bank Calculators
        composable(Screen.EMI.route) {
            EMICalculatorScreen(navController = navController)
        }
        composable(Screen.FD.route) {
            FDCalculatorScreen(navController = navController)
        }
        composable(Screen.RD.route) {
            RDCalculatorScreen(navController = navController)
        }
        
        // Post Office Calculators
        composable(Screen.PPF.route) {
            PPFCalculatorScreen(navController = navController)
        }
        composable(Screen.SSY.route) {
            SSYCalculatorScreen(navController = navController)
        }
        composable(Screen.SCSS.route) {
            SCSSCalculatorScreen(navController = navController)
        }
        composable(Screen.KVP.route) {
            KVPCalculatorScreen(navController = navController)
        }
        composable(Screen.NSC.route) {
            NSCCalculatorScreen(navController = navController)
        }
        composable(Screen.MIS.route) {
            MISCalculatorScreen(navController = navController)
        }
        
        // Retirement & Tax Calculators
        composable(Screen.NPS.route) {
            NPSCalculatorScreen(navController = navController)
        }
        composable(Screen.EPF.route) {
            EPFCalculatorScreen(navController = navController)
        }
        composable(Screen.APY.route) {
            APYCalculatorScreen(navController = navController)
        }
        composable(Screen.Gratuity.route) {
            GratuityCalculatorScreen(navController = navController)
        }
        composable(Screen.IncomeTax.route) {
            IncomeTaxCalculatorScreen(navController = navController)
        }
        
        // Mutual Funds & Investment Calculators
        composable(Screen.SIP.route) {
            SIPCalculatorScreen(navController = navController)
        }
        composable(Screen.SWP.route) {
            SWPCalculatorScreen(navController = navController)
        }
        composable(Screen.ELSS.route) {
            ELSSCalculatorScreen(navController = navController)
        }
        composable(Screen.Lumpsum.route) {
            LumpsumCalculatorScreen(navController = navController)
        }
        composable(Screen.CapitalGains.route) {
            CapitalGainsScreen(navController = navController)
        }
    }
}
