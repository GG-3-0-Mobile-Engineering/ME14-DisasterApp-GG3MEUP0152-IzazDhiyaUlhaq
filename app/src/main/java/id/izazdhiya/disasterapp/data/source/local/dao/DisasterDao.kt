package id.izazdhiya.disasterapp.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import id.izazdhiya.disasterapp.data.source.local.entity.Disaster
import kotlinx.coroutines.flow.Flow

@Dao
interface DisasterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDisaster(disaster: List<Disaster>)

    @Query("SELECT * FROM disaster")
    fun getDisaster(): Flow<List<Disaster>>

    @Query("SELECT * FROM disaster WHERE disasterType = :type")
    suspend fun getDisasterByType(type: String): Disaster

    @Query("DELETE FROM disaster")
    suspend fun deleteDisaster()

}