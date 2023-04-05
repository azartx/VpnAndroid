package com.vpn.core.coreui

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.launch

abstract class BaseViewModel(application: Application) :
    AndroidViewModel(application) {

    val coroutineExceptionHanlder = CoroutineExceptionHandler { _, throwable ->
        Log.e("", throwable.message ?: "ERROR!!!")
    }

    val job = SupervisorJob()

    fun launchInBackground(block: suspend () -> Unit) =
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHanlder + job) { block.invoke() }

    override fun onCleared() {
        super.onCleared()
        job.cancelChildren()
    }

    fun cancelJob() = job.cancelChildren()
}