package com.satya.subm.submission.di

import android.app.Application
import com.satya.subm.submission.data.remote.MovieAPI
import com.satya.subm.submission.data.remote.tvshow.TVShowAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(MovieAPI.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    fun provideRetrofitTVShow(): Retrofit =
        Retrofit.Builder()
            .baseUrl(TVShowAPI.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideMovieAPI (retrofit: Retrofit) : MovieAPI =
        retrofit.create(MovieAPI::class.java)


    @Provides
    @Singleton
    fun provideTVShowAPI (retrofit: Retrofit) : TVShowAPI =
        retrofit.create(TVShowAPI::class.java)
}