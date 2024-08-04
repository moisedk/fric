package com.github.fric.data.repositories

import com.github.fric.data.Expense
import com.github.fric.data.User
import com.github.fric.data.local.LocalExpenseProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ExpenseRepositoryImpl: ExpenseRepository {
    override fun getAllExpensesForUser(user: User): Flow<List<Expense>> = flow{
        emit(LocalExpenseProvider.getAllExpensesForUser(user.id))
    }

    override fun getExpensesForCategory(categoryId: Int): Flow<List<Expense>> {
        TODO("Not yet implemented")
    }

    override fun getExpenseById(id: Int): Flow<Expense?> {
        TODO("Not yet implemented")
    }

    override fun addExpense(expense: Expense) {
        TODO("Not yet implemented")
    }

    override fun deleteExpenseById(id: Int) {
        TODO("Not yet implemented")
    }

    override fun updateExpense(id: Int) {
        TODO("Not yet implemented")
    }
}