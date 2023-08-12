package id.izazdhiya.disasterapp.data.source.network.response.flood


import com.google.gson.annotations.SerializedName

data class Geometry(
    @SerializedName("arcs")
    val arcs: List<List<Int>>,
    @SerializedName("properties")
    val properties: Properties,
    @SerializedName("type")
    val type: String
)