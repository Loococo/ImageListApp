package app.loococo.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import app.loococo.data.local.model.StoreEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface StoreDao {
    @Insert
    suspend fun insert(storeEntity: StoreEntity)

    @Query("SELECT * FROM store ORDER BY name ASC")
    fun getStores(): Flow<List<StoreEntity>>

    @Query("DELETE FROM store WHERE id = :id")
    suspend fun deleteById(id: Long)
}