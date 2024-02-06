package com.sunya.nycschools.data.remote

import com.google.common.truth.Truth
import com.sunya.nycschools.common.ApiHelper
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiServiceTest {

    private lateinit var mockWebServer: MockWebServer
    private lateinit var apiService: ApiService

    @Before
    fun setUp() {
        mockWebServer = MockWebServer()
        apiService = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `getSchoolList returns list of school`() = runTest {
        mockWebServer.enqueue(
            MockResponse().setResponseCode(200).setBody(
                ApiHelper.readJsonAsString("/schools.json")
            )
        )
        val response = apiService.getSchoolList()
        mockWebServer.takeRequest()
        Truth.assertThat(response.code()).isEqualTo(200)
        Truth.assertThat(response.body()!!.size).isGreaterThan(0)
    }

}