package com.jdroid.cryptoapp.common

import androidx.annotation.StringRes

sealed class UiText {
    class StringResource(@StringRes val id: Int, val args: Array<Any> = emptyArray()) : UiText()
}