package com.sunya.nycschools.ui.schools.composables

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import com.sunya.nycschools.data.model.School


@Composable
fun SchoolLazyList(schoolList: List<School>, onSchoolItemTap: (School) -> Unit) {
    LazyColumn {
        items (schoolList.size) {position ->
            SchoolItem(schoolList[position], onSchoolItemTap)
        }
    }
}