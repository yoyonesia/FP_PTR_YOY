package com.training.task2.ui

import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.core.view.isGone
import androidx.fragment.app.DialogFragment
import com.training.task2.R
import kotlinx.android.synthetic.main.common_dialog_default.*
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class LoadingDialog : DialogFragment(), DialogDelegate, CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    private val animation: Int? by lazy { arguments?.getInt(ARG_ANIMATION) }
    private val message: String? by lazy { arguments?.getString(ARG_MESSAGE) }
    private val isDismissedOnTapAnywhere: Boolean? by lazy {
        arguments?.getBoolean(ARG_IS_DISMISSED_ON_TAP_ANYWHERE, false)
    }
    private val autoDismissTime: Long? by lazy {
        arguments?.getLong(ARG_AUTO_DISMISS_TIME)?.takeIf { it > 0 }
    }

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
    ): View? = inflater.inflate(R.layout.loading_dialog, container)

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
                this@LoadingDialog.dismiss()
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

    class Builder(private val context: Context) {
        private var onDismissListener: (() -> Unit)? = null
        private var autoDismissTime: Long? = null
        private var isCancelable: Boolean? = null
        private var isDismissedOnTapAnywhere: Boolean? = null
        private var animation: Int? = null
        private var message: String? = null

        fun setAnimation(animationResId: Int) = apply {
            this.animation = animationResId
        }

        fun setMessage(@StringRes messageResId: Int) = apply {
            this.message = context.getString(messageResId)
        }

        fun setMessage(message: String) = apply {
            this.message = message
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

        fun build(): DialogFragment {
            val dialog = LoadingDialog()
            val arguments = Bundle().apply {
                animation?.let { putInt(ARG_ANIMATION, it) }
                message?.let { putString(ARG_MESSAGE, it) }
            }
            dialog.arguments = arguments
            onDismissListener?.let { dialog.onDismissListener = it }
            isCancelable?.let { dialog.isCancelable = it }
            return dialog
        }
    }
}
