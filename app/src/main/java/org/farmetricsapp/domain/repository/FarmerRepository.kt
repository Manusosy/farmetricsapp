package org.farmetricsapp.domain.repository

import org.farmetricsapp.data.local.entities.Farmer
import kotlinx.coroutines.flow.Flow

interface FarmerRepository {
    suspend fun syncFarmers()
    suspend fun insertFarmer(farmer: Farmer)
    suspend fun updateFarmer(farmer: Farmer)
    suspend fun deleteFarmer(farmerId: String)
    fun getFarmers(): Flow<List<Farmer>>
    fun getFarmerById(farmerId: String): Flow<Farmer?>
} 