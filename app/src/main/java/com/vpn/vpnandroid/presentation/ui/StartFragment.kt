package com.vpn.vpnandroid.presentation.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import com.vpn.core.coreui.MvvmFragment
import com.vpn.core.di.AbstractFeatureApi
import com.vpn.feature_vpn.vpn_core.di.VpnCoreFeatureImpl
import com.vpn.feature_vpn.vpn_core.domain.VpnServiceManagerImpl
import com.vpn.feature_vpn.vpn_core_api.di.VpnCoreFeatureApi
import com.vpn.feature_vpn.vpn_core_api.manager.IVpnServiceManager
import com.vpn.vpnandroid.R
import com.vpn.vpnandroid.databinding.FragmentStartBinding
import com.vpn.vpnandroid.presentation.viewmodel.StartViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class StartFragment : MvvmFragment<StartViewModel, FragmentStartBinding>() {

    override val layoutRes = R.layout.fragment_start
    override val viewModel: StartViewModel by viewModel()
    override val dependencies: List<AbstractFeatureApi>
        get() = listOf(VpnCoreFeatureImpl)

    private val serviceManager: IVpnServiceManager by inject()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners()

        Log.e("ffff", serviceManager::class.java.toString())
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