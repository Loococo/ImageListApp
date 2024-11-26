package app.loococo.imagelistapp.di

import app.loococo.data.remote.api.StoreApi
import app.loococo.data.remote.manger.StoreDataManger
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteDatasourceModule {
    @Provides
    @Singleton
    fun provideStoreDataManger(api: StoreApi): StoreDataManger = StoreDataManger(api)
}