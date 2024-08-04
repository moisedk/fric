package com.github.fric.data.repositories

import com.github.fric.data.Budget
import com.github.fric.data.local.LocalBudgetsProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class BudgetsRepository : BaseBudgetsRepository {
    override fun getAllBudgets(): Flow<List<Budget>> = flow {
        emit(LocalBudgetsProvider.getAllBudgets())
    }
    override fun setBudget(budget: Budget) {
        TODO("Not yet implemented")
    }

    override fun deleteBudget(budgetId: Int) {
        TODO("Not yet implemented")
    }

    override fun updateBudget(budgetId: Int) {
        TODO("Not yet implemented")
    }
}