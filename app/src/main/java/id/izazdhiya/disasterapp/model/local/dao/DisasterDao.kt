//package id.izazdhiya.disasterapp.model.local.dao
//
//import androidx.room.Dao
//import androidx.room.Insert
//import androidx.room.OnConflictStrategy
//import androidx.room.Query
//import id.izazdhiya.disasterapp.model.local.entity.Disaster
//import kotlinx.coroutines.flow.Flow
//
//@Dao
//interface DisasterDao {
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertDisaster(product: List<Disaster>)
//
//    @Query("SELECT * FROM disaster")
//    fun getDisaster(): Flow<List<Disaster>>
//
//    @Query("SELECT * FROM disaster WHERE pkey = :disasterId")
//    suspend fun getDisasterById(disasterId: String): Disaster
//
//    @Query("DELETE FROM disaster")
//    suspend fun deleteDisaster()
//
//}