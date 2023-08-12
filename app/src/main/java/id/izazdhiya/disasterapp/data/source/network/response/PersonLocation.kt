package id.izazdhiya.disasterapp.data.source.network.response


import com.google.gson.annotations.SerializedName

data class PersonLocation(
    @SerializedName("lat")
    val lat: Double,
    @SerializedName("lng")
    val lng: Double
)