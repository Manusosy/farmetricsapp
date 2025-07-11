package org.farmetricsapp.domain.repository

import kotlinx.coroutines.flow.Flow
import org.farmetricsapp.data.local.entities.Issue

interface IssueRepository {
    suspend fun syncIssues()
    suspend fun insertIssue(issue: Issue)
    suspend fun updateIssue(issue: Issue)
    suspend fun deleteIssue(issueId: String)
    fun getIssues(): Flow<List<Issue>>
    fun getIssueById(issueId: String): Flow<Issue?>
    fun getIssuesByFarmId(farmId: String): Flow<List<Issue>>
    fun getIssuesByStatus(status: String): Flow<List<Issue>>
} 