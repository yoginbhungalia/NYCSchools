package com.sunya.nycschools.data.remote.common

sealed class NetworkResult <out T> {
    data class Success<T>(val data: T): NetworkResult<T>()
    data class Error(val errorTypes: ErrorTypes): NetworkResult<Nothing>()
}