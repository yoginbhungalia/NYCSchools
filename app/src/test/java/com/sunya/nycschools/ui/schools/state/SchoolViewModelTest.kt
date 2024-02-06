package com.sunya.nycschools.ui.schools.state

import android.text.style.TabStopSpan.Standard
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth
import com.sunya.nycschools.common.ApiHelper
import com.sunya.nycschools.data.remote.common.NetworkResult
import com.sunya.nycschools.data.repository.SchoolRepository
import com.sunya.nycschools.domain.LocalisedErrorTypeUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import java.util.concurrent.CountDownLatch

class SchoolViewModelTest {

    @get:Rule
    val instantTaskExecutorRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var testDispatcher: TestDispatcher

    @Mock
    lateinit var schoolRepository: SchoolRepository

    private lateinit var schoolViewModel: SchoolViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        schoolViewModel = SchoolViewModel(
            schoolRepository,
            LocalisedErrorTypeUseCase()
        )
        testDispatcher = StandardTestDispatcher()
    }

    @Test
    fun `loadSchoolList load list of schools in SchoolListState`() = runTest {
        Mockito.`when`(
            schoolRepository.getSchoolList()
        ).thenReturn(
            NetworkResult.Success(
                ApiHelper.listOfNetworkSchools().map {
                    it.toSchool()
                }
            )
        )
        schoolViewModel.loadSchoolList(testDispatcher)
        testDispatcher.scheduler.advanceUntilIdle()
        Truth.assertThat(schoolViewModel.schoolListState).isInstanceOf(SchoolListState.Success::class.java)
        Truth.assertThat((schoolViewModel.schoolListState as SchoolListState.Success).schoolList.size).isGreaterThan(0)
    }

}