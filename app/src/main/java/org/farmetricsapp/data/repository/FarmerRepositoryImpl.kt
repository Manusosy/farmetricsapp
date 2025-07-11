package org.farmetricsapp.data.repository

import kotlinx.coroutines.flow.Flow
import org.farmetricsapp.data.local.dao.FarmerDao
import org.farmetricsapp.data.local.entities.Farmer
import org.farmetricsapp.domain.repository.FarmerRepository
import javax.inject.Inject

class FarmerRepositoryImpl @Inject constructor(
    private val farmerDao: FarmerDao
) : FarmerRepository {
    override suspend fun syncFarmers() {
        // Implementation will be added later
    }

    override suspend fun insertFarmer(farmer: Farmer) {
        farmerDao.insertFarmer(farmer)
    }

    override suspend fun updateFarmer(farmer: Farmer) {
        farmerDao.updateFarmer(farmer)
    }

    override suspend fun deleteFarmer(farmerId: String) {
        farmerDao.getFarmerById(farmerId).collect { farmer ->
            farmer?.let { farmerDao.deleteFarmer(it) }
        }
    }

    override fun getFarmers(): Flow<List<Farmer>> {
        return farmerDao.getFarmers()
    }

    override fun getFarmerById(farmerId: String): Flow<Farmer?> {
        return farmerDao.getFarmerById(farmerId)
    }
} 