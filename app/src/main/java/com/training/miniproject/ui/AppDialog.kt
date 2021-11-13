package com.training.miniproject.ui

import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.core.view.isGone
import androidx.fragment.app.DialogFragment
import com.training.miniproject.R
import kotlinx.android.synthetic.main.common_dialog_default.*
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class AppDialog : DialogFragment(), DialogDelegate, CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    private val animation: Int? by lazy { arguments?.getInt(ARG_ANIMATION) }
    private val message: String? by lazy { arguments?.getString(ARG_MESSAGE) }
    private val error: String? by lazy { arguments?.getString(ARG_ERROR) }
    private val positiveButtonText: String? by lazy { arguments?.getString(ARG_POSITIVE_BUTTON_TEXT) }
    private val negativeButtonText: String? by lazy { arguments?.getString(ARG_NEGATIVE_BUTTON_TEXT) }
    private val isDismissedOnTapAnywhere: Boolean? by lazy {
        arguments?.getBoolean(ARG_IS_DISMISSED_ON_TAP_ANYWHERE, false)
    }
    private val autoDismissTime: Long? by lazy {
        arguments?.getLong(ARG_AUTO_DISMISS_TIME)?.takeIf { it > 0 }
    }

    private var positiveButtonAction: ((DialogDelegate) -> Unit)? = null
    private var negativeButtonAction: ((DialogDelegate) -> Unit)? = null
    private var onDismissListener: (() -> Unit)? = null
    private var dismissDialogJob: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, R.style.AppTheme_Dialog)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.common_dialog_default, container)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        animation?.run {
            if (this == 0){
                animationView.isGone = true
            }else{
                animationView.setAnimation(this)
            }
        } ?: run { animationView.isGone = true }

        message?.run(messageTextView::setText) ?: run {
            messageTextView.isGone = true
        }
        error?.run(errorTextView::setText) ?: run {
            errorTextView.isGone = true
        }
        positiveButtonText?.run {
            positiveButton.text = this
            positiveButton.setOnClickListener {
                positiveButtonAction?.invoke(this@AppDialog)
            }
        } ?: run {
            positiveButton.isGone = true
        }
        negativeButtonText?.run {
            negativeButton.text = this
            negativeButton.setOnClickListener {
                negativeButtonAction?.invoke(this@AppDialog)
            }
        } ?: run {
            negativeButton.isGone = true
        }
        isDismissedOnTapAnywhere?.let {
            if (it) {
                isCancelable = true
                view.setOnClickListener {
                    dismiss()
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        autoDismissTime?.let {
            dismissDialogJob = launch {
                delay(it)
                this@AppDialog.dismiss()
            }
        }
    }

    override fun onPause() {
        super.onPause()
        dismissDialogJob?.cancel()
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        onDismissListener?.invoke()
        dismissDialogJob?.cancel()
    }

    companion object {
        private const val ARG_MESSAGE = "ARG_MESSAGE"
        private const val ARG_ERROR = "ARG_ERROR"
        private const val ARG_POSITIVE_BUTTON_TEXT = "ARG_POSITIVE_BUTTON_TEXT"
        private const val ARG_NEGATIVE_BUTTON_TEXT = "ARG_NEGATIVE_BUTTON_TEXT"
        private const val ARG_IS_DISMISSED_ON_TAP_ANYWHERE = "ARG_IS_DISMISSED_ON_TAP_ANYWHERE"
        private const val ARG_AUTO_DISMISS_TIME = "ARG_AUTO_DISMISS_TIME"
        private const val ARG_ANIMATION = "ARG_ANIMATION"
    }

    class Builder(private val context: Context) : BaseDialogBuilder(context) {
        private var error: String? = null
        private var onDismissListener: (() -> Unit)? = null
        private var autoDismissTime: Long? = null
        private var isCancelable: Boolean? = null
        private var isDismissedOnTapAnywhere: Boolean? = null
        private var animation: Int? = null

        fun setAnimation(animationResId: Int) = apply {
            this.animation = animationResId
        }

        fun setError(@StringRes errorResId: Int) = apply {
            this.error = context.getString(errorResId)
        }

        fun setError(error: String) = apply {
            this.error = error
        }

        fun setOnDismissListener(listener: () -> Unit) = apply {
            this.onDismissListener = listener
        }

        fun setAutoDismiss(timeMillis: Long) = apply {
            this.autoDismissTime = timeMillis
        }

        fun setCancelable(isCancelable: Boolean) = apply {
            this.isCancelable = isCancelable
        }

        fun setDismissedOnTapAnywhere(isDismissedOnTapAnywhere: Boolean) = apply {
            this.isDismissedOnTapAnywhere = isDismissedOnTapAnywhere
        }

        override fun build(): DialogFragment {
            val dialog = AppDialog()
            val arguments = Bundle().apply {
                animation?.let { putInt(ARG_ANIMATION, it) }
                message?.let { putString(ARG_MESSAGE, it) }
                error?.let { putString(ARG_ERROR, it) }
                positiveButtonText?.let { putString(ARG_POSITIVE_BUTTON_TEXT, it) }
                negativeButtonText?.let { putString(ARG_NEGATIVE_BUTTON_TEXT, it) }
                autoDismissTime?.let { putLong(ARG_AUTO_DISMISS_TIME, it) }
                isDismissedOnTapAnywhere?.let { putBoolean(ARG_IS_DISMISSED_ON_TAP_ANYWHERE, it) }
            }
            dialog.arguments = arguments
            positiveButtonAction?.let { dialog.positiveButtonAction = it }
            negativeButtonAction?.let { dialog.negativeButtonAction = it }
            onDismissListener?.let { dialog.onDismissListener = it }
            isCancelable?.let { dialog.isCancelable = it }
            return dialog
        }
    }
}
