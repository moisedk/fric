package com.github.fric

import android.os.Bundle
import android.os.SystemClock
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.github.fric.data.viewModels.BudgetViewModel
import com.github.fric.data.viewModels.FricHomeViewModel
import com.github.fric.ui.FricApp
import com.github.fric.ui.LoginScreen
import com.github.fric.ui.theme.AppTheme
import com.google.accompanist.adaptive.calculateDisplayFeatures

class MainActivity : ComponentActivity() {
    private val homeViewModel: FricHomeViewModel by viewModels()
    private val fricViewModel: BudgetViewModel by viewModels()

    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            var isUserLoggedIn by remember { mutableStateOf(false) }
            if (isUserLoggedIn) {
                AppTheme {
                    val windowSize = calculateWindowSizeClass(this)
                    val displayFeatures = calculateDisplayFeatures(this)
                    val homeUiState by homeViewModel.uiState.collectAsStateWithLifecycle()

                    FricApp(
                        windowSize = windowSize,
                        displayFeatures = displayFeatures,
                        fricHomeUIState = homeUiState,
                        budgetViewModel = fricViewModel,
                        closeRecordScreen = {
                            homeViewModel.closeRecordScreen()
                        },
                        navigateToRecord = { expenseId, pane ->
                            homeViewModel.openReportExpenseScreen(expenseId, pane)
                        }
                    )
                }
            }
            else {
                // Login Screen
                LoginScreen(
                    onRegisterClick = { /*TODO*/ },
                    onForgotPasswordClick = { /*TODO*/ },
                    onLoginSuccess = { /*TODO*/},
                    onLoginFailure = {/*TODO*/},
                    onLoginClick = {
                        username, password ->
                        homeViewModel.login(username, password) { loginResult ->
                            isUserLoggedIn = loginResult
                        }
                    }
                )
            }
        }

        SystemClock.sleep(1000)
    }

}