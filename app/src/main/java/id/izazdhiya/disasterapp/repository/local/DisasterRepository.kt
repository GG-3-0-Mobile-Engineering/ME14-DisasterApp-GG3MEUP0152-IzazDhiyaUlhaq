//package id.izazdhiya.disasterapp.repository.local
//
//import androidx.room.withTransaction
//import id.izazdhiya.disasterapp.model.local.DisasterDatabase
//import id.izazdhiya.disasterapp.service.ApiService
//import kotlinx.coroutines.delay
//import javax.inject.Inject
//
//class DisasterRepository @Inject constructor(
//    private val api: ApiService,
//    private val db: DisasterDatabase
//) {
//    private val disasterDao = db.disasterDao()
//
//    fun getDisaster() = networkBoundResource(
//        query = {
//            disasterDao.getDisaster()
//        },
//        fetch = {
//            delay(2000)
//            api.getReports("geojson", 3423)
//        },
//        saveFetchResult = { disaster ->
//            db.withTransaction {
//                disasterDao.deleteDisaster()
//                disasterDao.insertDisaster(disaster)
//            }
//
//        }
//    )
//}