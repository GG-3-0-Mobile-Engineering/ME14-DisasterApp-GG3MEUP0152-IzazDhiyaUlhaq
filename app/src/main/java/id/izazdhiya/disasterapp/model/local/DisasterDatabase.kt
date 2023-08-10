package id.izazdhiya.disasterapp.model.local

import androidx.room.Database
import androidx.room.RoomDatabase
import id.izazdhiya.disasterapp.model.local.dao.DisasterDao
import id.izazdhiya.disasterapp.model.local.entity.Disaster

@Database(entities = [Disaster::class], version = 1, exportSchema = false)
abstract class DisasterDatabase : RoomDatabase() {
    abstract fun disasterDao(): DisasterDao
}