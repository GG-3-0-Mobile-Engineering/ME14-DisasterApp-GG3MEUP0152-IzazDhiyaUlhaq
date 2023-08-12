package id.izazdhiya.disasterapp.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import id.izazdhiya.disasterapp.data.source.local.dao.DisasterDao
import id.izazdhiya.disasterapp.data.source.local.entity.Disaster

@Database(entities = [Disaster::class], version = 1, exportSchema = false)
abstract class DisasterDatabase : RoomDatabase() {
    abstract fun disasterDao(): DisasterDao
}