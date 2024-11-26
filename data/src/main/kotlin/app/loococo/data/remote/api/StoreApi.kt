package app.loococo.data.remote.api

import app.loococo.data.model.StoreResponse
import retrofit2.Response
import retrofit2.http.GET

interface StoreApi {
    @GET("/stores")
    suspend fun stores(): Response<StoreResponse>
}