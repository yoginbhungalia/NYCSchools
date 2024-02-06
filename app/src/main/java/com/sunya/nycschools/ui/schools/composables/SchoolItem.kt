package com.sunya.nycschools.ui.schools.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sunya.nycschools.data.model.School

@Composable
fun SchoolItem(
    school: School,
    onSchoolItemTap: (School) -> Unit
) {
    Surface (
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable {
                onSchoolItemTap(school)
            }
    ) {
        Text(text = "${school.dbn}: ${school.schoolName}")
    }
}