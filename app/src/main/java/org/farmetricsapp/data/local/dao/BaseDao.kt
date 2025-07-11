package org.farmetricsapp.data.local.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update

interface BaseDao<T> {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: T)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(items: List<T>)

    @Update
    suspend fun update(item: T)

    @Update
    suspend fun updateAll(items: List<T>)

    @Delete
    suspend fun delete(item: T)

    @Delete
    suspend fun deleteAll(items: List<T>)
} 