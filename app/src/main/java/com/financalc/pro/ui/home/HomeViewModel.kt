package com.financalc.pro.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {

    private val _welcomeMessage = MutableLiveData<String>().apply {
        value = "Welcome to FinCalc Pro\n\nYour comprehensive Indian financial calculator with 30+ calculators for loans, investments, post office schemes, retirement planning, and more."
    }
    val welcomeMessage: LiveData<String> = _welcomeMessage
}
