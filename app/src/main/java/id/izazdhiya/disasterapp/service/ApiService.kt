package id.izazdhiya.disasterapp.service

import id.izazdhiya.disasterapp.model.network.response.DisasterReport
import id.izazdhiya.disasterapp.model.network.response.Result
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("reports")
    suspend fun getReports(
        @Query("geoformat") geoFormat: String
    ): DisasterReport

    @GET("reports")
    suspend fun getReportsByProvince(
        @Query("geoformat") geoFormat: String,
        @Query("admin") provinceId: String
    ): DisasterReport

    @GET("reports")
    suspend fun getReportsByDisaster(
        @Query("geoformat") geoFormat: String,
        @Query("disaster") disasterType: String
    ): DisasterReport

    @GET("reports/archive")
    suspend fun getArchive(
        @Query("geoformat") geoFormat: String,
        @Query("start") startDate: String,
        @Query("end") endDate: String,
    ): DisasterReport

    @GET("reports/archive")
    suspend fun getArchiveByProvince(
        @Query("geoformat") geoFormat: String,
        @Query("start") startDate: String,
        @Query("end") endDate: String,
        @Query("admin") provinceId: String
    ): DisasterReport
}