package com.github.fric.data.viewModels

import androidx.lifecycle.ViewModel
import com.github.fric.data.ExpenseReport
import com.github.fric.data.repositories.ExpenseReportRepository
import com.github.fric.data.repositories.ExpenseReportRepositoryImpl
import com.github.fric.utils.FricContentType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class FricHomeViewModel(reportRepository: ExpenseReportRepository = ExpenseReportRepositoryImpl()) : ViewModel() {
    private val _uiState = MutableStateFlow(HomeUIState())
    val uiState = _uiState.asStateFlow()
    init {
        observeReport()
    }

    private fun observeReport() {
//        TODO("Not yet implemented")
    }

    fun closeRecordScreen() {
//        TODO("Not yet implemented")
    }

    fun openReportExpenseScreen(expenseId: Int, pane: FricContentType) {
//        TODO("Not yet implemented")
    }
}


data class HomeUIState(
    val currentReport: ExpenseReport? = null,
    val isAddingExpense: Boolean = false,
    val loading: Boolean = false,
    val error: String? = null
)