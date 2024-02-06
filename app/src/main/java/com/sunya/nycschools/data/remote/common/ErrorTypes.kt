package com.sunya.nycschools.data.remote.common

sealed class ErrorTypes {
    data object EmptyDataError: ErrorTypes()
    data class ServerError(val code: Int, val message: String): ErrorTypes()
}