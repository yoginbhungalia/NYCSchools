package com.sunya.nycschools.data.repository

import com.sunya.nycschools.data.model.School
import com.sunya.nycschools.data.remote.ApiService
import com.sunya.nycschools.data.remote.common.ErrorTypes
import com.sunya.nycschools.data.remote.common.NetworkResult
import javax.inject.Inject

class SchoolRepositoryImp @Inject constructor(private val apiService: ApiService): SchoolRepository {
    override suspend fun getSchoolList(): NetworkResult<List<School>> {
        val schoolListResponse = apiService.getSchoolList()
        return if (schoolListResponse.isSuccessful) {
            if (schoolListResponse.body() != null && schoolListResponse.body()!!.isNotEmpty()) {
                NetworkResult.Success(
                    schoolListResponse.body()!!.map {
                        it.toSchool()
                    }
                )
            } else {
                NetworkResult.Error(
                    ErrorTypes.EmptyDataError
                )
            }
        } else {
            NetworkResult.Error(
                ErrorTypes.ServerError(schoolListResponse.code(), schoolListResponse.message())
            )
        }
    }
}