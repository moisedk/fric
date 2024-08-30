package com.github.fric.data.local

import com.github.fric.data.Frequency
import com.github.fric.data.Goal
import com.github.fric.data.GoalCategory
import java.time.LocalDate

object LocalGoalProvider {
    fun getAllBudgets(): List<Goal> = allGoals

    private val allGoals = listOf(
        Goal(
            1,
            "Emergency Fund",
            "Save for emergency expenses",
            1000.0,
            0.0,
            LocalDate.now(),
            LocalDate.now().plusDays(30),
            Frequency.MONTHLY,
            GoalCategory.EMERGENCY_FUND,
        )
    )
}