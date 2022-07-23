package com.jdroid.cryptoapp.common

import android.view.View


val View.visible: View
    get() = apply { visibility = View.VISIBLE }

val View.gone: View
    get() = apply { visibility = View.GONE }

val View.invisible: View
    get() = apply { visibility = View.INVISIBLE }

fun View.isVisible(): Boolean = visibility == View.VISIBLE
fun View.isGone(): Boolean = visibility == View.GONE
fun View.isInvisible(): Boolean = visibility == View.INVISIBLE