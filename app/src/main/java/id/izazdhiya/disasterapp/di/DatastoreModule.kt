package id.izazdhiya.disasterapp.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import id.izazdhiya.disasterapp.datastore.SettingsDataStore
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatastoreModule {
    @Singleton
    @Provides
    fun provideUserPreference(
        @ApplicationContext appContext: Context
    ) = SettingsDataStore(appContext)
}