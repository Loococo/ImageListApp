package app.loococo.domain.model

data class StoreData(
    val title: String,
    val stores: List<StoreItem>
)

data class StoreItem(
    val code: String,
    val thumbnailUrl: String,
    val iconImageUrl: String,
    val name: String,
    val selected: Boolean = false
)