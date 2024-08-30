package com.github.fric.data

import java.time.LocalDate
import java.util.UUID

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

data class Goal(
    val id: Int = 1,
    val name: String,
    val description: String,
    val targetAmount: Double,
    val currentAmount: Double = 0.0,
    val startDate: LocalDate = LocalDate.now(),
    val deadline: LocalDate,
    val frequency: Frequency = Frequency.MONTHLY,
    val category: GoalCategory,
    val priority: Priority = Priority.MEDIUM,
    val status: GoalStatus = GoalStatus.IN_PROGRESS
)

enum class Frequency {
    DAILY, WEEKLY, MONTHLY, YEARLY
}

enum class GoalCategory {
    EMERGENCY_FUND, DEBT_REPAYMENT, RETIREMENT, VACATION, EDUCATION, HOME_PURCHASE, CAR_PURCHASE, INVESTMENT, OTHER
}

enum class Priority {
    LOW, MEDIUM, HIGH
}

enum class GoalStatus {
    NOT_STARTED, IN_PROGRESS, COMPLETED, ABANDONED
}