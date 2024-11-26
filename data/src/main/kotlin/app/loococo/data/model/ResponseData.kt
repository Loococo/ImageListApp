package app.loococo.data.model

import app.loococo.domain.model.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import retrofit2.Response

suspend fun <T : Any, R : Any> suspendResponseResult(
    execute: suspend () -> Response<T>,
    mapResponse: (T) -> R = { it as R }
): Flow<Resource<R>> = flow {
    emit(Resource.Loading)

    try {
        val response = execute()
        when {
            response.isSuccessful -> {
                val body = response.body()
                if (body != null) {
                    emit(Resource.Success(mapResponse(body)))
                } else {
                    emit(Resource.Error("Response body is null"))
                }
            }
            else -> {
                emit(Resource.Error("API call failed with status code: ${response.code()}"))
            }
        }
    } catch (e: Exception) {
        emit(Resource.Error("Exception occurred: ${e.message}"))
    }
}.catch { e ->
    emit(Resource.Error("Unhandled exception: ${e.message}"))
}
