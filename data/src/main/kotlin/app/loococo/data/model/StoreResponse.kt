package app.loococo.data.model

import app.loococo.domain.model.StoreData
import app.loococo.domain.model.StoreItem

data class StoreResponse(
    val title: String?,
    val list: List<StoreItemResponse>?
)

data class StoreItemResponse(
    val code: String?,
    val thumbnailUrl: String?,
    val iconImageUrl: String?,
    val name: String?,
)

fun StoreResponse.toStoreData(): StoreData {
    return StoreData(
        title = title ?: "",
        stores = list?.map { it.toStoreItem() } ?: emptyList()
    )
}

fun StoreItemResponse.toStoreItem(): StoreItem {
    return StoreItem(
        code = code ?: "",
        thumbnailUrl = thumbnailUrl ?: "",
        iconImageUrl = iconImageUrl ?: "",
        name = name ?: "",
    )
}
