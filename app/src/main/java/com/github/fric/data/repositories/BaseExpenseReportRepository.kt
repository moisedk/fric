package com.github.fric.data.repositories

import com.github.fric.data.ExpenseReport
import com.github.fric.utils.ReportStep
import kotlinx.coroutines.flow.Flow
import java.util.Date

sealed interface BaseExpenseReportRepository {
    fun getExpenseReport(): Flow<ExpenseReport>
    fun getExpenseReportsForRange(userId: Int = -1, startDate: Date? = null, endDate: Date? = null, step: Int = ReportStep.WEEKLY): Flow<List<ExpenseReport>>
    fun addExpenseReport(expenseReport: ExpenseReport)
}