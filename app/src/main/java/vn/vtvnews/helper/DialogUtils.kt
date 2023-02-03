package vn.vtvnews.helper

import android.content.Context
import androidx.appcompat.app.AlertDialog

object DialogUtils {

    fun showDialogLoginSuccess(
        context: Context,
        title: String,
        textPositive: String,
        navigateHome: Unit
    ) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle(title)
        builder.setPositiveButton(textPositive) { dialog, _ ->
            dialog.dismiss()
        }
        builder.create()
        builder.show()
    }

    fun showDialog(
        context: Context,
        title: String,
        textPositive: String?,
        textNegative: String?,
        clickPositive: (() -> Unit)? = null,
    ) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle(title)
        builder.setCancelable(false)
        builder.setPositiveButton(textPositive) { _, _ ->
            clickPositive?.invoke()
        }
        builder.setNegativeButton(textNegative) { dialog, _ ->
            dialog.dismiss()
        }
        builder.create()
        builder.show()
    }

}