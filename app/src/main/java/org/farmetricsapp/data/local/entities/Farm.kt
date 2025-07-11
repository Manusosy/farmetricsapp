package org.farmetricsapp.data.local.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(
    tableName = "farms",
    foreignKeys = [
        ForeignKey(
            entity = Farmer::class,
            parentColumns = ["id"],
            childColumns = ["farmerId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index(value = ["farmerId"]),
        Index(value = ["name"]),
        Index(value = ["primaryCrop"]),
        Index(value = ["location"])
    ]
)
data class Farm(
    @PrimaryKey
    val id: String,
    val farmerId: String,
    val name: String,
    val size: Double,
    val sizeUnit: String,
    val primaryCrop: String,
    val secondaryCrops: List<String>?,
    val soilType: String?,
    val irrigationType: String?,
    val boundaries: String?, // GeoJSON string
    val location: String?, // Latitude,Longitude string
    val lastSyncTimestamp: LocalDateTime?,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val updatedAt: LocalDateTime = LocalDateTime.now()
) 