package id.izazdhiya.disasterapp.networktest.responsetest

import id.izazdhiya.disasterapp.data.source.network.response.flood.Flood
import id.izazdhiya.disasterapp.data.source.network.response.flood.Objects
import id.izazdhiya.disasterapp.data.source.network.response.flood.Output
import id.izazdhiya.disasterapp.data.source.network.response.flood.Transform
import junit.framework.Assert.assertEquals
import org.junit.Test

class FloodTest {

    @Test
    fun `test Flood properties`() {
        val result = createMockFlood()
        val statusCode = 200

        val flood = Flood(result, statusCode)

        assertEquals(result, flood.result)
        assertEquals(statusCode, flood.statusCode)
    }

    private fun createMockFlood(): id.izazdhiya.disasterapp.data.source.network.response.flood.Result {
        val mockProperties = id.izazdhiya.disasterapp.data.source.network.response.flood.Properties(
            areaId = "",
            areaName = "",
            cityName = "",
            geomId = "",
            lastUpdated = "",
            parentName = "",
            state = 0
        )
        val mockGeometry = id.izazdhiya.disasterapp.data.source.network.response.flood.Geometry(
            arcs = listOf(listOf(0)),
            properties = mockProperties,
            type = ""
        )
        val mockOutput = Output(
            geometries = listOf(mockGeometry),
            type = ""
        )
        val mockObject = Objects(
            output = mockOutput
        )
        val mockTransform = Transform(
            scale = listOf(0.1),
            translate = listOf(0.1)
        )
        return id.izazdhiya.disasterapp.data.source.network.response.flood.Result(
            arcs = listOf(listOf(listOf(1,2,3))),
            bbox = listOf(0.1),
            objects = mockObject,
            transform = mockTransform,
            type = ""
        )
    }
}