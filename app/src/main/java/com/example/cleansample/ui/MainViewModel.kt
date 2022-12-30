package com.example.cleansample.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cleansample.core.utils.ResultWrapper
import com.example.cleansample.domain.GetUserUserUseCase
import com.example.cleansample.domain.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getUserUserUseCase: GetUserUserUseCase
) : ViewModel() {

    private val _flowUsers = MutableSharedFlow<List<UserView>>()
    val flowUsers = _flowUsers.asSharedFlow()


    init {
        getUserFromLocal()
    }

    private var job: Job? = null
    private fun getUserFromLocal() {
        job?.cancel()
        job = viewModelScope.launch {
            getUserUserUseCase().onEach { result ->
                when (result) {
                    is ResultWrapper.Loading -> {
                        Log.d("TAG", "getUserFromLocal: Loading")
                    }
                    is ResultWrapper.Failure -> {
                        Log.d("TAG", "getUserFromLocal:Failure ${result.message}")
                    }
                    is ResultWrapper.Success -> {
                        Log.d("TAG", "getUserFromLocal: Success${result.resultData}")
                    }
                }
            }.launchIn(this)
        }
    }

}