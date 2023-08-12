package id.izazdhiya.disasterapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import id.izazdhiya.disasterapp.data.source.network.Resource
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
import id.izazdhiya.disasterapp.domain.repository.DisasterRepository
import id.izazdhiya.disasterapp.ui.viewmodel.DisasterViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class DisasterViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: DisasterRepository

    private lateinit var viewModel: DisasterViewModel
    private val testDispatcher = TestCoroutineDispatcher()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        Dispatchers.setMain(testDispatcher)
        viewModel = DisasterViewModel(repository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `getReports should return success`() = testDispatcher.runBlockingTest {
        // Given
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
        val mockResult = Result(
            features = mockFeature,
            type = "FeatureCollection"
        )
        val mockDisasterReports = DisasterReport(
            result = mockResult,
            statusCode = 200
        )

        `when`(repository.getReports("geojson")).thenReturn(mockDisasterReports)

        // When
        val observer = mock(Observer::class.java) as Observer<Resource<DisasterReport>>
        viewModel.getReports().observeForever(observer)

        // Then
        verify(repository).getReports("geojson")
        verify(observer).onChanged(Resource.loading(null))
        verify(observer).onChanged(Resource.success(mockDisasterReports))
    }

    @Test
    fun `getReportsByProvince should return success`() = testDispatcher.runBlockingTest {
        // Given
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
        val mockResult = Result(
            features = mockFeature,
            type = "FeatureCollection"
        )
        val mockDisasterReports = DisasterReport(
            result = mockResult,
            statusCode = 200
        )

        val provinceId = "ID-JK"
        `when`(repository.getReportsByProvince("geojson", provinceId)).thenReturn(mockDisasterReports)

        // When
        val observer = mock(Observer::class.java) as Observer<Resource<DisasterReport>>
        viewModel.getReportsByProvince(provinceId).observeForever(observer)

        // Then
        verify(repository).getReportsByProvince("geojson", provinceId)
        verify(observer).onChanged(Resource.loading(null))
        verify(observer).onChanged(Resource.success(mockDisasterReports))
    }

}

