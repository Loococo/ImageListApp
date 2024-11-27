package app.loococo.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import app.loococo.domain.model.StoreItem

@Entity(tableName = "store")
data class StoreEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0L,
    val code: String,
    val thumbnailUrl: String,
    val iconImageUrl: String,
    val name: String,
)

fun StoreEntity.toStoreItem(): StoreItem {
    return StoreItem(
        id = id,
        code = code,
        thumbnailUrl = thumbnailUrl,
        iconImageUrl = iconImageUrl,
        name = name,
        selected = true
    )
}

fun StoreItem.toStoreEntity(): StoreEntity {
    return StoreEntity(
        code = code,
        thumbnailUrl = thumbnailUrl,
        iconImageUrl = iconImageUrl,
        name = name
    )
}