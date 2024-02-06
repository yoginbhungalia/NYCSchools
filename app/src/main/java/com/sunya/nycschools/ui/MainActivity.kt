package com.sunya.nycschools.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.sunya.nycschools.ui.common.NavigationPaths
import com.sunya.nycschools.ui.common.SchoolNavigationArgs
import com.sunya.nycschools.ui.schools.composables.SchoolDetail
import com.sunya.nycschools.ui.schools.composables.SchoolList
import com.sunya.nycschools.ui.schools.state.SchoolViewModel
import com.sunya.nycschools.ui.theme.NYCSchoolsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val schoolViewModel: SchoolViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        schoolViewModel.loadSchoolList()

        setContent {
            NYCSchoolsTheme {
                val navController = rememberNavController()

                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    NavHost(
                        navController,
                        startDestination = NavigationPaths.HOME
                    ) {
                        composable(NavigationPaths.HOME) {
                            SchoolList(schoolViewModel.schoolListState, navController)
                        }
                        composable(
                            NavigationPaths.SCHOOL_DETAIL,
                            arguments = listOf(navArgument(SchoolNavigationArgs.OVERVIEW) {
                                type = NavType.StringType
                            })
                        ) { backStackEntry ->
                            SchoolDetail(backStackEntry.arguments?.getString(SchoolNavigationArgs.OVERVIEW))
                        }
                    }
                }
            }
        }
    }
}