package org.farmetricsapp.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(tableName = "farmers")
data class Farmer(
    @PrimaryKey
    val id: String,
    val fieldOfficerId: String,
    val fullName: String,
    val phoneNumber: String,
    val location: String,
    val lastSyncTimestamp: LocalDateTime?,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val updatedAt: LocalDateTime = LocalDateTime.now()
) 