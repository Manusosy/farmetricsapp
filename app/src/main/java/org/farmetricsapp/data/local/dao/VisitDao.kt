package org.farmetricsapp.data.local.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import org.farmetricsapp.data.local.entities.Visit
import java.time.LocalDateTime

@Dao
interface VisitDao {
    @Query("SELECT * FROM visits")
    fun getVisits(): Flow<List<Visit>>

    @Query("SELECT * FROM visits WHERE id = :visitId")
    fun getVisitById(visitId: String): Flow<Visit?>

    @Query("SELECT * FROM visits WHERE farmId = :farmId")
    fun getVisitsByFarmId(farmId: String): Flow<List<Visit>>

    @Query("SELECT * FROM visits WHERE visitDate BETWEEN :startDate AND :endDate")
    fun getVisitsByDateRange(startDate: LocalDateTime, endDate: LocalDateTime): Flow<List<Visit>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVisit(visit: Visit)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(visits: List<Visit>)

    @Update
    suspend fun updateVisit(visit: Visit)

    @Delete
    suspend fun deleteVisit(visit: Visit)

    @Query("""
        SELECT * FROM visits 
        WHERE purpose LIKE '%' || :query || '%' 
        OR notes LIKE '%' || :query || '%'
    """)
    fun searchVisits(query: String): Flow<List<Visit>>
} 