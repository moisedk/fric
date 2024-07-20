package com.github.fric.ui

import com.github.fric.model.FricHomeData

data class FricHomeUIState(
    val loading: Boolean = false,
    val error: String? = null,
    val data: List<FricHomeData> = emptyList()
    //TODO("Add more properties")
)