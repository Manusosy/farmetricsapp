package org.farmetricsapp.data.local.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import org.farmetricsapp.data.local.entities.FieldOfficer
import java.time.LocalDateTime

@Dao
interface FieldOfficerDao {
    @Query("SELECT * FROM field_officers")
    fun getAllFieldOfficers(): Flow<List<FieldOfficer>>

    @Query("SELECT * FROM field_officers WHERE id = :id")
    suspend fun getFieldOfficerById(id: String): FieldOfficer?

    @Query("SELECT * FROM field_officers WHERE email = :email")
    suspend fun getFieldOfficerByEmail(email: String): FieldOfficer?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFieldOfficer(fieldOfficer: FieldOfficer)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFieldOfficers(fieldOfficers: List<FieldOfficer>)

    @Update
    suspend fun updateFieldOfficer(fieldOfficer: FieldOfficer)

    @Delete
    suspend fun deleteFieldOfficer(fieldOfficer: FieldOfficer)

    @Query("DELETE FROM field_officers")
    suspend fun deleteAllFieldOfficers()

    @Query("UPDATE field_officers SET lastSyncTimestamp = :timestamp WHERE id = :id")
    suspend fun updateLastSyncTimestamp(id: String, timestamp: LocalDateTime)
} 