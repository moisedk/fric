package com.github.fric.data.local

import com.github.fric.data.Budget
import com.github.fric.data.local.LocalExpenseCategoriesProvider.ExpenseCategories
import java.time.LocalDate
// Local data source for budgets (mock data)
object LocalBudgetsProvider {
    private val allBudgets = listOf(
        Budget(
            1, "Bills", ExpenseCategories.BILLS, 200.0, 0.0, LocalDate.now().minusDays(3), LocalDate.now().plusDays(7)
        ),
        Budget(
            2, "Rent", ExpenseCategories.RENT, 1000.0, 0.0, LocalDate.now(), LocalDate.now().plusDays(9)
        ),
        Budget(
            3, "Entertainment", ExpenseCategories.ENTERTAINMENT, 500.0, 0.0, LocalDate.now(), LocalDate.now().plusDays(23)
        ),
        Budget(
            4, "Transportation", ExpenseCategories.TRANSPORTATION, 300.0, 0.0, LocalDate.now(), LocalDate.now().plusDays(3)
        ),
        Budget(
            5, "Utilities", ExpenseCategories.UTILITIES, 150.0, 0.0, LocalDate.now(), LocalDate.now().plusDays(87)
        ),
        Budget(
            6, "Health", ExpenseCategories.HEALTH, 250.0, 0.0, LocalDate.now(), LocalDate.now().plusDays(2343)
        ),
        Budget(
            7, "Clothing", ExpenseCategories.CLOTHING, 150.0, 0.0, LocalDate.now(), LocalDate.now().plusDays(122)
        ),
        Budget(
            8, "Other", ExpenseCategories.OTHER, 0.0, 0.0, LocalDate.now(), LocalDate.now().plusDays(54)
        )

    )
    fun getAllBudgets() : List<Budget> {
        return allBudgets
    }
}