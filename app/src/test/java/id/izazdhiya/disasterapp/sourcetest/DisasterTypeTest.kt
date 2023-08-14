package id.izazdhiya.disasterapp.sourcetest

import id.izazdhiya.disasterapp.data.source.DisasterType
import org.junit.Test
import org.junit.Assert.assertEquals

class DisasterTypeTest {

    @Test
    fun `test DisasterType properties`() {
        val id = "flood"
        val name = "Flood"

        val disasterType = DisasterType(id, name)

        assertEquals(id, disasterType.id)
        assertEquals(name, disasterType.name)
    }

    @Test
    fun `test DisasterType equality`() {
        val type1 = DisasterType("flood", "Flood")
        val type2 = DisasterType("flood", "Flood")
        val type3 = DisasterType("earthquake", "Earthquake")

        assertEquals(type1, type2)
        assertEquals(type2, type1)
        assertEquals(type1.hashCode(), type2.hashCode())
        assertEquals(type1.toString(), type2.toString())

        assert(type1 != type3)
        assert(type2 != type3)
    }
}
