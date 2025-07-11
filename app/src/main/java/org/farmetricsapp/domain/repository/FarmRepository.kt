package org.farmetricsapp.domain.repository

import kotlinx.coroutines.flow.Flow
import org.farmetricsapp.data.local.entities.Farm

interface FarmRepository {
    suspend fun syncFarms()
    suspend fun insertFarm(farm: Farm)
    suspend fun updateFarm(farm: Farm)
    suspend fun deleteFarm(farmId: String)
    fun getFarms(): Flow<List<Farm>>
    fun getFarmById(farmId: String): Flow<Farm?>
    fun getFarmsByFarmerId(farmerId: String): Flow<List<Farm>>
} 