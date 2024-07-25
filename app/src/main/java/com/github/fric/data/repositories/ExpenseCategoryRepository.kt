package com.github.fric.data.repositories

import com.github.fric.data.Expense
import com.github.fric.data.User
import kotlinx.coroutines.flow.Flow

interface ExpenseCategoryRepository {
    fun getAllExpensesForUser(user: User): Flow<List<Expense>>
    fun addExpense(expense: Expense)
    fun deleteExpenseById(id: Int)
    fun updateExpense(id: Int)
}