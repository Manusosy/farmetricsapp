package org.farmetricsapp.workers

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.first
import org.farmetricsapp.domain.repository.AuthRepository
import org.farmetricsapp.domain.repository.FarmerRepository

@HiltWorker
class FarmerSyncWorker @AssistedInject constructor(
    @Assisted appContext: Context,
    @Assisted workerParams: WorkerParameters,
    private val authRepository: AuthRepository,
    private val farmerRepository: FarmerRepository
) : CoroutineWorker(appContext, workerParams) {

    override suspend fun doWork(): Result {
        return try {
            // Get current user
            val currentUser = authRepository.getCurrentUser().first()
            if (currentUser.data == null) {
                return Result.failure()
            }

            // Sync farmers
            farmerRepository.syncFarmers(currentUser.data.id).first()
            Result.success()
        } catch (e: Exception) {
            Result.retry()
        }
    }

    companion object {
        const val UNIQUE_WORK_NAME = "farmer_sync_worker"
    }
} 