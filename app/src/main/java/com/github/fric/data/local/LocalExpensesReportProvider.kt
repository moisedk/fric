package com.github.fric.data.local

import com.github.fric.data.ExpenseReport
import java.time.LocalDate

object ExpenseReport{
    fun createReport() = listOf(
        ExpenseReport(LocalDate.now().minusDays(3), LocalDate.now().plusDays(7), 200.0, 0.0, listOf(), listOf())
    )
}