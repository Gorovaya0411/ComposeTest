package com.example.composetest.di.modules

import com.example.composetest.data.remote.*
import com.example.composetest.data.repositories.ListImagesRepository
import com.example.composetest.data.remote.retrofit.ListImagesRetrofitDataSource
import com.example.composetest.domain.usecase.LoadListImageUseCase
import dagger.Module
import dagger.Provides
import retrofit2.*
import javax.inject.Singleton

@Module
class ListImagesModule {

    @Singleton
    @Provides
    fun provideLoadListImageUseCase(listImagesRepository: ListImagesRepository): LoadListImageUseCase =
        LoadListImageUseCase(listImagesRepository)


    @Singleton
    @Provides
    fun provideListImagesRepository(
        listImageRetrofitService: ListImagesRetrofitDataSource
    ) = ListImagesRepository(listImageRetrofitService)


    @Singleton
    @Provides
    fun provideUserListsImagesRetrofitDataSource(listImagesApi: ListImagesApi) =
        ListImagesRetrofitDataSource(listImagesApi)

    @Singleton
    @Provides
    fun provideRestService(retrofit: Retrofit): RestService =
        retrofit.create(RestService::class.java)


    @Singleton
    @Provides
    fun providePlaylistApi(restService: RestService) = ListImagesApi(restService)
}