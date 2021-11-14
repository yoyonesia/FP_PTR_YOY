package com.training.miniproject.ui

import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import com.training.miniproject.R

fun Fragment.dismissAllDialogs() {
    parentFragmentManager.fragments.forEach {
        if (it is DialogFragment) {
            it.dismissAllowingStateLoss()
        }
    }
    childFragmentManager.fragments.forEach {
        if (it is DialogFragment) {
            it.dismissAllowingStateLoss()
        }
    }
}

fun Fragment.showLoadingDialog(){
    LoadingDialog.Builder(requireContext())
        .setAnimation(R.raw.ram_loading)
        .setCancelable(false)
        .setMessage("Loading")
        .build()
        .show(parentFragmentManager, "LoadingDialog")
}

fun Fragment.showFailLoadDataDialog(onOkAction: () -> Unit){
    dismissAllDialogs()
    AppDialog.Builder(requireContext())
        .setAnimation(R.raw.ram_failed)
        .setMessage("Failed Load Data")
        .setPositiveButtonText("OK")
        .setPositiveButtonAction {
            dismissAllDialogs()
            onOkAction.invoke()
        }
        .build()
        .show(parentFragmentManager, "FailLoadDataDialog")
}

fun Fragment.showLoginFailedDialog(onOkAction: () -> Unit){
    dismissAllDialogs()
    AppDialog.Builder(requireContext())
        .setAnimation(R.raw.ram_failed)
        .setMessage("Login Failed")
        .setPositiveButtonText("OK")
        .setPositiveButtonAction {
            dismissAllDialogs()
            onOkAction.invoke()
        }
        .build()
        .show(parentFragmentManager, "LoginFailedDialog")
}

fun Fragment.showSucceedDialog(){
    dismissAllDialogs()
    AppDialog.Builder(requireContext())
        .setAnimation(R.raw.person_success)
        .setAutoDismiss(2000)
        .setCancelable(true)
        .setMessage("Successfully Load Data")
        .build()
        .show(parentFragmentManager, "SucceedDialog")
}

fun Fragment.showSessionTimeoutDialog(){
    dismissAllDialogs()
    AppDialog.Builder(requireContext())
        .setCancelable(true)
        .setAnimation(R.raw.ram_failed)
        .setMessage("Session Time Out")
        .build()
        .show(parentFragmentManager, "SessionTimeoutDialog")
}