package com.github.fric.data.repositories

import com.github.fric.data.Expense
import com.github.fric.data.User
import kotlinx.coroutines.flow.Flow

class  ExpenseCategoryRepositoryImpl: ExpenseCategoryRepository {
    override fun getAllExpenseCategoriesForUser(user: User): Flow<List<Expense>> {
        TODO("Not yet implemented")
    }

    override fun addExpenseCategory(expense: Expense) {
        TODO("Not yet implemented")
    }

    override fun deleteExpenseCategoryById(id: Int) {
        TODO("Not yet implemented")
    }

    override fun updateExpenseById(id: Int) {
        TODO("Not yet implemented")
    }
}