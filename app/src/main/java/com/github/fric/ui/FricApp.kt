package com.github.fric.ui

import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteType
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.window.layout.DisplayFeature
import com.github.fric.ui.navigation.FricRoute
import com.github.fric.ui.utils.FricContentType
import com.github.fric.ui.utils.FricNavType

    // Maps FricApp Navigation type to NavigationSuiteType for adaptive navigation suite
   private  fun NavigationSuiteType.toNavType() = when (this) {
       NavigationSuiteType.NavigationBar -> FricNavType.BOTTOM_NAVIGATION
       NavigationSuiteType.NavigationRail -> FricNavType.NAVIGATION_RAIL
       NavigationSuiteType.NavigationDrawer -> FricNavType.PERMANENT_NAVIGATION_DRAWER
       else -> FricNavType.BOTTOM_NAVIGATION
    }

@Composable
private fun FricOverViewScreen(
    navController: NavHostController,
    contentType: FricContentType,
    fricHomeUIState: FricHomeUIState,
    fricNavType: FricNavType,
    displayFeatures: List<DisplayFeature>,
    closeRecordScreen: () -> Unit,
    navigateToRecord:  (Long, FricContentType) -> Unit,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = FricRoute.OVERVIEW,
        modifier = modifier
    ) {
        composable(FricRoute.OVERVIEW) {
            FricOverviewScreen(
                contentType = contentType,
                fricHomeUIState = fricHomeUIState,
                navigationType = fricNavType,
                displayFeatures = displayFeatures,
                closeRecordScreen = closeRecordScreen,
                navigateToRecord = navigateToRecord
            )
        }
        composable(FricRoute.EXPENSES) {
            // #TODO: Add Expenses Screen or placeholder
        }
        composable(FricRoute.BUDGET) {
            // #TODO: Add Budget Screen or placeholder
        }
    }
}