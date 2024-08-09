package com.nadin.city_locator.di

import com.nadin.city_locator.data.repository.CityRepoImpl
import com.nadin.city_locator.domain.repository.CityRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepoModule{

    @Binds
    @Singleton
    abstract fun bindCityRepo(cityRepoImpl: CityRepoImpl):CityRepo
}