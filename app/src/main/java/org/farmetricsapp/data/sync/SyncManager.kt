package org.farmetricsapp.data.sync

import android.content.Context
import androidx.work.*
import org.farmetricsapp.workers.FarmSyncWorker
import org.farmetricsapp.workers.FarmerSyncWorker
import org.farmetricsapp.workers.IssueSyncWorker
import org.farmetricsapp.workers.VisitSyncWorker
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class SyncManager @Inject constructor(
    private val workManager: WorkManager,
    private val context: Context
) {
    fun schedulePeriodicalSync() {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        // Schedule farmer sync
        val farmerSyncRequest = PeriodicWorkRequestBuilder<FarmerSyncWorker>(
            repeatInterval = 15,
            repeatIntervalTimeUnit = TimeUnit.MINUTES
        )
            .setConstraints(constraints)
            .setBackoffCriteria(
                BackoffPolicy.LINEAR,
                WorkRequest.MIN_BACKOFF_MILLIS,
                TimeUnit.MILLISECONDS
            )
            .build()

        // Schedule farm sync
        val farmSyncRequest = PeriodicWorkRequestBuilder<FarmSyncWorker>(
            repeatInterval = 15,
            repeatIntervalTimeUnit = TimeUnit.MINUTES
        )
            .setConstraints(constraints)
            .setBackoffCriteria(
                BackoffPolicy.LINEAR,
                WorkRequest.MIN_BACKOFF_MILLIS,
                TimeUnit.MILLISECONDS
            )
            .build()

        // Schedule visit sync
        val visitSyncRequest = PeriodicWorkRequestBuilder<VisitSyncWorker>(
            repeatInterval = 15,
            repeatIntervalTimeUnit = TimeUnit.MINUTES
        )
            .setConstraints(constraints)
            .setBackoffCriteria(
                BackoffPolicy.LINEAR,
                WorkRequest.MIN_BACKOFF_MILLIS,
                TimeUnit.MILLISECONDS
            )
            .build()

        // Schedule issue sync
        val issueSyncRequest = PeriodicWorkRequestBuilder<IssueSyncWorker>(
            repeatInterval = 15,
            repeatIntervalTimeUnit = TimeUnit.MINUTES
        )
            .setConstraints(constraints)
            .setBackoffCriteria(
                BackoffPolicy.LINEAR,
                WorkRequest.MIN_BACKOFF_MILLIS,
                TimeUnit.MILLISECONDS
            )
            .build()

        // Enqueue unique work for each entity
        workManager.enqueueUniquePeriodicWork(
            FarmerSyncWorker.UNIQUE_WORK_NAME,
            ExistingPeriodicWorkPolicy.KEEP,
            farmerSyncRequest
        )

        workManager.enqueueUniquePeriodicWork(
            FarmSyncWorker.UNIQUE_WORK_NAME,
            ExistingPeriodicWorkPolicy.KEEP,
            farmSyncRequest
        )

        workManager.enqueueUniquePeriodicWork(
            VisitSyncWorker.UNIQUE_WORK_NAME,
            ExistingPeriodicWorkPolicy.KEEP,
            visitSyncRequest
        )

        workManager.enqueueUniquePeriodicWork(
            IssueSyncWorker.UNIQUE_WORK_NAME,
            ExistingPeriodicWorkPolicy.KEEP,
            issueSyncRequest
        )
    }

    fun requestImmediateSync() {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val syncWorkRequest = OneTimeWorkRequestBuilder<FarmerSyncWorker>()
            .setConstraints(constraints)
            .build()

        val chainedWork = workManager
            .beginWith(syncWorkRequest)
            .then(OneTimeWorkRequestBuilder<FarmSyncWorker>().build())
            .then(OneTimeWorkRequestBuilder<VisitSyncWorker>().build())
            .then(OneTimeWorkRequestBuilder<IssueSyncWorker>().build())

        chainedWork.enqueue()
    }

    fun cancelAllSync() {
        workManager.cancelUniqueWork(FarmerSyncWorker.UNIQUE_WORK_NAME)
        workManager.cancelUniqueWork(FarmSyncWorker.UNIQUE_WORK_NAME)
        workManager.cancelUniqueWork(VisitSyncWorker.UNIQUE_WORK_NAME)
        workManager.cancelUniqueWork(IssueSyncWorker.UNIQUE_WORK_NAME)
    }

    fun getSyncStatus() = workManager.getWorkInfosForUniqueWork(FarmerSyncWorker.UNIQUE_WORK_NAME)
} 