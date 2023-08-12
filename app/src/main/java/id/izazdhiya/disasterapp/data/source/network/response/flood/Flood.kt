package id.izazdhiya.disasterapp.data.source.network.response.flood


import com.google.gson.annotations.SerializedName

data class Flood(
    @SerializedName("result")
    val result: Result,
    @SerializedName("statusCode")
    val statusCode: Int
)