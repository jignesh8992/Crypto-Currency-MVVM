package com.jdroid.cryptoapp.common

sealed class Resource<T>(val data: T? = null, val message: String? = null, val stringResource: Int? = null) {

    class Success<T>(data: T) : Resource<T>(data)
    class Error<T>(stringResource: Int? = null, message: String, data: T? = null) : Resource<T>(data, message,stringResource)
    class ServerError<T>(stringResource: Int? = null, message: String, data: T? = null) : Resource<T>(data, message,stringResource)
    class Loading<T> : Resource<T>()
    class NoNetworkConnectivity<T> : Resource<T>()
}