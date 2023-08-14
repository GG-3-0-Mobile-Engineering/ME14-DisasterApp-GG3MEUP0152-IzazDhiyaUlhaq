package id.izazdhiya.disasterapp.repositorytest

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import id.izazdhiya.disasterapp.data.service.ApiService
import id.izazdhiya.disasterapp.domain.repository.DisasterRepository
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class DisasterRepositoryTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var mockApiService: ApiService

    private val testDispatcher = TestCoroutineDispatcher()
    private val testScope = TestCoroutineScope(testDispatcher)

    private lateinit var repository: DisasterRepository

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        repository = DisasterRepository(mockApiService)
    }

    @After
    fun cleanup() {
        testScope.cleanupTestCoroutines()
    }

    @Test
    fun testGetReports() = testScope.runBlockingTest {
        val fakeReports = null
            Mockito.`when`(mockApiService.getReports(Mockito.anyString())).thenReturn(fakeReports)

        val result = repository.getReports(Mockito.anyString())

        assertEquals(fakeReports, result)
    }

    @Test
    fun testGetReportsByProvince() = testScope.runBlockingTest {
        val fakeReports = null
            Mockito.`when`(mockApiService.getReportsByProvince(Mockito.anyString(), Mockito.anyString())).thenReturn(fakeReports)

        val result = repository.getReportsByProvince(Mockito.anyString(), Mockito.anyString())

        assertEquals(fakeReports, result)
    }

    @Test
    fun testGetArchive() = testScope.runBlockingTest {
        val fakeReports = null
        Mockito.`when`(mockApiService.getArchive(Mockito.anyString(), Mockito.anyString(), Mockito.anyString())).thenReturn(fakeReports)

        val result = repository.getArchive(Mockito.anyString(), Mockito.anyString(), Mockito.anyString())

        assertEquals(fakeReports, result)
    }
}
