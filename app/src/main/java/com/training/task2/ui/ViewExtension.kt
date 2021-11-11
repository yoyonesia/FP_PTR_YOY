package com.training.task2.ui

import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import com.training.task2.R

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
        .setAnimation(R.raw.loading)
        .setCancelable(false)
        .setMessage("Loading")
        .build()
        .show(parentFragmentManager, "LoadingDialog")
}

fun Fragment.showFailLoadDataDialog(onOkAction: () -> Unit){
    dismissAllDialogs()
    AppDialog.Builder(requireContext())
        .setAnimation(R.raw.person_failed)
        .setMessage("Failed Load Data")
        .setPositiveButtonText("OK")
        .setPositiveButtonAction {
            dismissAllDialogs()
            onOkAction.invoke()
        }
        .build()
        .show(parentFragmentManager, "FailLoadDataDialog")
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