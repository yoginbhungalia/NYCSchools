package com.sunya.nycschools.di

import com.sunya.nycschools.data.repository.SchoolRepository
import com.sunya.nycschools.data.repository.SchoolRepositoryImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface ApplicationLevelBindingModule {

    @Binds
    fun bindsSchoolRepository(schoolRepositoryImp: SchoolRepositoryImp): SchoolRepository

}