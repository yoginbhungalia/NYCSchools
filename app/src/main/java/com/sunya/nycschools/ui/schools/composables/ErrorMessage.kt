package com.sunya.nycschools.ui.schools.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource

@Composable
fun ErrorMessage(localisedErrorMessage: Int, modifier: Modifier = Modifier) {
    Box (
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    )
    {
        Text(text = stringResource(id = localisedErrorMessage))
    }
}