package com.github.fric.data.repositories

import com.github.fric.data.ExpenseReport
import com.github.fric.utils.ReportStep
import kotlinx.coroutines.flow.Flow
import java.util.Date

interface ExpenseReportRepository {
    fun getExpenseReport(userId: Int, from: Date, to: Date): Flow<ExpenseReport>
    fun getExpenseReportsForRange(userId: Int, startDate: Date, endDate: Date, step: Int = ReportStep.WEEKLY): Flow<List<ExpenseReport>>
    fun addExpenseReport(expenseReport: ExpenseReport)
}