package id.izazdhiya.disasterapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import id.izazdhiya.disasterapp.domain.repository.DisasterRepository
import id.izazdhiya.disasterapp.data.service.ApiService

@InstallIn(ViewModelComponent::class)
@Module
object RepositoryModule {
    @ViewModelScoped
    @Provides
    fun providesMovieRepository(apiService: ApiService) = DisasterRepository(apiService)
}