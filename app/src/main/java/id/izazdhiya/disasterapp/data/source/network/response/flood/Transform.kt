package id.izazdhiya.disasterapp.data.source.network.response.flood


import com.google.gson.annotations.SerializedName

data class Transform(
    @SerializedName("scale")
    val scale: List<Double>,
    @SerializedName("translate")
    val translate: List<Double>
)