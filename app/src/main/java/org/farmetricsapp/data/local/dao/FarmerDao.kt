package org.farmetricsapp.data.local.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import org.farmetricsapp.data.local.entities.Farmer

@Dao
interface FarmerDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFarmer(farmer: Farmer)

    @Update
    suspend fun updateFarmer(farmer: Farmer)

    @Delete
    suspend fun deleteFarmer(farmer: Farmer)

    @Query("SELECT * FROM farmers")
    fun getFarmers(): Flow<List<Farmer>>

    @Query("SELECT * FROM farmers WHERE id = :farmerId")
    fun getFarmerById(farmerId: String): Flow<Farmer?>
} 