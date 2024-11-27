package app.loococo.imagelistapp.di

import app.loococo.data.repository.FavoriteRepositoryImpl
import app.loococo.data.repository.StoreRepositoryImpl
import app.loococo.domain.repository.FavoriteRepository
import app.loococo.domain.repository.StoreRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun provideStoreRepository(repository: StoreRepositoryImpl): StoreRepository

    @Binds
    fun provideFavoriteRepository(repository: FavoriteRepositoryImpl): FavoriteRepository
}