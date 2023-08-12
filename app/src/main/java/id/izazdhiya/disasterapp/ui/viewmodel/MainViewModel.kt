package id.izazdhiya.disasterapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import id.izazdhiya.disasterapp.data.datastore.SettingsDataStore
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val preferences: SettingsDataStore) : ViewModel() {
    fun getTheme() = preferences.getThemeSetting().asLiveData()

    class Factory(private val preferences: SettingsDataStore) :
        ViewModelProvider.NewInstanceFactory() {

        override fun <T : ViewModel> create(modelClass: Class<T>): T =
            MainViewModel(preferences) as T
    }
}