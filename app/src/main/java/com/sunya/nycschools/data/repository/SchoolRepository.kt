package com.sunya.nycschools.data.repository

import com.sunya.nycschools.data.model.School
import com.sunya.nycschools.data.remote.common.NetworkResult

interface SchoolRepository {

    suspend fun getSchoolList(): NetworkResult<List<School>>

}