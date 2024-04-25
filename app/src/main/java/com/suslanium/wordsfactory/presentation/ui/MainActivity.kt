package com.suslanium.wordsfactory.presentation.ui

import android.Manifest.permission.POST_NOTIFICATIONS
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import androidx.work.BackoffPolicy
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.suslanium.wordsfactory.R
import com.suslanium.wordsfactory.domain.repository.TestRepository
import com.suslanium.wordsfactory.presentation.notification.NotificationWorker
import com.suslanium.wordsfactory.presentation.ui.navigation.WordsFactoryNavigation
import com.suslanium.wordsfactory.presentation.ui.theme.WordsFactoryTheme
import java.time.Duration
import java.time.ZoneId
import java.time.ZonedDateTime
import java.util.concurrent.TimeUnit

class MainActivity : ComponentActivity() {

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            enqueueTestReminders()
        } else {
            Toast.makeText(this, R.string.notification_permission_rejected_msg, Toast.LENGTH_LONG)
                .show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            WordsFactoryTheme {
                val navController = rememberNavController()
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    WordsFactoryNavigation(navController = navController)
                }
            }
        }

        if (checkSelfPermission(POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED || Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU) {
            enqueueTestReminders()
        } else {
            requestPermissionLauncher.launch(POST_NOTIFICATIONS)
        }
    }

    private fun enqueueTestReminders() {
        val workConstraints = Constraints.Builder().setRequiredNetworkType(NetworkType.NOT_REQUIRED)
            .setRequiresBatteryNotLow(false).setRequiresCharging(false)
            .setRequiresStorageNotLow(false).setRequiresDeviceIdle(false).build()

        val workRequest = PeriodicWorkRequestBuilder<NotificationWorker>(
            repeatInterval = 1, repeatIntervalTimeUnit = TimeUnit.DAYS
        ).setBackoffCriteria(BackoffPolicy.LINEAR, 1, TimeUnit.MINUTES)
            .setConstraints(workConstraints).setInitialDelay(calculateInitialNotificationDelay())
            .setConstraints(workConstraints).build()

        val workManager = WorkManager.getInstance(this)
        workManager.enqueueUniquePeriodicWork(
            "notification_worker", ExistingPeriodicWorkPolicy.CANCEL_AND_REENQUEUE, workRequest
        )
    }

    private fun calculateInitialNotificationDelay(): Duration {
        val currentTime = ZonedDateTime.now(ZoneId.systemDefault())
        var notificationTime = currentTime.with(TestRepository.NOTIFICATION_TIME)

        if (currentTime > notificationTime) {
            notificationTime = notificationTime.plusDays(1)
        }

        return Duration.between(currentTime, notificationTime)
    }
}