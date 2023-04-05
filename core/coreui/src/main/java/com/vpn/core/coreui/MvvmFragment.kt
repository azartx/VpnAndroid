package com.vpn.core.coreui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel

abstract class MvvmFragment<VM : MvvmViewModel, VB : ViewDataBinding>(@LayoutRes contentLayoutId: Int = 0) :
    BaseScopeFragment(contentLayoutId),
    CoroutineScope {

    override val coroutineContext = Job() + Dispatchers.Main
    abstract val layoutRes: Int
    abstract val viewModel: VM
    lateinit var viewBinding: VB


    @CallSuper
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setupViewBinding(inflater, container)
        return viewBinding.root
    }

    @CallSuper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.init(this.arguments ?: Bundle())
        onCreate()
        viewModel.onStarted()

        lifecycleScope.launchWhenStarted {
            viewModel.showInfoDialogEvent.collect {pair ->
                pair.first?.let {
                    showInfoDialog(it, pair.second)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        clearViewBinding()
    }

    open fun onCreate() {
    }

    override fun onPause() {
        super.onPause()
        viewModel.clear()
    }

    open fun onLoadingChange(isLoading: Boolean) {}

    private fun setupViewBinding(inflater: LayoutInflater, container: ViewGroup?) {
        val binding = DataBindingUtil.inflate<VB>(inflater, layoutRes, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        //binding.setVariable(, this)
       // binding.setVariable(viewModelBR, viewModel)
        viewBinding = binding
    }

    private fun clearViewBinding() {
        with(viewBinding) {
           // setVariable(viewBR, null)
         //   setVariable(viewModelBR, null)
            unbind()
            lifecycleOwner = null
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        coroutineContext.cancel()
    }
}