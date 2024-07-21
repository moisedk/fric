package com.github.fric.ui

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteType
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.window.layout.DisplayFeature
import androidx.window.layout.FoldingFeature
import com.github.fric.ui.navigation.FricNavActions
import com.github.fric.ui.navigation.FricRoute
import com.github.fric.ui.utils.DevicePosture
import com.github.fric.ui.utils.FricContentType
import com.github.fric.ui.utils.FricNavType
import com.github.fric.ui.utils.isBookPosture
import com.github.fric.ui.utils.isSeparating

// Maps FricApp Navigation type to NavigationSuiteType for adaptive navigation suite
private fun NavigationSuiteType.toNavType() = when (this) {
    NavigationSuiteType.NavigationBar -> FricNavType.BOTTOM_NAVIGATION
    NavigationSuiteType.NavigationRail -> FricNavType.NAVIGATION_RAIL
    NavigationSuiteType.NavigationDrawer -> FricNavType.PERMANENT_NAVIGATION_DRAWER
    else -> FricNavType.BOTTOM_NAVIGATION
}

@Composable
public fun FricApp(
    windowSize: WindowSizeClass,
    fricHomeUIState: FricHomeUIState,
    fricNavType: FricNavType,
    displayFeatures: List<DisplayFeature>,
    closeRecordScreen: () -> Unit = {},
    navigateToRecord: (Long, FricContentType) -> Unit = { _, _ -> },
) {
    val foldingFeature = displayFeatures.filterIsInstance<FoldingFeature>().firstOrNull()

    val foldingDevicePosture = when {
        isBookPosture(foldingFeature) ->
            DevicePosture.BookPosture(foldingFeature.bounds)

        isSeparating(foldingFeature) ->
            DevicePosture.Separating(foldingFeature.bounds, foldingFeature.orientation)

        else -> DevicePosture.NormalPosture

    }
    val contentType = when (windowSize.widthSizeClass) {
        WindowWidthSizeClass.Compact -> FricContentType.SINGLE_PANE
        WindowWidthSizeClass.Medium -> if (foldingDevicePosture != DevicePosture.NormalPosture) {
            FricContentType.TWO_PANE
        } else {
            FricContentType.SINGLE_PANE
        }
        WindowWidthSizeClass.Expanded -> FricContentType.TWO_PANE
        else -> FricContentType.SINGLE_PANE
    }
    val navController = rememberNavController()
    val navActions = remember(navController) { FricNavActions(navController) }
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination?.route ?: FricRoute.OVERVIEW
}

@Composable
private fun FricNavHost(
    navController: NavHostController,
    contentType: FricContentType,
    fricHomeUIState: FricHomeUIState,
    fricNavType: FricNavType,
    displayFeatures: List<DisplayFeature>,
    closeRecordScreen: () -> Unit,
    navigateToRecord: (Long, FricContentType) -> Unit,
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