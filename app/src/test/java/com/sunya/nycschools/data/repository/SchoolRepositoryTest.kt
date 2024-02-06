package com.sunya.nycschools.data.repository

import com.google.common.truth.Truth
import com.sunya.nycschools.common.ApiHelper
import com.sunya.nycschools.data.remote.ApiService
import com.sunya.nycschools.data.remote.common.ErrorTypes
import com.sunya.nycschools.data.remote.common.NetworkResult
import kotlinx.coroutines.test.runTest
import okhttp3.ResponseBody
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.Response

class SchoolRepositoryTest {

    @Mock
    lateinit var apiService: ApiService

    private lateinit var schoolRepository: SchoolRepository

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        schoolRepository = SchoolRepositoryImp(apiService)
    }

    @Test
    fun `getSchoolList return list of schools`() = runTest {
        Mockito.`when`(apiService.getSchoolList()).thenReturn(
            Response.success(
                200,
                    ApiHelper.listOfNetworkSchools()
            )
        )
        val schoolList = schoolRepository.getSchoolList()
        Truth.assertThat(schoolList).isInstanceOf(NetworkResult.Success::class.java)
        Truth.assertThat((schoolList as NetworkResult.Success).data.size).isGreaterThan(0)
    }

    @Test
    fun `getSchoolList returns empty data error`() = runTest {
        Mockito.`when`(apiService.getSchoolList()).thenReturn(
            Response.success(
                200,
                listOf()
            )
        )
        val schoolList = schoolRepository.getSchoolList()
        Truth.assertThat(schoolList).isInstanceOf(NetworkResult.Error::class.java)
        Truth.assertThat((schoolList as NetworkResult.Error).errorTypes).isEqualTo(ErrorTypes.EmptyDataError)
    }

    @Test
    fun `getSchoolList returns server error`() = runTest {
        Mockito.`when`(apiService.getSchoolList()).thenReturn(
            Response.error(
                500,
                "".toResponseBody()
            )
        )
        val schoolList = schoolRepository.getSchoolList()
        Truth.assertThat(schoolList).isInstanceOf(NetworkResult.Error::class.java)
    }

}