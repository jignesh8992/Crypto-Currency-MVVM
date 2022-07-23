package com.jdroid.cryptoapp.common

import android.content.Context
import android.net.ConnectivityManager
import android.net.ConnectivityManager.*
import android.net.NetworkCapabilities.*
import android.os.Build
import androidx.appcompat.app.AlertDialog
import com.jdroid.cryptoapp.common.callback.OnUserActionListener

object Utils

fun Context.hasInternetConnection(): Boolean {
    val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        val activeNetwork = connectivityManager.activeNetwork ?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
        return when {
            capabilities.hasTransport(TRANSPORT_WIFI) -> true
            capabilities.hasTransport(TRANSPORT_CELLULAR) -> true
            capabilities.hasTransport(TRANSPORT_ETHERNET) -> true
            else -> false
        }
    } else {
        connectivityManager.activeNetworkInfo?.run {
            return when (type) {
                TYPE_WIFI -> true
                TYPE_MOBILE -> true
                TYPE_ETHERNET -> true
                else -> false
            }
        }
    }
    return false
}

fun Context.showAlert(title: String? = null, message: String? = null, positiveButtonText: String? = null, negativeButtonText: String? = null, onUserActionListener: OnUserActionListener? = null, isNeedToCheckInternetConnectivity: Boolean = false) {

    val builder = AlertDialog.Builder(this).setCancelable(false)

    title?.let {
        builder.setTitle(it)
    }
    message?.let {
        builder.setMessage(it)
    }
    positiveButtonText?.let {
        builder.setPositiveButton(positiveButtonText) { dialog, _ ->
            if (!isNeedToCheckInternetConnectivity || (isNeedToCheckInternetConnectivity && hasInternetConnection())) {
                dialog.cancel()
                onUserActionListener?.onYes()
            }
        }
    }
    negativeButtonText?.let {
        builder.setNegativeButton(negativeButtonText) { dialog, _ ->
            dialog.cancel()
            onUserActionListener?.onNo()
        }
    }

    val alertDialog = builder.create()
    alertDialog.show()

    val positiveButton = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE)
    positiveButton?.let {
        it.setOnClickListener {
            if (!isNeedToCheckInternetConnectivity || (isNeedToCheckInternetConnectivity && hasInternetConnection())) {
                alertDialog.dismiss()
            }
        }
    }
}