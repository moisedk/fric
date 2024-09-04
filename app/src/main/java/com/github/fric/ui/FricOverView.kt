package com.github.fric.ui

//import androidx.navigation.NavHostController
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.window.layout.DisplayFeature
import com.commandiron.wheel_picker_compose.WheelDateTimePicker
import com.commandiron.wheel_picker_compose.core.WheelPickerDefaults
import com.github.fric.data.viewModels.FricHomeViewModel
import com.github.fric.data.viewModels.HomeUiState
import com.github.fric.ui.components.FricExpenseReportScreen
import com.github.fric.utils.FricContentType
import com.github.fric.utils.FricNavType
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FricOverviewScreen(
    contentType: FricContentType,
    fricHomeUIState: HomeUiState,
    homeViewModel: FricHomeViewModel,
    navigationType: FricNavType,
    displayFeatures: List<DisplayFeature>,
    modifier: Modifier = Modifier
) {
    var openBottomSheet by rememberSaveable { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val scope = rememberCoroutineScope()

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { openBottomSheet = !openBottomSheet }) {
                Icon(imageVector = Icons.Filled.Add, contentDescription = "Add")
            }
        }
    ) { contentPadding ->
        Column(
            modifier = Modifier.padding(contentPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            FricExpenseReportScreen(expenseReportViewModel = homeViewModel)
            if (openBottomSheet) {
                ModalBottomSheet(
                    onDismissRequest = { openBottomSheet = false },
                    sheetState = sheetState
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {

                        Text(
                            text = "Record Spending",
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.titleLarge
                        )
                        var title by remember { mutableStateOf("") }
                        var amount by remember { mutableStateOf("") }
                        var date by remember { mutableStateOf(LocalDate.now()) }
                        var category by remember { mutableStateOf("") }
                        var description by remember { mutableStateOf("") }
                        val dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
                        OutlinedTextField(
                            value = title,
                            onValueChange = { title = it },
                            modifier = Modifier.padding(horizontal = 16.dp),
                            label = { Text("Name") }
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        OutlinedTextField(
                            value = amount,
                            onValueChange = { value -> amount = value },
                            modifier = Modifier.padding(horizontal = 16.dp),
                            label = { Text("Amount") }
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        DropdownMenu(expanded = false, onDismissRequest = { /*TODO*/ }) {

                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        Row {
                            Text(text = "Date: ")
                            WheelDateTimePicker(
                                startDateTime = LocalDateTime.now(),
                                maxDateTime = LocalDateTime.now(),
                                size = DpSize(200.dp, 50.dp),
                                rowCount = 3,
                                textStyle = MaterialTheme.typography.titleSmall,
                                selectorProperties = WheelPickerDefaults.selectorProperties(
                                    enabled = true,
                                    shape = RoundedCornerShape(0.dp),
                                )
                            ){ snappedDateTime -> }
                        }
                        // Save button
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Button(
                                onClick = {
                                    scope
                                        .launch { sheetState.hide() }
                                        .invokeOnCompletion {
                                            if (!sheetState.isVisible) {
                                                openBottomSheet = false
                                            }
                                        }
                                }
                            ) {
                                Text("Save")
                            }
                        }
                    }
                }
            }
        }

    }
//    if (showDialog) {
//        ExpenseDialog(onDismiss = { showDialog = false }) { expense ->
//            // Handle saving expense
//            showDialog = false
//        }
//    }

}

@Composable
private fun RecordSheet() {

}
