package app.loococo.domain.model


sealed class Resource<out T> {
    data class Success<T>(val data: T) : Resource<T>()
    data class Error(val error: String) : Resource<Nothing>()
    data object Loading : Resource<Nothing>()
}