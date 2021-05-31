package com.example.delivery_much_teste_android.shared

import android.content.Context
import android.content.DialogInterface
import androidx.appcompat.app.AlertDialog

/**
 * @author Vinicius Gouveia on 26/05/2021
 */
@Suppress("LongParameterList")
class GenericDialog(
    private val context: Context,
    private val title: String,
    private val body: String
) {

    fun oneButtonDialog(
        buttonText: String,
        callback: DialogInterface.OnClickListener?
    ): AlertDialog = AlertDialog.Builder(context)
        .setCancelable(false)
        .setPositiveButton(buttonText, callback)
        .create()
}
