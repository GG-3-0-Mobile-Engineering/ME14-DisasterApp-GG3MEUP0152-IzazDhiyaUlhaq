package id.izazdhiya.disasterapp.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.izazdhiya.disasterapp.data.source.local.DisasterDatabase
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(app: Application): DisasterDatabase =
        Room.databaseBuilder(app, DisasterDatabase::class.java, "disaster").build()

    @Singleton
    @Provides
    fun providesUserDao(database: DisasterDatabase) = database.disasterDao()


}