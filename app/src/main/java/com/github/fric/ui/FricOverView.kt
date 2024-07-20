package com.github.fric.ui

//import androidx.navigation.NavHostController
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.window.layout.DisplayFeature
import com.github.fric.ui.utils.FricContentType
import com.github.fric.ui.utils.FricNavType

@Composable
fun FricOverviewScreen(
    contentType: FricContentType,
    fricHomeUIState: FricHomeUIState,
    navigationType: FricNavType,
    displayFeatures: List<DisplayFeature>,
    closeRecordScreen: () -> Unit,
    navigateToRecord: (Long, FricContentType) -> Unit,
    modifier: Modifier = Modifier
) {
    // TODO: Create content for the overview screen

}
