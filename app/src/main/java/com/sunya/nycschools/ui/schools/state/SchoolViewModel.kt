package com.sunya.nycschools.ui.schools.state

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sunya.nycschools.data.remote.common.NetworkResult
import com.sunya.nycschools.data.repository.SchoolRepository
import com.sunya.nycschools.domain.LocalisedErrorTypeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SchoolViewModel @Inject constructor(
    private val schoolRepository: SchoolRepository,
    private val localisedErrorTypeUseCase: LocalisedErrorTypeUseCase
): ViewModel() {

    var schoolListState by mutableStateOf<SchoolListState>(SchoolListState.Loading)
        private set

    fun loadSchoolList(coroutineDispatcher: CoroutineDispatcher = Dispatchers.IO) {
        viewModelScope.launch (coroutineDispatcher) {
            schoolListState = SchoolListState.Loading
            schoolListState = when (val result = schoolRepository.getSchoolList()) {
                is NetworkResult.Success -> {
                    SchoolListState.Success(result.data)
                }

                is NetworkResult.Error -> {
                    SchoolListState.Error(localisedErrorTypeUseCase(result.errorTypes))
                }
            }
        }
    }

}