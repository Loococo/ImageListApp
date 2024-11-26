package app.loococo.data.remote.manger

import app.loococo.data.model.suspendResponseResult
import app.loococo.data.model.toStoreData
import app.loococo.data.remote.api.StoreApi
import app.loococo.domain.model.Resource
import app.loococo.domain.model.StoreData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class StoreDataManger @Inject constructor(private val storeApi: StoreApi) {

    suspend fun stores(): Flow<Resource<StoreData>> =
        suspendResponseResult(
            execute = { storeApi.stores() },
            mapResponse = { it.toStoreData() }
        )
}