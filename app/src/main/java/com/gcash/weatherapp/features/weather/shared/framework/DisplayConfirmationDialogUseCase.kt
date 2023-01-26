package com.gcash.weatherapp.features.weather.shared.framework

import android.content.Context
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import javax.inject.Inject

class DisplayConfirmationDialogUseCase @Inject constructor() {

    operator fun invoke(
        context: Context,
        @StringRes title: Int,
        @StringRes message: Int,
        cancelable: Boolean = false,
        @StringRes cancelLabel: Int = android.R.string.cancel,
        @StringRes confirmLabel: Int = android.R.string.ok,
        onCancel: (() -> Unit?)? = null,
        onConfirm: () -> Unit,
    ): AlertDialog = getBaseDialogBuilder(
        context = context,
        title = title,
        cancelable = cancelable,
        cancelLabel = cancelLabel,
        confirmLabel = confirmLabel,
        onCancel = onCancel,
        onConfirm = onConfirm
    ).setMessage(message)
        .show()

    private fun getBaseDialogBuilder(
        context: Context,
        @StringRes title: Int,
        cancelable: Boolean = false,
        @StringRes cancelLabel: Int = android.R.string.cancel,
        @StringRes confirmLabel: Int = android.R.string.ok,
        onCancel: (() -> Unit?)? = null,
        onConfirm: () -> Unit,
    ): MaterialAlertDialogBuilder = MaterialAlertDialogBuilder(context)
        .setCancelable(cancelable)
        .setTitle(title)
        .setNegativeButton(cancelLabel) { dialog, _ ->
            onCancel?.let {
                it()
            }
            dialog.dismiss()
        }
        .setPositiveButton(confirmLabel) { _, _ ->
            onConfirm()
        }
}