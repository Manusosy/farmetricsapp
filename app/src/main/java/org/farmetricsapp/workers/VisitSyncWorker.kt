package org.farmetricsapp.workers

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import org.farmetricsapp.domain.repository.AuthRepository
import org.farmetricsapp.domain.repository.VisitRepository

@HiltWorker
class VisitSyncWorker @AssistedInject constructor(
    @Assisted appContext: Context,
    @Assisted workerParams: WorkerParameters,
    private val authRepository: AuthRepository,
    private val visitRepository: VisitRepository
) : CoroutineWorker(appContext, workerParams) {

    override suspend fun doWork(): Result {
        return try {
            // Get current officer ID
            val currentOfficerId = authRepository.getCurrentOfficerId()
            if (currentOfficerId == null) {
                return Result.failure()
            }

            // Sync visits
            visitRepository.syncVisits()
            Result.success()
        } catch (e: Exception) {
            Result.retry()
        }
    }

    companion object {
        const val UNIQUE_WORK_NAME = "visit_sync_worker"
    }
} 