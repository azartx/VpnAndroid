package com.vpn.vpnandroid.presentation.ui

import android.os.Bundle
import android.view.View
import com.vpn.core.coreui.MvvmFragment
import com.vpn.core.di.AbstractFeatureApi
import com.vpn.vpnandroid.R
import com.vpn.vpnandroid.databinding.FragmentStartBinding
import com.vpn.vpnandroid.presentation.viewmodel.StartViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class StartFragment : MvvmFragment<StartViewModel, FragmentStartBinding>() {

    override val dependencies: List<AbstractFeatureApi> get() = listOf()

    override val layoutRes = R.layout.fragment_start
    override val viewModel: StartViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners()
    }

    private fun initListeners() {
        viewBinding.gitButton.setOnClickListener {

            // findNavController().navigate(StartFragmentDirections.navActionOpenGithubUserList())
        }
        viewBinding.weatherButton.setOnClickListener {

            // findNavController().navigate(StartFragmentDirections.navActionOpenWeatherFragment())
        }
    }
}