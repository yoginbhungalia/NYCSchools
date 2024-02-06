package com.sunya.nycschools.ui.schools.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.sunya.nycschools.ui.common.NavigationPaths
import com.sunya.nycschools.ui.schools.state.SchoolListState
import com.sunya.nycschools.ui.theme.NYCSchoolsTheme

@Composable
fun SchoolList(schoolListState: SchoolListState, navController: NavController, modifier: Modifier = Modifier) {
    NYCSchoolsTheme {
        Box (
            modifier = modifier.fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {

            when (schoolListState) {
                is SchoolListState.Success -> {
                    SchoolLazyList(schoolListState.schoolList) {
                        navController.navigate("${NavigationPaths.SCHOOL_DETAIL_WITHOUT_ARG}${it.overviewParagraph}")
                    }
                }
                is SchoolListState.Loading -> {
                    CircularLoader(modifier)
                }
                is SchoolListState.Error -> {
                    ErrorMessage(schoolListState.localisedErrorMessage, modifier)
                }
            }
        }
    }
}