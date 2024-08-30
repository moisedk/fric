package com.github.fric.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.fric.R
import com.github.fric.data.Goal
import com.github.fric.data.viewModels.GoalUiState
import com.github.fric.data.viewModels.GoalViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FricGoalScreen(
    goalViewModel: GoalViewModel
) {
    val uiState by goalViewModel.uiState.collectAsState()
    when (uiState) {
        is GoalUiState.Loading -> Text(text = "Loading...")
        is GoalUiState.Success -> {
            val goals = remember {
                mutableStateOf((uiState as GoalUiState.Success).goals)
            }
            GoalList(
                goals = goals.value,
                onAddGoal = {
//                    goalViewModel.addGoal()
                }
            )
        }
        is GoalUiState.AddingGoal -> {
            ModalBottomSheet(onDismissRequest = { /* goalViewModel.cancelAddingGoal()*/ }) {
                // Add goal form would go here
                Spacer(
                    modifier = Modifier
                        .height(400.dp)
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.tertiary)
                )
            }
        }
        is GoalUiState.Error -> Text(text = "Error: ${(uiState as GoalUiState.Error).error}")
    }
}

@Composable
fun GoalList(
    goals: List<Goal>,
    onAddGoal: () -> Unit
) {
    val selectedGoalId = remember { mutableStateOf<Int?>(null) }
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(goals) { goal ->
            GoalCard(
                goal = goal,
                isExpanded = selectedGoalId.value == goal.id,
                onExpand = {
                    selectedGoalId.value = if (selectedGoalId.value == goal.id) null else goal.id
                }
            )
        }
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = { onAddGoal() },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = MaterialTheme.colorScheme.onPrimary
                    )
                ) {
                    Icon(
                        imageVector = Icons.Filled.Add,
                        contentDescription = "Add Goal"
                    )
                }
            }
        }
    }
}

@Composable
fun GoalCard(
    goal: Goal,
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
                        painter = painterResource(id = R.drawable.budget_icon), // You'll need to add this icon
                        contentDescription = "Goal image",
                        modifier = Modifier.size(width = 100.dp, height = 100.dp)
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
                        text = goal.name,
                        modifier = Modifier.padding(8.dp),
                        textAlign = TextAlign.Center,
                        maxLines = 1,
                        fontStyle = MaterialTheme.typography.headlineSmall.fontStyle,
                        fontSize = 18.sp,
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                    )
                    Text(
                        text = "TARGET: ${goal.targetAmount}",
                        modifier = Modifier.padding(8.dp),
                        textAlign = TextAlign.Start,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                    Text(
                        text = "SAVED: ${goal.currentAmount}",
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
                    Text(text = goal.description)
                    Text(text = "DEADLINE: ${goal.deadline}")
                }
            }
        }
    }
}