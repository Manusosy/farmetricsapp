package org.farmetricsapp.data.repository

import kotlinx.coroutines.flow.Flow
import org.farmetricsapp.data.local.dao.IssueDao
import org.farmetricsapp.data.local.entities.Issue
import org.farmetricsapp.domain.repository.IssueRepository
import javax.inject.Inject

class IssueRepositoryImpl @Inject constructor(
    private val issueDao: IssueDao
) : IssueRepository {
    override suspend fun syncIssues() {
        // Implementation will be added later
    }

    override suspend fun insertIssue(issue: Issue) {
        issueDao.insertIssue(issue)
    }

    override suspend fun updateIssue(issue: Issue) {
        issueDao.updateIssue(issue)
    }

    override suspend fun deleteIssue(issueId: String) {
        issueDao.getIssueById(issueId).collect { issue ->
            issue?.let { issueDao.deleteIssue(it) }
        }
    }

    override fun getIssues(): Flow<List<Issue>> {
        return issueDao.getIssues()
    }

    override fun getIssueById(issueId: String): Flow<Issue?> {
        return issueDao.getIssueById(issueId)
    }

    override fun getIssuesByFarmId(farmId: String): Flow<List<Issue>> {
        return issueDao.getIssuesByFarmId(farmId)
    }

    override fun getIssuesByStatus(status: String): Flow<List<Issue>> {
        return issueDao.getIssuesByStatus(status)
    }
} 