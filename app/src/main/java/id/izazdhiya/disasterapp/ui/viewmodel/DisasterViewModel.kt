package id.izazdhiya.disasterapp.ui.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import dagger.hilt.android.lifecycle.HiltViewModel
import id.izazdhiya.disasterapp.data.source.local.dao.DisasterDao
import id.izazdhiya.disasterapp.data.source.network.Resource
import id.izazdhiya.disasterapp.domain.repository.DisasterRepository
import kotlinx.coroutines.Dispatchers
import java.net.URLEncoder
import java.nio.charset.StandardCharsets
import javax.inject.Inject
import kotlin.Exception

@HiltViewModel
class DisasterViewModel @Inject constructor(private val repository: DisasterRepository, private val disasterDao: DisasterDao) : ViewModel(){

//    fun getReports(): LiveData<List<Disaster>> = disasterDao.getDisaster()
//    fun getReportsByProvince(provinceId: String): LiveData<List<Disaster>> = disasterDao.getDisasterByType(provinceId)

//    fun getReports() = repository.getReports().asLiveData()
//    fun getReportsByProvince(provinceId: String) = repository.getReportsByProvince(provinceId).asLiveData()
//    fun getArchive(start: String, end: String) = repository.getArchive(start, end).asLiveData()




    fun getReports() = liveData(Dispatchers.IO) {
        emit(Resource.loading(null))
        try {
            emit(Resource.success(repository.getReports("geojson")))
        } catch (e: Exception) {
            emit(Resource.error(data = null, message = e.message ?: "Error Occurred!"))
        }
    }

    fun getReportsByProvince(provinceId: String) = liveData(Dispatchers.IO) {
        emit(Resource.loading(null))
        try {
            emit(Resource.success(repository.getReportsByProvince("geojson", provinceId)))
        } catch (e: Exception) {
            emit(Resource.error(data = null, message = e.message ?: "Error Occurred!"))
        }
    }

//    fun getReportsByDisaster(disaster: String) = liveData(Dispatchers.IO) {
//        emit(Resource.loading(null))
//        try {
//            emit(Resource.success(repository.getReportsByDisaster("geojson", disaster)))
//        } catch (e: Exception) {
//            emit(Resource.error(data = null, message = e.message ?: "Error Occurred!"))
//        }
//    }

    fun getArchive(startTime: String, endTime: String) = liveData(Dispatchers.IO) {
        val encodedStartTime = URLEncoder.encode(startTime, StandardCharsets.UTF_8.toString())
        val encodedEndTime = URLEncoder.encode(endTime, StandardCharsets.UTF_8.toString())
        emit(Resource.loading(null))
        try {
            emit(Resource.success(repository.getArchive("geojson", encodedStartTime, encodedEndTime)))
        } catch (e: Exception) {
            emit(Resource.error(data = null, message = e.message ?: "Error Occurred!"))
        }
    }

//    fun getArchiveByProvince(start: String, end: String, provinceId: String) = liveData(Dispatchers.IO) {
//        emit(Resource.loading(null))
//        try {
//            emit(Resource.success(repository.getArchiveByProvince("geojson", start, end, provinceId)))
//        } catch (e: Exception) {
//            emit(Resource.error(data = null, message = e.message ?: "Error Occurred!"))
//        }
//    }

//    fun getFloods() = liveData(Dispatchers.IO) {
//        emit(Resource.loading(null))
//        try {
//            emit(Resource.success(repository.getFloods("topojson", "ID-JK", 1)))
//        } catch (e: Exception) {
//            emit(Resource.error(data = null, message = e.message ?: "Error Occurred!"))
//        }
//    }

}