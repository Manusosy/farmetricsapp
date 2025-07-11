package org.farmetricsapp.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.jan.supabase.SupabaseClient
import org.farmetricsapp.data.repository.*
import org.farmetricsapp.domain.repository.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindFarmerRepository(
        farmerRepositoryImpl: FarmerRepositoryImpl
    ): FarmerRepository

    @Binds
    @Singleton
    abstract fun bindFarmRepository(
        farmRepositoryImpl: FarmRepositoryImpl
    ): FarmRepository

    @Binds
    @Singleton
    abstract fun bindIssueRepository(
        issueRepositoryImpl: IssueRepositoryImpl
    ): IssueRepository

    @Binds
    @Singleton
    abstract fun bindVisitRepository(
        visitRepositoryImpl: VisitRepositoryImpl
    ): VisitRepository

    companion object {
        @Provides
        @Singleton
        fun provideAuthRepository(
            supabaseClient: SupabaseClient
        ): AuthRepository {
            return AuthRepositoryImpl(supabaseClient)
        }
    }
} 