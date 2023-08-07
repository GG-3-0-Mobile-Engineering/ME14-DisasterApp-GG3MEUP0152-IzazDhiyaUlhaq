//package id.izazdhiya.disasterapp.di
//
//import dagger.Module
//import dagger.Provides
//import dagger.hilt.InstallIn
//import dagger.hilt.components.SingletonComponent
//import id.izazdhiya.disasterapp.model.local.DisasterDatabase
//import javax.inject.Singleton
//
//@InstallIn(SingletonComponent::class)
//@Module
//object DatabaseModule {
//
//    @Singleton
//    @Provides
//    fun providesUserDao(database: DisasterDatabase) = database.disasterDao()
//
//}