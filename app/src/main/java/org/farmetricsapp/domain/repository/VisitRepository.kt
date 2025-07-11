package org.farmetricsapp.domain.repository

import kotlinx.coroutines.flow.Flow
import org.farmetricsapp.data.local.entities.Visit
import java.time.LocalDateTime

interface VisitRepository {
    suspend fun syncVisits()
    suspend fun insertVisit(visit: Visit)
    suspend fun updateVisit(visit: Visit)
    suspend fun deleteVisit(visitId: String)
    fun getVisits(): Flow<List<Visit>>
    fun getVisitById(visitId: String): Flow<Visit?>
    fun getVisitsByFarmId(farmId: String): Flow<List<Visit>>
    fun getVisitsByDateRange(startDate: LocalDateTime, endDate: LocalDateTime): Flow<List<Visit>>
    fun searchVisits(query: String): Flow<List<Visit>>
} 