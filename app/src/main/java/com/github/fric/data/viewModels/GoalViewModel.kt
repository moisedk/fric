package com.github.fric.data.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.fric.data.Budget
import com.github.fric.data.ExpenseCategory
import com.github.fric.data.Frequency
import com.github.fric.data.Goal
import com.github.fric.data.GoalCategory
import com.github.fric.data.GoalStatus
import com.github.fric.data.Priority
import com.github.fric.data.repositories.BaseBudgetsRepository
import com.github.fric.data.repositories.BaseGoalRepository
import com.github.fric.data.repositories.BudgetsRepository
import com.github.fric.data.repositories.GoalRepository
import com.github.fric.utils.FricContentType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.time.LocalDate

class GoalViewModel(private val goalsRepository: BaseGoalRepository = GoalRepository): ViewModel() {
    private val goals = goalsRepository.getAllBudgets()
    private val _uiState: StateFlow<GoalUiState> = goals.map { goals -> GoalUiState.Success(goals) }
        .stateIn(
            scope = viewModelScope,
            initialValue = GoalUiState.Loading,
            started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5_000))
    var uiState: StateFlow<GoalUiState> = _uiState
    fun addGoal(description: String, categoryId: ExpenseCategory, amountAssigned: Double, amountSpent: Double, startDate: LocalDate, endDate: LocalDate) {
        viewModelScope.launch {
            goalsRepository.setBudget(Goal(
                name = "",
                description = "",
                targetAmount = 12.0,
                currentAmount  = 0.0,
                startDate  = LocalDate.now(),
                deadline = LocalDate.now(),
                category = GoalCategory.OTHER

            ))
        }
    }

}


sealed interface GoalUiState {
    data object Loading: GoalUiState
    data class Success(val goals: List<Goal>): GoalUiState
    data class Error(val error: Throwable): GoalUiState
    data object AddingGoal: GoalUiState
}