package com.nadin.city_locator.di

import android.content.Context
import com.nadin.city_locator.data.datasource.JsonCity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideJsonCity(@ApplicationContext context: Context):JsonCity{
        return JsonCity(context, "cities.json")
    }
}