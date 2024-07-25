package com.github.fric.data

import java.util.Date

data class User (
    val id: Int,
    val fName: String,
    val lName: String,
    val email: String,
    val password: String
)
data class Expense (
    val id: Int,
    val userId: Int,
    val amount: Double,
    val category: ExpenseCategory,
    val date: Date,
    val description: String,
    val isRecurring: Boolean = false
)
data class ExpenseCategory (
    val id: Int,
    val userId: Int,
    val name: String,
    val budgetAssigned: Double,
    val budgetSpent: Double
)
data class Budget (
    val id: Int,
    val userId: Int,
    val categoryId: Int,
    val name: String,
    val amount: Double,
    val startDate: Date,
    val endDate: Date,
)
data class ExpenseReport (
    val id: Int,
    val userId: Int,
    val from: Date,
    val to: Date,
    val totalBudget: Double,
    val totalSpent: Double,
    val spendingTrends: Map<String, Double>
    )