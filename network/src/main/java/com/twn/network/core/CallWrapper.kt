package com.twn.network.core

import com.twn.network.ApiOperation

internal object CallWrapper {
    inline fun <T> selfCall(apiCall: () -> T): ApiOperation<T> {
        return try {
            ApiOperation.Success(data = apiCall())
        } catch (exception: Exception){
            ApiOperation.Failure(exception = exception)
        }
    }
}