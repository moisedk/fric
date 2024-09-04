package com.github.fric.ui.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.github.fric.data.ExpenseReport
import com.github.fric.data.viewModels.FricHomeViewModel
import com.github.fric.data.viewModels.HomeUiState
import java.time.format.DateTimeFormatter

@Composable
fun FricExpenseReportScreen(
    expenseReportViewModel: FricHomeViewModel
) {
    val uiState by expenseReportViewModel.uiState.collectAsState()
    when (uiState) {
        is HomeUiState.Loading -> Text(text = "Loading...")
        is HomeUiState.Success -> {
            val reports = remember {
                mutableStateOf((uiState as HomeUiState.Success).reports)
            }
            ExpenseReportList(
                reports = reports.value,
                onAddReport = {
//                    expenseReportViewModel.startAddingReport()
                }
            )
        }
        is HomeUiState.Error -> Text(text = "Error: ${(uiState as HomeUiState.Error).error}")
    }
}

@Composable
fun ExpenseReportList(
    reports: List<ExpenseReport>,
    onAddReport: () -> Unit
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
        Text(
            text = "Welcome, Moise",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(16.dp)
        )
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(reports) { report ->
                ExpenseReportCard(report = report)
            }
        }
    }
}

@SuppressLint("DefaultLocale")
@Composable
fun ExpenseReportCard(report: ExpenseReport) {
    val isOverBudget = report.totalSpent > report.totalBudget
    val dateFormatter = DateTimeFormatter.ofPattern("MMM d, yyyy")

    Card(
        modifier = Modifier
            .width(300.dp)
            .height(200.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (isOverBudget) MaterialTheme.colorScheme.errorContainer
            else MaterialTheme.colorScheme.primaryContainer
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = "${report.from.format(dateFormatter)} - ${report.to.format(dateFormatter)}",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = if (isOverBudget) "Over Budget!" else "Within Budget",
                    style = MaterialTheme.typography.titleSmall,
                    color = if (isOverBudget) MaterialTheme.colorScheme.error
                    else MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold
                )
            }
            Column {
                Text(
                    text = "Budget: $${String.format("%.2f", report.totalBudget)}",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
                Text(
                    text = "Spent: $${String.format("%.2f", report.totalSpent)}",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
                Text(
                    text = "Current: $${String.format("%.2f", report.totalBudget - report.totalSpent)}",
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold,
                    color = if (isOverBudget) MaterialTheme.colorScheme.error
                    else MaterialTheme.colorScheme.onPrimaryContainer
                )
            }
        }
    }
}