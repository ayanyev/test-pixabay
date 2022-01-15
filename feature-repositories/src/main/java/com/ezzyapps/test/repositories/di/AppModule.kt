package com.ezzyapps.test.repositories.di

import android.content.Context
import androidx.room.Room
import com.ezzyapps.test.repositories.data.PhotoRepositoryImpl
import com.ezzyapps.test.repositories.data.local.HitsDatabase
import com.ezzyapps.test.repositories.data.remote.HitsClient
import com.ezzyapps.test.repositories.data.remote.RemoteClient
import com.ezzyapps.test.repositories.domain.PhotoRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    companion object {

        @Provides
        @Singleton
        fun provideRemoteClient(): HitsClient =
            RemoteClient.create(HitsClient::class.java)

        @Provides
        @Singleton
        fun provideLocalDatabase(@ApplicationContext context: Context): HitsDatabase =
            Room.databaseBuilder(context, HitsDatabase::class.java, "photoDB")
                .fallbackToDestructiveMigration()
                .build()
                .apply { Thread { clearAllTables() }.start() } // clear db at startup
    }

    @Binds
    abstract fun bindRepository(
        repository: PhotoRepositoryImpl
    ): PhotoRepository

}