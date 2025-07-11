package org.farmetricsapp.data.local.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import org.farmetricsapp.data.local.entities.Farm

@Dao
interface FarmDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFarm(farm: Farm)

    @Update
    suspend fun updateFarm(farm: Farm)

    @Delete
    suspend fun deleteFarm(farm: Farm)

    @Query("SELECT * FROM farms")
    fun getFarms(): Flow<List<Farm>>

    @Query("SELECT * FROM farms WHERE id = :farmId")
    fun getFarmById(farmId: String): Flow<Farm?>

    @Query("SELECT * FROM farms WHERE farmerId = :farmerId")
    fun getFarmsByFarmerId(farmerId: String): Flow<List<Farm>>
} 