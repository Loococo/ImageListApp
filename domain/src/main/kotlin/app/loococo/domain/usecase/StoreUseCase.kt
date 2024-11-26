package app.loococo.domain.usecase

import app.loococo.domain.model.Resource
import app.loococo.domain.model.StoreData
import app.loococo.domain.repository.StoreRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class StoreUseCase @Inject constructor(private val storeRepository: StoreRepository) {

    suspend operator fun invoke(): Flow<Resource<StoreData>> {
       return storeRepository.stores()
    }
}