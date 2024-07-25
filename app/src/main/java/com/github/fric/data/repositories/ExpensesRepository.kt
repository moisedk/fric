package com.github.fric.data.repositories

import com.github.fric.data.Expense
import com.github.fric.data.User
import kotlinx.coroutines.flow.Flow

interface ExpensesRepository {
    fun getAllExpensesForUser(user: User): Flow<List<Expense>>
    fun getExpensesForCategory(categoryId: Int): Flow<List<Expense>>
    fun getExpenseById(id: Int): Flow<Expense?>
    fun addExpense(expense: Expense)
    fun deleteExpenseById(id: Int)
    fun updateExpense(id: Int)
}