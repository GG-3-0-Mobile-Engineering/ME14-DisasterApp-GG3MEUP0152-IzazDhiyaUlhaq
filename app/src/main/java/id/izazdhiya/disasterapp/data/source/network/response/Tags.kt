package id.izazdhiya.disasterapp.data.source.network.response


import com.google.gson.annotations.SerializedName

data class Tags(
    @SerializedName("district_id")
    val districtId: Any,
    @SerializedName("instance_region_code")
    val instanceRegionCode: String,
    @SerializedName("local_area_id")
    val localAreaId: String
)