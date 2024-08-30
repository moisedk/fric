package com.github.fric.data.repositories

import com.github.fric.data.Goal
import com.github.fric.data.local.LocalGoalProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

data object GoalRepository : BaseGoalRepository {
    override fun getAllBudgets(): Flow<List<Goal>> = flow {
        emit(LocalGoalProvider.getAllBudgets())
    }

    override fun setBudget(goal: Goal) {
        /*TODO("Not yet implemented")*/
    }

}

sealed interface BaseGoalRepository {
    fun getAllBudgets(): Flow<List<Goal>>
    fun setBudget(goal: Goal)
}
