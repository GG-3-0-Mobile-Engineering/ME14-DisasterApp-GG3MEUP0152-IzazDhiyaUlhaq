package id.izazdhiya.disasterapp.viewmodeltest

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import id.izazdhiya.disasterapp.data.datastore.SettingsDataStore
import id.izazdhiya.disasterapp.ui.viewmodel.MainViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class MainViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var settingsDataStore: SettingsDataStore

    private val testDispatcher = TestCoroutineDispatcher()
    private val testScope = TestCoroutineScope(testDispatcher)

    private lateinit var viewModel: MainViewModel

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(testDispatcher)
        viewModel = MainViewModel(settingsDataStore)
    }

    @After
    fun cleanup() {
        Dispatchers.resetMain()
        testScope.cleanupTestCoroutines()
    }

    @Test
    fun testGetTheme() {
        val fakeThemeValue = true
        `when`(settingsDataStore.getThemeSetting()).thenReturn(flowOf(fakeThemeValue))

        val observer = Observer<Boolean> {}

        try {
            viewModel.getTheme().observeForever(observer)

        } finally {
            viewModel.getTheme().removeObserver(observer)
        }
    }
}
