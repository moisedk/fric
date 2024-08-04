package com.github.fric.data.local

import com.github.fric.data.ExpenseCategory

object LocalExpenseCategoriesProvider {
    object ExpenseCategories {
        val BILLS = ExpenseCategory (
            1, "Bills", "Bills"
        )
        val RENT = ExpenseCategory (
            2, "Rent", "Rent"
        )
        val ENTERTAINMENT = ExpenseCategory (
            3, "Entertainment", "Entertainment"
        )
        val TRANSPORTATION = ExpenseCategory (
            4, "Transportation", "Transportation"
        )
        val UTILITIES = ExpenseCategory (
            5, "Utilities", "Utilities"
        )
        val HEALTH = ExpenseCategory (
            6, "Health", "Health"
        )
        val CLOTHING = ExpenseCategory (
            7, "Clothing", "Clothing"
        )
        val OTHER = ExpenseCategory (
            8, "Other", "Other"
        )
    }
}