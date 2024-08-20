package com.github.fric.data.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import com.github.fric.data.ExpenseReport
import com.github.fric.data.repositories.ExpenseReportRepository
import com.github.fric.data.repositories.ExpenseReportRepositoryImpl
import com.github.fric.utils.FricContentType
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.Firebase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class FricHomeViewModel(reportRepository: ExpenseReportRepository = ExpenseReportRepositoryImpl()) :
    ViewModel() {
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

    fun login(username: String, password: String, onLoginResult: (Boolean) -> Unit) {
        val auth: FirebaseAuth = Firebase.auth
        auth.signInWithEmailAndPassword(username, password)
            .addOnCompleteListener { login ->
                onLoginResult(login.isSuccessful)
            }
    }
}


    data class HomeUIState(
        val currentReport: ExpenseReport? = null,
        val isAddingExpense: Boolean = false,
        val loading: Boolean = false,
        val error: String? = null
    )