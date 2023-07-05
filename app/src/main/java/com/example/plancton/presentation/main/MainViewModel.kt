package com.example.plancton.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.plancton.domain.usecase.GetEventsUseCase
import com.example.plancton.navigation.router.MainRouter
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.Calendar
import java.util.Date
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val getEventsUseCase: GetEventsUseCase,
    private val router: MainRouter
) : ViewModel() {

    private val _state: MutableLiveData<MainState> = MutableLiveData(MainState.Initial)
    val state: LiveData<MainState> = _state

    init {
        val calendar = Calendar.getInstance().apply {
            timeInMillis = System.currentTimeMillis()
            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }
        viewModelScope.launch {
            _state.value = MainState.Loading
            delay(2500L)
            _state.value = MainState.Content(
                getEventsUseCase(
                    Date(calendar.timeInMillis),
                    Date(calendar.timeInMillis + 24 * 60 * 60 * 1000 * 365L)
                )
            )
        }
    }

    fun openAdd() {
        router.openAddEvent()
    }
}