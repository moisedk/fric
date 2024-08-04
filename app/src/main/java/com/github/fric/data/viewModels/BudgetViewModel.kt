package com.github.fric.data.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.fric.data.Budget
import com.github.fric.data.ExpenseCategory
import com.github.fric.data.repositories.BaseBudgetsRepository
import com.github.fric.data.repositories.BudgetsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.time.LocalDate

// Listens for data from the BudgetsRepository, and updates the UI accordingly
class BudgetViewModel(private val budgetsRepository: BaseBudgetsRepository = BudgetsRepository()): ViewModel() {
    private val budgets = budgetsRepository.getAllBudgets()
    private val _uiState: StateFlow<BudgetUiState> = budgets.map { budgets -> BudgetUiState.Success(budgets) }
        .stateIn(
            scope = viewModelScope,
            initialValue = BudgetUiState.Loading,
            started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5_000))
    val uiState: StateFlow<BudgetUiState> = _uiState
    fun addBudget(description: String, categoryId: ExpenseCategory, amountAssigned: Double, amountSpent: Double, startDate: LocalDate, endDate: LocalDate) {
        viewModelScope.launch {
            budgetsRepository.setBudget(Budget(
                id = 0,
                description = description,
                categoryId = categoryId,
                amountAssigned = amountAssigned,
                amountSpent = amountSpent,
                startDate = startDate,
                endDate = endDate
            ))
        }
    }
}

sealed interface BudgetUiState {
    data object Loading: BudgetUiState
    data class Success(val budgets: List<Budget>): BudgetUiState
    data class Error(val error: Throwable): BudgetUiState

}