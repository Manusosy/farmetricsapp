package org.farmetricsapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import org.farmetricsapp.data.local.converters.DateConverters
import org.farmetricsapp.data.local.converters.ListConverters
import org.farmetricsapp.data.local.dao.*
import org.farmetricsapp.data.local.entities.*

@Database(
    entities = [
        FieldOfficer::class,
        Farmer::class,
        Farm::class,
        Visit::class,
        Issue::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(DateConverters::class, ListConverters::class)
abstract class FarmetricsDatabase : RoomDatabase() {
    abstract fun fieldOfficerDao(): FieldOfficerDao
    abstract fun farmerDao(): FarmerDao
    abstract fun farmDao(): FarmDao
    abstract fun visitDao(): VisitDao
    abstract fun issueDao(): IssueDao

    companion object {
        const val DATABASE_NAME = "farmetrics_db"
    }
} 