package id.izazdhiya.disasterapp.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import id.izazdhiya.disasterapp.repository.DisasterRepository
import id.izazdhiya.disasterapp.service.ApiService

@InstallIn(ViewModelComponent::class)
@Module
object RepositoryModule {
    @ViewModelScoped
    @Provides
    fun providesMovieRepository(apiService: ApiService) = DisasterRepository(apiService)
}