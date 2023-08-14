package id.izazdhiya.disasterapp.sourcetest

import id.izazdhiya.disasterapp.data.source.DisasterArea
import org.junit.Test
import org.junit.Assert.assertEquals

class DisasterAreaTest {

    @Test
    fun `test DisasterArea properties`() {
        val id = "123"
        val name = "Test Area"

        val disasterArea = DisasterArea(id, name)

        assertEquals(id, disasterArea.id)
        assertEquals(name, disasterArea.name)
    }

    @Test
    fun `test DisasterArea equality`() {
        val area1 = DisasterArea("123", "Area One")
        val area2 = DisasterArea("123", "Area One")
        val area3 = DisasterArea("456", "Area Two")

        assertEquals(area1, area2)
        assertEquals(area2, area1)
        assertEquals(area1.hashCode(), area2.hashCode())
        assertEquals(area1.toString(), area2.toString())

        assert(area1 != area3)
        assert(area2 != area3)
    }
}
