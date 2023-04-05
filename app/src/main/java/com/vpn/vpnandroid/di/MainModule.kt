package com.vpn.vpnandroid.di

import com.vpn.vpnandroid.presentation.viewmodel.StartViewModel
import com.vpn.core.di.resolve
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainModule = module {
    viewModel { resolve(::StartViewModel) }
}