package id.izazdhiya.disasterapp

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import id.izazdhiya.disasterapp.model.network.response.flood.Flood
import id.izazdhiya.disasterapp.model.network.response.flood.Geometry
import id.izazdhiya.disasterapp.service.ApiClient
import id.izazdhiya.disasterapp.service.ApiService
import id.izazdhiya.disasterapp.utils.NotificationUtil
import kotlinx.coroutines.runBlocking

class NotificationWorker(appContext: Context, workerParams: WorkerParameters) :
    Worker(appContext, workerParams) {

    private val apiService: ApiService by lazy { ApiClient.instance }

    override fun doWork(): Result {
        runBlocking {
            val apiData = fetchDataFromApi()
            val geometriesWithHighestState = getGeometryWithHighestState(apiData)

            if (isConditionMet(apiData)) {
                val area = geometriesWithHighestState?.properties?.parentName
                val city = geometriesWithHighestState?.properties?.cityName
                val message = when (geometriesWithHighestState?.properties?.state) {
                    1 -> "tidak diketahui"
                    2 -> "antara 10 hingga 70 cm"
                    3 -> "antara 71 hingga 150 cm"
                    4 -> "lebih dari 150 cm"
                    else -> "status tidak valid"
                }

                val title = "Waspada Banjir!"
                val content = "Terjadi banjir di daerah $area, $city. Ketinggian air $message. Hati-Hati!"
                NotificationUtil.createNotification(applicationContext, title, content)
            }
        }

        return Result.success()
    }


    private suspend fun fetchDataFromApi(): Flood {
        return apiService.getFloods("geojson", "ID-JK", 1)
    }

    private fun isConditionMet(apiData: Flood): Boolean {
        return apiData.result.objects.output.geometries.isNotEmpty()
    }

    private fun getGeometryWithHighestState(apiData: Flood): Geometry? {
        val objects = apiData.result.objects
        return if (isConditionMet(apiData)) {
            val output = objects.output
            val geometries = output.geometries
            geometries.maxByOrNull { it.properties.state }
        } else {
            null
        }
    }



}

