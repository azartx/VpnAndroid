package com.vpn.core.coreui

import android.app.Application
import android.os.Bundle
import android.util.Log
import androidx.annotation.MainThread
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavArgs
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

abstract class MvvmViewModel(application: Application) : BaseViewModel(application) {

    lateinit var args: Bundle

    val _error = MutableStateFlow("")
    val error: StateFlow<String> = _error.asStateFlow()

    val _showInfoDialogEvent = MutableStateFlow<Pair<String?, String?>>(null to null)
    val showInfoDialogEvent = _showInfoDialogEvent.asStateFlow()


    internal fun init(args: Bundle) {
        val initialized = this::args.isInitialized
        this.args = args
        if (!initialized) {
            onCreated()
        }
    }

    open fun onCreated() {
    }

    open fun onStarted() {
    }

    open fun clear() {
        cancelJob()
    }

    private var handleErrorsJob: Job? = null

    protected open fun <T> handleErrors(block: suspend (isErrorOccurred: Boolean) -> T) {
        handleErrorsJob?.let { return }
        handleErrorsJob = launchInBackground {
            try {
                block(false)
            } catch (exception: Exception) {
                Log.e(javaClass.simpleName, "", exception)
                if (exception !is CancellationException) {
                    viewModelScope.launch {
                        _showInfoDialogEvent.emit(
                            Pair(
                                exception.localizedMessage,
                                null
                            ) as Pair<String, String?>
                        )
                    }
                    block(true)
                }
            } finally {
                handleErrorsJob = null
            }
        }
    }

    fun showError(title: String, message: String?) {
        _showInfoDialogEvent.tryEmit(Pair(title, message))
    }
}

@MainThread
inline fun <reified Args : NavArgs> MvvmViewModel.navArgs() =
    androidx.navigation.NavArgsLazy(Args::class) {
        args ?: throw IllegalStateException("Fragment $this has null arguments")
    }