package com.github.fric.data.repositories

import com.github.fric.data.Expense
import com.github.fric.data.User
import kotlinx.coroutines.flow.Flow

interface ExpenseCategoryRepository {
    fun getAllExpenseCategoriesForUser(user: User): Flow<List<Expense>>
    fun addExpenseCategory(expense: Expense)
    fun deleteExpenseCategoryById(id: Int)
    fun updateExpenseById(id: Int)
}