package app.loococo.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import app.loococo.data.local.dao.StoreDao
import app.loococo.data.local.model.StoreEntity

@Database(entities = [StoreEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun storeDao(): StoreDao
}