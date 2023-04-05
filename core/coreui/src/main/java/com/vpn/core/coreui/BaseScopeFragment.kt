package com.vpn.core.coreui

import androidx.annotation.LayoutRes
import com.vpn.core.di_android.DependenciesComponent
import com.vpn.core.di_android.fragmentScope
import org.koin.android.scope.AndroidScopeComponent
import org.koin.core.scope.Scope

abstract class BaseScopeFragment(
    @LayoutRes contentLayoutId: Int
) : BaseFragment(contentLayoutId), AndroidScopeComponent, DependenciesComponent {

    override val scope: Scope by fragmentScope()
}
