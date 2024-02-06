package com.sunya.nycschools.data.remote

import com.sunya.nycschools.data.remote.networkmodel.NetworkSchool
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("resource/s3k6-pzi2.json")
    suspend fun getSchoolList(): Response<List<NetworkSchool>>

}