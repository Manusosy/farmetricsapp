package org.farmetricsapp.data.local.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(
    tableName = "issues",
    foreignKeys = [
        ForeignKey(
            entity = Farm::class,
            parentColumns = ["id"],
            childColumns = ["farmId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["farmId"])]
)
data class Issue(
    @PrimaryKey
    val id: String,
    val farmId: String,
    val title: String,
    val description: String,
    val severity: String, // LOW, MEDIUM, HIGH
    val status: String, // OPEN, IN_PROGRESS, RESOLVED
    val images: List<String>?, // List of image URLs
    val location: String?, // Latitude,Longitude string
    val lastSyncTimestamp: LocalDateTime?,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val updatedAt: LocalDateTime = LocalDateTime.now()
) 