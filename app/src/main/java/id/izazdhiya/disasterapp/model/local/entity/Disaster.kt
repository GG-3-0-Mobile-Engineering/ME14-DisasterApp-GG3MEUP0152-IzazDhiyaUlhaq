package id.izazdhiya.disasterapp.model.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "disaster")
data class Disaster(

    @PrimaryKey
    @ColumnInfo(name = "pKey")
    val pKey: String,

    @ColumnInfo(name = "disasterType")
    val disasterType: String,

    @ColumnInfo(name = "imageUrl")
    val imageUrl: String? = null,

    @ColumnInfo(name = "loc")
    val loc: String? = null,

    @ColumnInfo(name = "latitude")
    val latitude: Double,

    @ColumnInfo(name = "longitude")
    val longitude: Double,

    @ColumnInfo(name = "date")
    val date: String,

    @ColumnInfo(name = "status")
    val status: String

)