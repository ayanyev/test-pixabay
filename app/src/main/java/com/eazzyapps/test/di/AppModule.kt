package com.eazzyapps.test.di

import com.ezzyapps.test.pixabay.common.ActivityDelegate
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    companion object {

        @Provides
        @Singleton
        fun provideActivityDelegate(): ActivityDelegate = ActivityDelegate()

    }

}