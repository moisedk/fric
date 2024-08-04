package com.github.fric.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.outlined.ChevronRight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.fric.R
import com.github.fric.data.Budget
import com.github.fric.data.local.LocalBudgetsProvider
import com.github.fric.data.viewModels.BudgetUiState

@Composable

fun FricBudgetScreen(
    fricUiState: BudgetUiState,
    onExpandBudget: (Int, Boolean) -> Unit,
    onAddBudget: (String, String, Double, Double, String, String) -> Unit
) {
    when (fricUiState) {
        is BudgetUiState.Loading -> Text(text = "Loading...")
        is BudgetUiState.Success -> BudgetList(
            budgets = fricUiState.budgets,
            onExpand = onExpandBudget,
            onAddBudget = onAddBudget
        )

        is BudgetUiState.Error -> Text(text = "Error: ${fricUiState.error.message}")
    }
}

@Composable
fun BudgetList(
    budgets: List<Budget>,
    onExpand: (Int, Boolean) -> Unit,
    onAddBudget: (String, String, Double, Double, String, String) -> Unit
) {
    val selectedBudgetId = remember { mutableStateOf<Int?>(null) }
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(budgets) { budget ->
            BudgetCard(
                budget = budget,
                isExpanded = selectedBudgetId.value == budget.id,
            )
            {
                onExpand(budget.id, true)
            }
        }
        item {
            Button(onClick = {
                onAddBudget("Budget Name", "Description", 100.0, 200.0, "Category", "Subcategory")
            }) {
                Text("Add Budget")
            }
        }
    }
}

@Composable
fun BudgetCard(
    budget: Budget,
    isExpanded: Boolean = false,
    onExpandBudget: () -> Unit,
) {
    Card(
        modifier = Modifier
            .background(color = MaterialTheme.colorScheme.primaryContainer)
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onExpandBudget() },

        ) {
        Column {
            Row {
                Column {
                    Image(
                        painter = painterResource(id = R.drawable.budget_icon),
                        contentDescription = "Budget image",
                        modifier = Modifier
                            .size(width = 100.dp, height = 100.dp)

                    )
                }
                Spacer(
                    modifier = Modifier
                        .width(4.dp)
                        .height(100.dp)
                        .background(color = MaterialTheme.colorScheme.background)
                )
                Column {
                    Text(
                        text = budget.description,
                        modifier = Modifier
                            .padding(8.dp),
                        textAlign = TextAlign.Center,
                        maxLines = 1,
                        fontStyle = MaterialTheme.typography.headlineSmall.fontStyle,
                        fontSize = 18.sp,
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                    )
                    Text(
                        text = "Assigned: ${budget.amountAssigned}",
                        modifier = Modifier
                            .padding(8.dp),
                        textAlign = TextAlign.Start,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                    Text(
                        text = "Spent: ${budget.amountSpent}",
                        modifier = Modifier.padding(8.dp),
                        textAlign = TextAlign.Start,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )

                }
                Column(horizontalAlignment = Alignment.End, modifier = Modifier.fillMaxWidth()) {
                    Button(onClick = { /*TODO*/ }, colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)) {
                        Icon(
                            imageVector = Icons.Filled.ChevronRight,
                            contentDescription = "Expand or Collapse",
                            tint = MaterialTheme.colorScheme.onPrimaryContainer,
                        )
                    }
                }
            }
        }
    }

}

@Composable
@Preview
fun BudgetListPreview() {
    val budgets = LocalBudgetsProvider.getAllBudgets()
    BudgetList(budgets = budgets, onExpand = { _, _ -> }, onAddBudget = { _, _, _, _, _, _ -> })
}