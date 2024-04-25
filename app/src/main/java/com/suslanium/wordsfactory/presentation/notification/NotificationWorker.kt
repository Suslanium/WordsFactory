package com.suslanium.wordsfactory.presentation.notification

import android.Manifest
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.suslanium.wordsfactory.R
import com.suslanium.wordsfactory.domain.usecase.IsNotificationPendingUseCase
import com.suslanium.wordsfactory.presentation.ui.MainActivity
import org.koin.java.KoinJavaComponent.inject

class NotificationWorker(private val context: Context, workerParams: WorkerParameters) :
    CoroutineWorker(context, workerParams) {

    private val isNotificationPendingUseCase: IsNotificationPendingUseCase by inject(
        IsNotificationPendingUseCase::class.java
    )

    private companion object {
        const val CHANNEL_ID = "test_reminder_notification_channel"
        const val NOTIFICATION_ID = 1
    }

    override suspend fun doWork(): Result {
        Log.e("NotificationWorker", "doWork")
        if (isNotificationPendingUseCase()) {
            with(NotificationManagerCompat.from(context)) {
                if (ActivityCompat.checkSelfPermission(
                        context, Manifest.permission.POST_NOTIFICATIONS
                    ) == PackageManager.PERMISSION_GRANTED || Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU
                ) {
                    notify(NOTIFICATION_ID, createNotification(this))
                }
            }
        }
        return Result.success()
    }

    private fun createNotification(notificationManager: NotificationManagerCompat): Notification {
        createNotificationChannel(notificationManager)

        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent =
            PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_IMMUTABLE)

        return NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.dictionary_icon)
            .setContentTitle(context.getString(R.string.notification_title))
            .setContentText(context.getString(R.string.notification_message))
            .setContentIntent(pendingIntent).setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_HIGH).build()
    }

    private fun createNotificationChannel(notificationManager: NotificationManagerCompat) {
        val name = context.getString(R.string.notification_channel_name)
        val descriptionText = context.getString(R.string.notification_channel_description)
        val importance = NotificationManager.IMPORTANCE_HIGH
        val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
            description = descriptionText
        }

        notificationManager.createNotificationChannel(channel)
    }

}