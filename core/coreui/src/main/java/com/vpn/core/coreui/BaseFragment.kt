package com.vpn.core.coreui

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.vpn.core.coreui.utils.KeyboardUtils
import com.google.android.material.dialog.MaterialAlertDialogBuilder

abstract class BaseFragment(@LayoutRes layoutId: Int) : Fragment(layoutId) {

    open val navController: NavController
        get() {
            val view =
                ((requireActivity().window.decorView.rootView.findViewById(android.R.id.content) as ViewGroup).getChildAt(
                    0
                ) as ViewGroup)
            return if (view is FragmentContainerView) {
                Navigation.findNavController(view)
            } else {
                findNavController()
            }
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        KeyboardUtils.hideSoftKeyboard(activity)
    }

    fun closeFragment() {
        navController.popBackStack()
    }

    fun showInfoDialog(msg: String, title: String? = null, positiveButtonText: String? = null) {
        val buttonText: String = positiveButtonText ?: getString(android.R.string.ok)
        MaterialAlertDialogBuilder(requireContext())
            .setMessage(msg)
            .setTitle(title)
            .setPositiveButton(buttonText, null)
            .show()
    }
}
