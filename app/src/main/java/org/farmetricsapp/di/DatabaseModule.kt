package org.farmetricsapp.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import org.farmetricsapp.data.local.FarmetricsDatabase
import org.farmetricsapp.data.local.dao.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): FarmetricsDatabase {
        return Room.databaseBuilder(
            context,
            FarmetricsDatabase::class.java,
            FarmetricsDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    fun provideFieldOfficerDao(database: FarmetricsDatabase): FieldOfficerDao {
        return database.fieldOfficerDao()
    }

    @Provides
    fun provideFarmerDao(database: FarmetricsDatabase): FarmerDao {
        return database.farmerDao()
    }

    @Provides
    fun provideFarmDao(database: FarmetricsDatabase): FarmDao {
        return database.farmDao()
    }

    @Provides
    fun provideVisitDao(database: FarmetricsDatabase): VisitDao {
        return database.visitDao()
    }

    @Provides
    fun provideIssueDao(database: FarmetricsDatabase): IssueDao {
        return database.issueDao()
    }
} 