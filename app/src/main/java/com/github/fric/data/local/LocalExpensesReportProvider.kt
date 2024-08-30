package com.github.fric.data.local

import com.github.fric.data.ExpenseReport
import java.time.LocalDate

object LocalExpensesReportProvider{
    private val allReports = listOf(
        ExpenseReport(LocalDate.now().minusDays(6), LocalDate.now().plusDays(1), 200.0, 150.0, listOf(), listOf()),
        ExpenseReport(LocalDate.now().minusDays(3), LocalDate.now().plusDays(4), 200.0, 32.0, listOf(), listOf()),
        ExpenseReport(LocalDate.now().minusDays(1), LocalDate.now().plusDays(6), 200.0, 233.0, listOf(), listOf()),
    )
    fun getAllReports(): List<ExpenseReport> {
        return allReports

    }
}