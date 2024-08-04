package com.github.fric.data

import java.time.LocalDate

data class User (
    val id: Int,
    val fName: String,
    val lName: String,
    val email: String,
    val password: String
)
{
    fun getFullName() = "$fName $lName"
    fun login(email: String, password: String): Boolean {
        return this.email == email && this.password == password
    }
    fun register(email: String, password: String): Boolean {
        return this.email == email && this.password == password
    }
}

data class Expense (
    val id: Int,
    val amount: Double,
    val category: ExpenseCategory,
    val date: LocalDate,
    val description: String,
    val isRecurring: Boolean = false
)
data class ExpenseCategory (
    val id: Int,
    val name: String,
    val description: String
)
data class Budget (
    val id: Int,
    val description: String,
    val categoryId: ExpenseCategory,
    val amountAssigned: Double,
    val amountSpent: Double,
    val startDate: LocalDate,
    val endDate: LocalDate,
)
data class ExpenseReport (
    val from: LocalDate,
    val to: LocalDate,
    val totalBudget: Double,
    val totalSpent: Double,
    val expenses: List<Expense>,
    val budgets: List<Budget>,
    )
