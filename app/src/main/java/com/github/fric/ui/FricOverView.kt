package com.github.fric.ui

//import androidx.navigation.NavHostController
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.window.layout.DisplayFeature
import com.github.fric.data.viewModels.HomeUIState
import com.github.fric.ui.components.BarChartScreen
import com.github.fric.ui.components.PieChartScreen
import com.github.fric.utils.FricContentType
import com.github.fric.utils.FricNavType

@Composable
fun FricOverviewScreen(
    contentType: FricContentType,
    fricHomeUIState: HomeUIState,
    navigationType: FricNavType,
    displayFeatures: List<DisplayFeature>,
    closeRecordScreen: () -> Unit,
    navigateToRecord: (Int, FricContentType) -> Unit,
    modifier: Modifier = Modifier
) {
//    Column(modifier = modifier.fillMaxHeight(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {

        Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            PieChartScreen()
        }
        Spacer(modifier = Modifier.fillMaxWidth().width(12.dp))
        Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            BarChartScreen(color = Color.Green)
        }
//    }
}
