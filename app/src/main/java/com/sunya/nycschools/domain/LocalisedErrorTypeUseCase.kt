package com.sunya.nycschools.domain

import com.sunya.nycschools.R
import com.sunya.nycschools.data.remote.common.ErrorTypes
import javax.inject.Inject

class LocalisedErrorTypeUseCase @Inject constructor(){
    operator fun invoke(errorTypes: ErrorTypes): Int {
        return when (errorTypes) {
            is ErrorTypes.ServerError -> {
               R.string.server_error
            }
            is ErrorTypes.EmptyDataError -> {
                R.string.no_data_found
            }
        }
    }
}