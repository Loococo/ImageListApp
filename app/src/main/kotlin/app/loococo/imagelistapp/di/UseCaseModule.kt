package app.loococo.imagelistapp.di

import app.loococo.domain.repository.StoreRepository
import app.loococo.domain.usecase.StoreUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideStoreUseCase(repository: StoreRepository): StoreUseCase = StoreUseCase(repository)

}
