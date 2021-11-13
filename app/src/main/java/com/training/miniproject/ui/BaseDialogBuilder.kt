package com.training.miniproject.ui

import android.content.Context
import androidx.annotation.StringRes
import androidx.fragment.app.DialogFragment

interface Builder

abstract class BaseDialogBuilder(private val context: Context) {
    protected var positiveButtonText: String? = null
    protected var positiveButtonAction: ((DialogDelegate) -> Unit)? = null
    protected var negativeButtonText: String? = null
    protected var negativeButtonAction: ((DialogDelegate) -> Unit)? = null
    protected var message: String? = null


    fun setMessage(@StringRes messageResId: Int) = apply {
        this.message = context.getString(messageResId)
    }

    fun setMessage(message: String) = apply {
        this.message = message
    }

    fun setPositiveButtonText(buttonText: String) = apply {
        this.positiveButtonText = buttonText
    }

    fun setPositiveButtonText(@StringRes buttonTextResId: Int) = apply {
        this.positiveButtonText = context.getString(buttonTextResId)
    }

    fun setPositiveButtonAction(action: (DialogDelegate) -> Unit) = apply {
        this.positiveButtonAction = action
    }

    fun setNegativeButtonText(buttonText: String) = apply {
        this.negativeButtonText = buttonText
    }

    fun setNegativeButtonText(@StringRes buttonTextResId: Int) = apply {
        this.negativeButtonText = context.getString(buttonTextResId)
    }

    fun setNegativeButtonAction(action: (DialogDelegate) -> Unit) = apply {
        this.negativeButtonAction = action
    }

    abstract fun build(): DialogFragment
}