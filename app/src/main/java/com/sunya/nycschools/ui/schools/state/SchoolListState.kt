package com.sunya.nycschools.ui.schools.state

import com.sunya.nycschools.data.model.School

sealed class SchoolListState {
    data object Loading: SchoolListState()
    data class Success(val schoolList: List<School>): SchoolListState()
    data class Error(val localisedErrorMessage: Int): SchoolListState()
}