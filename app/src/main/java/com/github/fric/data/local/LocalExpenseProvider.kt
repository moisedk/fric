package com.github.fric.data.local

import com.github.fric.data.Expense
import java.time.LocalDate

object LocalExpenseProvider {
    private val allExpenses = listOf (
        Expense(
            1, 124.0, LocalExpenseCategoriesProvider.ExpenseCategories.BILLS, LocalDate.now(), "Rent", false
    ),
        Expense(
            2, 124.0, LocalExpenseCategoriesProvider.ExpenseCategories.TRANSPORTATION, LocalDate.now().minusDays(2), "Rent", false
        ),
        Expense(
            3, 124.0, LocalExpenseCategoriesProvider.ExpenseCategories.TRANSPORTATION, LocalDate.now().minusDays(3), "Rent", false
        ),
        Expense(
            4, 124.0, LocalExpenseCategoriesProvider.ExpenseCategories.RENT, LocalDate.now().minusDays(4), "Rent", false
        ),
        Expense(
            5, 124.0, LocalExpenseCategoriesProvider.ExpenseCategories.HEALTH, LocalDate.now().minusDays(5), "Rent", false
        ),
        Expense(
            6, 124.0, LocalExpenseCategoriesProvider.ExpenseCategories.OTHER, LocalDate.now().minusDays(6), "Rent", false
        ),
        Expense(
            7, 124.0, LocalExpenseCategoriesProvider.ExpenseCategories.TRANSPORTATION, LocalDate.now().minusDays(7), "Rent", false
        ),
        Expense(
            8, 124.0, LocalExpenseCategoriesProvider.ExpenseCategories.TRANSPORTATION, LocalDate.now().minusDays(8), "Rent", false
        ),
        Expense(
            9, 124.0, LocalExpenseCategoriesProvider.ExpenseCategories.TRANSPORTATION, LocalDate.now().minusDays(9), "Rent", false
        ),
        Expense(
            10, 124.0, LocalExpenseCategoriesProvider.ExpenseCategories.TRANSPORTATION, LocalDate.now().minusDays(10), "Rent", false
        ),
        Expense(
            11, 124.0, LocalExpenseCategoriesProvider.ExpenseCategories.TRANSPORTATION, LocalDate.now().minusDays(11), "Rent", false
        )
    )
    fun getAllExpensesForUser(userId: Int) : List<Expense> {
        return allExpenses
    }
}
