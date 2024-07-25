package com.github.fric.data.repositories

import com.github.fric.data.Budget
import kotlinx.coroutines.flow.Flow

interface BudgetRepository {
    fun getAllBudgetsForUser(userId: Int): Flow<List<Budget>>
    fun setBudget(budget: Budget)
    fun deleteBudget(budgetId: Int)
    fun updateBudget(budgetId: Int)
}