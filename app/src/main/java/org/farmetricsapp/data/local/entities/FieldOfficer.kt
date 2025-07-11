package org.farmetricsapp.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(tableName = "field_officers")
data class FieldOfficer(
    @PrimaryKey
    val id: String,
    val email: String,
    val fullName: String,
    val phoneNumber: String,
    val region: String,
    val district: String,
    val lastSyncTimestamp: LocalDateTime?,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val updatedAt: LocalDateTime = LocalDateTime.now()
) 