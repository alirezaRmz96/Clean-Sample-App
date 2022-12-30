package com.example.cleansample.di

import android.content.Context
import androidx.room.Room
import com.example.cleansample.data.repository.local.db.AppDao
import com.example.cleansample.data.repository.local.db.AppDataBase
import com.example.cleansample.data.repository.UserRepositoryImpl
import com.example.cleansample.data.repository.local.UserLocalDataSource
import com.example.cleansample.data.repository.local.UserLocalDataSourceImpl
import com.example.cleansample.data.repository.remote.UserRemoteDataSource
import com.example.cleansample.data.repository.remote.UserRemoteDataSourceImpl
import com.example.cleansample.data.repository.remote.service.Service
import com.example.cleansample.domain.UserRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class UserDependency {
    @Binds
    abstract fun bindsUserRepository(impl: UserRepositoryImpl):UserRepository

    @Binds
    abstract fun bindsUserRemoteDataSource(impl: UserRemoteDataSourceImpl):UserRemoteDataSource

    @Binds
    abstract fun bindUserLocalDataSource(impl: UserLocalDataSourceImpl):UserLocalDataSource

    companion object{
        @Provides
        @Singleton
        fun provideRetrofit():Retrofit{
            return Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        @Provides
        fun provideService(retrofit: Retrofit): Service {
            return retrofit.create(Service::class.java)
        }

        @Provides
        @Singleton
        fun provideRoom(
            @ApplicationContext context: Context
        ): AppDataBase {
            return Room.databaseBuilder(
                context,
                AppDataBase::class.java,
                "sample_app",
            ).build()
        }

        @Provides
        fun provideAppDao(appDataBase: AppDataBase): AppDao {
            return appDataBase.appDao()
        }
    }
}