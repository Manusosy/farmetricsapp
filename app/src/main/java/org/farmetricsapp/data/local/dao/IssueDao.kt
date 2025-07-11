package org.farmetricsapp.data.local.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import org.farmetricsapp.data.local.entities.Issue

@Dao
interface IssueDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertIssue(issue: Issue)

    @Update
    suspend fun updateIssue(issue: Issue)

    @Delete
    suspend fun deleteIssue(issue: Issue)

    @Query("SELECT * FROM issues")
    fun getIssues(): Flow<List<Issue>>

    @Query("SELECT * FROM issues WHERE id = :issueId")
    fun getIssueById(issueId: String): Flow<Issue?>

    @Query("SELECT * FROM issues WHERE farmId = :farmId")
    fun getIssuesByFarmId(farmId: String): Flow<List<Issue>>

    @Query("SELECT * FROM issues WHERE status = :status")
    fun getIssuesByStatus(status: String): Flow<List<Issue>>
} 