package com.example.spendingapp.core.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.Navigation
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.spendingapp.core.presentation.util.Background
import com.example.spendingapp.core.presentation.util.Screen
import com.example.spendingapp.features.balance.presentation.BalanceScreenCore
import com.example.spendingapp.features.spending_overview.presentation.ui.SpendingOverviewCore
import com.example.spendingapp.ui.theme.SpendingAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SpendingAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Navigation(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }

    @Composable
    fun Navigation(modifier: Modifier = Modifier) {
        val navController = rememberNavController()
        Background()

        NavHost(
            navController = navController,
            startDestination = Screen.SpendingOverview
        ) {

            composable<Screen.SpendingOverview> {
               SpendingOverviewCore(
                   onBalanceClick = { navController.navigate(Screen.Balance) },
                   onAddSpendingClick = { navController.navigate(Screen.SpendingDetails) }
               )
            }


            composable<Screen.SpendingDetails> {
                Box(
                    modifier = modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "Spending Details")
                }
            }


            composable<Screen.Balance> {
                Box(
                    modifier = modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    BalanceScreenCore {
                        navController.popBackStack()
                    }
                }
            }

        }
    }
}







