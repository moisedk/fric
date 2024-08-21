package com.github.fric.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.fric.R
import com.github.fric.data.Budget
import com.github.fric.data.local.LocalBudgetsProvider
import com.github.fric.data.local.LocalExpenseCategoriesProvider
import com.github.fric.data.viewModels.BudgetUiState
import com.github.fric.data.viewModels.BudgetViewModel
import java.time.LocalDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun FricBudgetScreen(
    budgetViewModel: BudgetViewModel
) {
    val uiState by budgetViewModel.uiState.collectAsState()
    when (uiState) {
        is BudgetUiState.Loading -> Text(text = "Loading...")
        is BudgetUiState.Success -> {
            val budgets = remember {
                mutableStateOf((uiState as BudgetUiState.Success).budgets)
            }
            BudgetList(

                budgets = budgets.value,
                onAddBudget = {
//                    budgets.value += Budget(
//                        id = 0,
//                        description = "New Budget",
//                        categoryId = LocalExpenseCategoriesProvider.ExpenseCategories.BILLS,
//                        amountAssigned = 1232.0,
//                        amountSpent = 0.0,
//                        startDate = LocalDate.now(),
//                        endDate = LocalDate.now().plusDays(7)
//                    )

                }
            )
        }

        is BudgetUiState.AddingBudget -> {
            ModalBottomSheet(onDismissRequest = {  }) {
                Spacer(
                    modifier = Modifier
                        .height(400.dp)
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.tertiary)
                )
            }
        }

        is BudgetUiState.Error -> Text(text = "Error: ${(uiState as BudgetUiState.Error).error}")
    }
}

@Composable
fun BudgetList(
    budgets: List<Budget>,
    onAddBudget: () -> Unit
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
                // onclick to expand
                selectedBudgetId.value =
                    if (selectedBudgetId.value == budget.id) null else budget.id
            }
        }
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(onClick = {
                    onAddBudget()
                }) {
                    Button(
                        onClick = { onAddBudget() },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.primary,
                            contentColor = MaterialTheme.colorScheme.onPrimary
                        )
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Add,
                            contentDescription = "Add Budget")
                    }
                }
            }

        }
    }
}

@Composable
fun BudgetCard(
    budget: Budget,
    isExpanded: Boolean = false,
    onExpand: () -> Unit
) {
    val regularCardHeight = 150
    val expandedCardHeight = 200
    Card(
        modifier = Modifier
            .background(color = MaterialTheme.colorScheme.primaryContainer)
            .fillMaxWidth()
            .padding(8.dp)
            .height(if (!isExpanded) regularCardHeight.dp else expandedCardHeight.dp)
    ) {
        Column(modifier = Modifier.clickable { onExpand() }) {
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
                        text = "ASSIGNED: ${budget.amountAssigned}",
                        modifier = Modifier
                            .padding(8.dp),
                        textAlign = TextAlign.Start,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                    Text(
                        text = "SPENT: ${budget.amountSpent}",
                        modifier = Modifier.padding(8.dp),
                        textAlign = TextAlign.Start,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )

                }
                Column(horizontalAlignment = Alignment.End, modifier = Modifier.fillMaxWidth()) {
                    Button(
                        onClick = { },
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                        modifier = Modifier.rotate(if (isExpanded) 90f else 0f)
                    ) {
                        Icon(
                            imageVector = Icons.Filled.ChevronRight,
                            contentDescription = "Expand or Collapse",
                            tint = MaterialTheme.colorScheme.onPrimaryContainer,
                        )
                    }
                }
            }
            if (isExpanded) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(text = budget.description)
                    Text(text = "FROM: ${budget.startDate}")
                    Text(text = "TO: ${budget.endDate}")
                }
            }
        }
    }

}

@Composable
@Preview
fun BudgetListPreview() {
    val budgets = LocalBudgetsProvider.getAllBudgets()
    BudgetList(budgets = budgets, onAddBudget = { })
}