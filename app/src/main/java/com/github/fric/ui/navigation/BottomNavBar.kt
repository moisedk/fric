package com.github.fric.ui.navigation

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource


@Composable
fun FricBottomNavigationBar(
    selectedDestination: String,
    navigateToTopLevelDestination: (dest: FricTopLevelDestination) -> Unit,
) {
        NavigationBar {
            FRIC_TOP_LEVEL_DESTINATIONS.forEach { destination ->
            // Check out the many styling arguments that I could use for this
            NavigationBarItem(
                selected = selectedDestination == destination.route,
                onClick = { navigateToTopLevelDestination(destination) },
                icon = {
                    Icon(
                        imageVector = destination.selectedIcon,
                        contentDescription = stringResource(id = destination.iconTextId)
                    )
                })
        }
    }

}