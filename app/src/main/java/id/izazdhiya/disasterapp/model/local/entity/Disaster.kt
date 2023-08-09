//package id.izazdhiya.disasterapp.model.local.entity
//
//import androidx.room.Embedded
//import androidx.room.Entity
//import androidx.room.PrimaryKey
//import id.izazdhiya.disasterapp.model.network.response.Geometry
//import id.izazdhiya.disasterapp.model.network.response.Properties
//
//@Entity(tableName = "disaster")
//data class Disaster(
//    @PrimaryKey(autoGenerate = true) val id: Long,
//    @Embedded val geometry: Geometry,
//    @Embedded val properties: Properties,
//    val type: String
//)