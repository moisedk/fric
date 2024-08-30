package com.github.fric.data.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.fric.data.ExpenseReport
import com.github.fric.data.repositories.BaseExpenseReportRepository
import com.github.fric.data.repositories.ExpenseReportRepository
import com.github.fric.utils.FricContentType
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class FricHomeViewModel(reportRepository: BaseExpenseReportRepository = ExpenseReportRepository) :
    ViewModel() {
    private val reports = reportRepository.getExpenseReportsForRange()
    private val _uiState: StateFlow<HomeUiState> = reports.map { reports -> HomeUiState.Success(reports) }
        .stateIn(
            scope = viewModelScope,
            initialValue = HomeUiState.Loading,
            started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5_000))
    var uiState: StateFlow<HomeUiState> = _uiState

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


sealed interface HomeUiState {
    data object Loading: HomeUiState
    data class Success(val reports: List<ExpenseReport>): HomeUiState
    data class Error(val error: Throwable): HomeUiState
}
