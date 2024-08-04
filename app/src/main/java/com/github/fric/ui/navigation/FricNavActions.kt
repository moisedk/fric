package com.github.fric.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalanceWallet
import androidx.compose.material.icons.filled.AccountTree
import androidx.compose.material.icons.filled.Dashboard
import androidx.compose.material.icons.outlined.AccountBalanceWallet
import androidx.compose.material.icons.outlined.AccountTree
import androidx.compose.material.icons.outlined.Dashboard
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.github.fric.R

object FricRoute {
    const val OVERVIEW = "Overview"
    const val BUDGET = "budget"
    const val EXPENSES = "expenses"
}

data class FricTopLevelDestination(
    val route: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val iconTextId: Int
)
class FricNavActions (private val navController: NavHostController) {
    fun navigateTo(destination: FricTopLevelDestination) {
        navController.navigate(destination.route) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            // Prevent adding the same destination multiple times to the back stack if the same icon is clicked multiple times
            launchSingleTop = true
            // Restore state when reselecting a previously selected item
            restoreState = true
        }
    }
}

val FRIC_TOP_LEVEL_DESTINATIONS = listOf(
    FricTopLevelDestination(
        route = FricRoute.EXPENSES,
        selectedIcon = Icons.Filled.AccountBalanceWallet,
        unselectedIcon = Icons.Outlined.AccountBalanceWallet,
        iconTextId = R.string.bottom_nav_exp_text
    ),
    FricTopLevelDestination(
        route = FricRoute.OVERVIEW,
        selectedIcon = Icons.Filled.Dashboard,
        unselectedIcon = Icons.Outlined.Dashboard,
        iconTextId = R.string.overview
    ),

    FricTopLevelDestination(
        route = FricRoute.BUDGET,
        selectedIcon = Icons.Filled.AccountTree,
        unselectedIcon = Icons.Outlined.AccountTree,
        iconTextId = R.string.bottom_nav_budget_text
        )
)