//package id.izazdhiya.disasterapp.repository.local
//
//import android.util.Log
//import androidx.room.ColumnInfo
//import androidx.room.withTransaction
//import id.izazdhiya.disasterapp.model.local.DisasterDatabase
//import id.izazdhiya.disasterapp.model.local.entity.Disaster
//import id.izazdhiya.disasterapp.model.network.response.DisasterReport
//import id.izazdhiya.disasterapp.model.network.response.Feature
//import id.izazdhiya.disasterapp.service.ApiService
//import id.izazdhiya.disasterapp.utils.networkBoundResource
//import kotlinx.coroutines.delay
//import javax.inject.Inject
//
//class DisasterRepository @Inject constructor(
//    private val apiService: ApiService,
//    private val db: DisasterDatabase
//) {
//    private val disasterDao = db.disasterDao()
//
//    fun getReports() = networkBoundResource(
//        query = {
//            disasterDao.getDisaster()
//        },
//        fetch = {
//            delay(2000)
//            apiService.getReports("geojson")
//        },
//        saveFetchResult = { disaster ->
//            db.withTransaction {
//                disasterDao.deleteDisaster()
//                disasterDao.insertDisaster(disaster)
//            }
//
//        }
//    )
//
//    fun getReportsByProvince(provinceId: String) = networkBoundResource(
//        query = {
//            disasterDao.getDisaster()
//        },
//        fetch = {
//            delay(2000)
//            apiService.getReportsByProvince("geojson", provinceId)
//        },
//        saveFetchResult = { disaster ->
//            db.withTransaction {
//                disasterDao.deleteDisaster()
//                disasterDao.insertDisaster(disaster)
//            }
//
//        }
//    )
//
//    fun getArchive(start: String, end: String) = networkBoundResource(
//        query = {
//            disasterDao.getDisaster()
//        },
//        fetch = {
//            delay(2000)
//            apiService.getArchive("geojson", start, end)
//        },
//        saveFetchResult = { disaster ->
//            db.withTransaction {
//                disasterDao.deleteDisaster()
//                disasterDao.insertDisaster(disaster)
//            }
//
//        }
//    )
//
////    suspend fun getReports(geoFormat: String) {
////        try {
////            disasterDao.deleteDisaster()
////            val apiData = apiService.getReports(geoFormat)
////            val disasters = convertApiDataToDisasters(apiData)
////            disasterDao.insertDisaster(disasters)
////        } catch (e: Exception) {
////            Log.d("Repository", "getReports: ${e.message.toString()} ")
////        }
////    }
//
////    suspend fun getReportsByProvince(geoFormat: String, provinceId: String) {
////        try {
////            disasterDao.deleteDisaster()
////            val apiData = apiService.getReportsByProvince(geoFormat, provinceId)
////            val disasters = convertApiDataToDisasters(apiData)
////            disasterDao.insertDisaster(disasters)
////        } catch (e: Exception) {
////            Log.d("Repository", "getReportsByProvince: ${e.message.toString()} ")
////        }
////    }
//
////    suspend fun getArchive(geoFormat: String, start: String, end: String) {
////        try {
////            disasterDao.deleteDisaster()
////            val apiData = apiService.getArchive(geoFormat, start, end)
////            val disasters = convertApiDataToDisasters(apiData)
////            disasterDao.insertDisaster(disasters)
////        } catch (e: Exception) {
////            Log.d("Repository", "getArchive: ${e.message.toString()} ")
////        }
////    }
//
////    private fun convertApiDataToDisasters(apiData: DisasterReport): List<Disaster> {
////        val disaster = apiData.result?.features
////        return disaster?.map {
////            Disaster(
////                pKey = it.properties.pkey,
////                disasterType = it.properties.disasterType,
////                imageUrl = it.properties.imageUrl,
////                loc = it.properties.tags.instanceRegionCode,
////                latitude = it.geometry.coordinates[1],
////                longitude = it.geometry.coordinates[0],
////                date = it.properties.createdAt,
////                status = it.properties.status
////            )
////        }!!
////    }
//}