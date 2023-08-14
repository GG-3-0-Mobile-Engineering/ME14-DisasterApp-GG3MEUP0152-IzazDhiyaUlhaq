package id.izazdhiya.disasterapp.networktest.responsetest

import id.izazdhiya.disasterapp.data.source.network.response.DisasterReport
import id.izazdhiya.disasterapp.data.source.network.response.Feature
import id.izazdhiya.disasterapp.data.source.network.response.FireLocation
import id.izazdhiya.disasterapp.data.source.network.response.FireRadius
import id.izazdhiya.disasterapp.data.source.network.response.Geometry
import id.izazdhiya.disasterapp.data.source.network.response.PersonLocation
import id.izazdhiya.disasterapp.data.source.network.response.Properties
import id.izazdhiya.disasterapp.data.source.network.response.ReportData
import id.izazdhiya.disasterapp.data.source.network.response.Result
import id.izazdhiya.disasterapp.data.source.network.response.Tags
import junit.framework.Assert.assertEquals
import org.junit.Test

class DisasterReportTest {

    @Test
    fun `test DisasterReport properties`() {
        val result = createMockDisasterReports()
        val statusCode = 200

        val disasterReport = DisasterReport(result, statusCode)

        assertEquals(result, disasterReport.result)
        assertEquals(statusCode, disasterReport.statusCode)
    }

    private fun createMockDisasterReports(): Result {
        val mockReportData = ReportData(
            reportType = "flood",
            accessabilityFailure = 1,
            airQuality = 1,
            condition = 1,
            evacuationArea = true,
            evacuationNumber = 1,
            fireDistance = 1.1,
            fireLocation = FireLocation(108.5419283181, -6.7016487411),
            fireRadius = FireRadius(108.5419283181, -6.7016487411),
            floodDepth = 81,
            impact = 1,
            personLocation = PersonLocation(108.5419283181, -6.7016487411),
            visibility = 1,
            volcanicSigns = listOf(1, 2)
        )
        val mockTags = Tags(
            districtId = 1,
            localAreaId = "1",
            instanceRegionCode = "ID-JB"
        )
        val mockGeometry = Geometry(
            coordinates = listOf(108.5419283181, -6.7016487411),
            type = "Point"
        )
        val mockProperties = Properties(
            createdAt = "2023-08-12T13:08:00.620Z",
            disasterType = "flood",
            imageUrl = "https://images.petabencana.id/673c34fb-22c9-440f-a6b5-711959b09ddf.jpg",
            partnerCode = 0,
            partnerIcon = 0,
            pkey = "322042",
            reportData = mockReportData,
            source = "grasp",
            status = "confirmed",
            tags = mockTags,
            text = "Trainer Mulya Dewi_Muhamad Rizki Maulana",
            title = 0,
            url = "673c34fb-22c9-440f-a6b5-711959b09ddf",
        )
        val mockFeature = listOf(
            Feature(
                geometry = mockGeometry,
                properties = mockProperties,
                type = "Feature"
            )
        )

        return Result(
            features = mockFeature,
            type = "FeatureCollection"
        )
    }
}





