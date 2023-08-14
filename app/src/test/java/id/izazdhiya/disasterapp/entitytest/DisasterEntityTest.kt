package id.izazdhiya.disasterapp.entitytest

import id.izazdhiya.disasterapp.data.source.local.entity.Disaster
import org.junit.Test
import org.junit.Assert.assertEquals

class DisasterEntityTest {

    @Test
    fun `test Disaster entity properties`() {
        val pKey = "123"
        val disasterType = "Flood"
        val imageUrl = "http://example.com/flood.jpg"
        val loc = "ID-JK"
        val latitude = 6.2088
        val longitude = 106.8456
        val date = "2023-08-14"
        val status = "Confirmed"

        val disaster = Disaster(pKey, disasterType, imageUrl, loc, latitude, longitude, date, status)

        assertEquals(pKey, disaster.pKey)
        assertEquals(disasterType, disaster.disasterType)
        assertEquals(imageUrl, disaster.imageUrl)
        assertEquals(loc, disaster.loc)
        assertEquals(latitude, disaster.latitude, 0.001)
        assertEquals(longitude, disaster.longitude, 0.001)
        assertEquals(date, disaster.date)
        assertEquals(status, disaster.status)
    }
}
