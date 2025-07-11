package org.farmetricsapp.data.repository

import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.postgrest.query.Order
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import org.farmetricsapp.data.local.dao.VisitDao
import org.farmetricsapp.data.local.entities.Visit
import org.farmetricsapp.domain.repository.VisitRepository
import java.time.LocalDateTime
import javax.inject.Inject

class VisitRepositoryImpl @Inject constructor(
    private val visitDao: VisitDao,
    private val supabaseClient: io.github.jan.supabase.SupabaseClient
) : VisitRepository {
    override suspend fun syncVisits() {
        try {
            // Fetch visits from Supabase
            val remoteVisits = supabaseClient.postgrest["visits"]
                .select {
                    order("created_at", Order.DESCENDING)
                }
                .decodeList<Visit>()

            // Insert all visits into local database
            visitDao.insertAll(remoteVisits)
        } catch (e: Exception) {
            // Handle error appropriately
            e.printStackTrace()
        }
    }

    override suspend fun insertVisit(visit: Visit) {
        try {
            // Insert to Supabase first
            supabaseClient.postgrest["visits"]
                .insert(visit)

            // Then to local database
            visitDao.insertVisit(visit)
        } catch (e: Exception) {
            // If Supabase insert fails, still save locally
            visitDao.insertVisit(visit)
        }
    }

    override suspend fun updateVisit(visit: Visit) {
        try {
            // Update in Supabase first
            supabaseClient.postgrest["visits"]
                .update(visit) {
                    filter {
                        eq("id", visit.id)
                    }
                }

            // Then update local database
            visitDao.updateVisit(visit)
        } catch (e: Exception) {
            // If Supabase update fails, still update locally
            visitDao.updateVisit(visit)
        }
    }

    override suspend fun deleteVisit(visitId: String) {
        visitDao.getVisitById(visitId).collect { visit ->
            visit?.let { 
                try {
                    // Delete from Supabase first
                    supabaseClient.postgrest["visits"]
                        .delete {
                            filter {
                                eq("id", visitId)
                            }
                        }

                    // Then delete locally
                    visitDao.deleteVisit(it)
                } catch (e: Exception) {
                    // If Supabase delete fails, still delete locally
                    visitDao.deleteVisit(it)
                }
            }
        }
    }

    override fun getVisits(): Flow<List<Visit>> {
        return visitDao.getVisits()
    }

    override fun getVisitById(visitId: String): Flow<Visit?> {
        return visitDao.getVisitById(visitId)
    }

    override fun getVisitsByFarmId(farmId: String): Flow<List<Visit>> {
        return visitDao.getVisitsByFarmId(farmId)
    }

    override fun getVisitsByDateRange(startDate: LocalDateTime, endDate: LocalDateTime): Flow<List<Visit>> {
        return visitDao.getVisitsByDateRange(startDate, endDate)
    }

    override fun searchVisits(query: String): Flow<List<Visit>> {
        return visitDao.searchVisits(query)
    }
} 