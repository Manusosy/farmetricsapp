package org.farmetricsapp.data.repository

import kotlinx.coroutines.flow.Flow
import org.farmetricsapp.data.local.dao.FarmDao
import org.farmetricsapp.data.local.entities.Farm
import org.farmetricsapp.domain.repository.FarmRepository
import javax.inject.Inject

class FarmRepositoryImpl @Inject constructor(
    private val farmDao: FarmDao
) : FarmRepository {
    override suspend fun syncFarms() {
        // Implementation will be added later
    }

    override suspend fun insertFarm(farm: Farm) {
        farmDao.insertFarm(farm)
    }

    override suspend fun updateFarm(farm: Farm) {
        farmDao.updateFarm(farm)
    }

    override suspend fun deleteFarm(farmId: String) {
        farmDao.getFarmById(farmId).collect { farm ->
            farm?.let { farmDao.deleteFarm(it) }
        }
    }

    override fun getFarms(): Flow<List<Farm>> {
        return farmDao.getFarms()
    }

    override fun getFarmById(farmId: String): Flow<Farm?> {
        return farmDao.getFarmById(farmId)
    }

    override fun getFarmsByFarmerId(farmerId: String): Flow<List<Farm>> {
        return farmDao.getFarmsByFarmerId(farmerId)
    }
} 