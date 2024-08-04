package com.github.fric.ui;

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.window.layout.DisplayFeature
import com.github.fric.data.viewModels.HomeUIState
import com.github.fric.utils.FricContentType
import com.github.fric.utils.FricNavType

@Composable
fun FricExpensesScreen(
    contentType: FricContentType,
    fricHomeUIState: HomeUIState,
    navigationType: FricNavType,
    displayFeatures: List<DisplayFeature>,
    closeRecordScreen: () -> Unit,
    navigateToRecord: (Int, FricContentType) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxHeight(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
        Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
            Text(text = "Expenses", modifier = Modifier.align(Alignment.CenterVertically))
        }
    }

}
