package app.loococo.imagelistapp.di

import app.loococo.data.remote.api.StoreApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
class ApiModule {

    @Provides
    fun providerAuthApi(retrofit: Retrofit): StoreApi = retrofit.create(StoreApi::class.java)

}
