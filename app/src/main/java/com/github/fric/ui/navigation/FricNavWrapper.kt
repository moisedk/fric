package com.github.fric.ui.navigation

import androidx.activity.compose.BackHandler
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.material3.adaptive.currentWindowSize
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffoldLayout
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteType
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import androidx.window.core.layout.WindowHeightSizeClass
import androidx.window.core.layout.WindowSizeClass
import androidx.window.core.layout.WindowWidthSizeClass
import com.github.fric.utils.FricNavContentPosition
import kotlinx.coroutines.launch

class FricNavSuiteScope(
    val navSuiteType: NavigationSuiteType
)

// Extension function to check if the current window is compact
fun WindowSizeClass.isCompact() =
    windowWidthSizeClass == WindowWidthSizeClass.COMPACT || windowHeightSizeClass == WindowHeightSizeClass.COMPACT

@Composable
fun FricNavigationWrapper(
    selectedDestination: String,
    navigateToTopLevelDestination: (FricTopLevelDestination) -> Unit,
    content: @Composable FricNavSuiteScope.() -> Unit
) {
    val adaptiveInfo = currentWindowAdaptiveInfo()
    val windowSize = with(LocalDensity.current) {
        currentWindowSize().toSize().toDpSize()
    }
    // Determine the navigation layout type (NavigationBar (bottom), NavigationRail, NavigationDrawer) based on the adaptive info and window size
    val navLayoutType = when {
        adaptiveInfo.windowPosture.isTabletop -> NavigationSuiteType.NavigationBar
        adaptiveInfo.windowSizeClass.isCompact() -> NavigationSuiteType.NavigationBar
        adaptiveInfo.windowSizeClass.windowWidthSizeClass == WindowWidthSizeClass.EXPANDED && windowSize.width >= 1200.dp -> NavigationSuiteType.NavigationDrawer
        else -> NavigationSuiteType.NavigationRail
    }
    // The content position (top or center) depends on the height of the screen
    // Only affects the modal and permanent drawers
    val navContentPosition = when (adaptiveInfo.windowSizeClass.windowHeightSizeClass) {
        WindowHeightSizeClass.COMPACT -> FricNavContentPosition.TOP
        WindowHeightSizeClass.MEDIUM,
        WindowHeightSizeClass.EXPANDED -> FricNavContentPosition.CENTER

        else -> FricNavContentPosition.TOP

    }
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()
    // Avoid opening the modal drawer when there is a permanent drawer or a bottom nav bar,
    // but always allow closing an open drawer.
    val gesturesEnabled =
        drawerState.isOpen || navLayoutType == NavigationSuiteType.NavigationRail

    BackHandler(enabled = drawerState.isOpen) {
        coroutineScope.launch {
            drawerState.close()
        }
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        gesturesEnabled = gesturesEnabled,
        drawerContent = {
            ModalNavigationDrawerContent(
                selectedDestination = selectedDestination,
                navigationContentPosition = navContentPosition,
                navigateToTopLevelDestination = navigateToTopLevelDestination,
                onDrawerClicked = {
                    coroutineScope.launch {
                        drawerState.close()
                    }
                }
            )
        },
    ) {
        NavigationSuiteScaffoldLayout(
            layoutType = navLayoutType,
            navigationSuite = {
                when (navLayoutType) {
                    NavigationSuiteType.NavigationBar -> FricBottomNavigationBar(
                        selectedDestination = selectedDestination,
                        navigateToTopLevelDestination = navigateToTopLevelDestination
                    )
                    NavigationSuiteType.NavigationRail -> FricNavigationRail(
                        selectedDestination = selectedDestination,
                        navigationContentPosition = navContentPosition,
                        navigateToTopLevelDestination = navigateToTopLevelDestination,
                        onDrawerClicked = {
                            coroutineScope.launch {
                                drawerState.open()
                            }
                        }
                    )
                    NavigationSuiteType.NavigationDrawer -> PermanentNavigationDrawerContent(
                        selectedDestination = selectedDestination,
                        navigationContentPosition = navContentPosition,
                        navigateToTopLevelDestination = navigateToTopLevelDestination
                    )
                }
            }
        ) {
            FricNavSuiteScope(navLayoutType).content()
        }
    }
}
