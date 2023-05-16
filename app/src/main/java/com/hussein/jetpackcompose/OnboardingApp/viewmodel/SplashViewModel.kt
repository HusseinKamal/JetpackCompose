package com.hussein.jetpackcompose.OnboardingApp.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hussein.jetpackcompose.OnboardingApp.data.DataStoreRepository
import com.hussein.jetpackcompose.OnboardingApp.navigation.ScreenOnBoarding
import kotlinx.coroutines.launch
import javax.inject.Inject

class SplashViewModel @Inject constructor(
    private val repository: DataStoreRepository
) : ViewModel() {

    private val _isLoading: MutableState<Boolean> = mutableStateOf(true)
    val isLoading: State<Boolean> = _isLoading

    private val _startDestination: MutableState<String> = mutableStateOf(ScreenOnBoarding.Welcome.route)
    val startDestination: State<String> = _startDestination

    init {
        viewModelScope.launch {
            repository.readOnBoardingState().collect { completed ->
                if (completed) {
                    _startDestination.value = ScreenOnBoarding.Home.route
                } else {
                    _startDestination.value = ScreenOnBoarding.Welcome.route
                }
            }
            _isLoading.value = false
        }
    }

}