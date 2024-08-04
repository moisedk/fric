package com.github.fric.data.repositories

import com.github.fric.data.ExpenseReport
import kotlinx.coroutines.flow.Flow
import java.util.Date

class ExpenseReportRepositoryImpl : ExpenseReportRepository {
    override fun getExpenseReport(userId: Int, from: Date, to: Date): Flow<ExpenseReport> {
        TODO("Not yet implemented")
    }

    override fun getExpenseReportsForRange(
        userId: Int,
        startDate: Date,
        endDate: Date,
        step: Int
    ): Flow<List<ExpenseReport>> {
        TODO("Not yet implemented")
    }

    override fun addExpenseReport(expenseReport: ExpenseReport) {
        TODO("Not yet implemented")
    }
}