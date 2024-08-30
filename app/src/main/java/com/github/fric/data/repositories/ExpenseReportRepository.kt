package com.github.fric.data.repositories

import com.github.fric.data.ExpenseReport
import com.github.fric.data.local.LocalExpensesReportProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.util.Date

data object ExpenseReportRepository : BaseExpenseReportRepository {
    override fun getExpenseReport(/*userId: Int, from: Date, to: Date*/): Flow<ExpenseReport> {
        TODO("Not yet implemented")
    }

    override fun getExpenseReportsForRange(
        userId: Int,
        startDate: Date?,
        endDate: Date?,
        step: Int
    ): Flow<List<ExpenseReport>> = flow {
        emit(LocalExpensesReportProvider.getAllReports())
    }

    override fun addExpenseReport(expenseReport: ExpenseReport) {
        TODO("Not yet implemented")
    }
}