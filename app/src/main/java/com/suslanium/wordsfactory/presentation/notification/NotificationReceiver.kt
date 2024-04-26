package com.suslanium.wordsfactory.presentation.notification

import android.Manifest
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.suslanium.wordsfactory.R
import com.suslanium.wordsfactory.domain.usecase.IsNotificationPendingUseCase
import com.suslanium.wordsfactory.presentation.ui.MainActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.inject
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

class NotificationReceiver : BroadcastReceiver() {

    private val isNotificationPendingUseCase: IsNotificationPendingUseCase by inject(
        IsNotificationPendingUseCase::class.java
    )

    companion object {
        private const val CHANNEL_ID = "test_reminder_notification_channel"
        private const val NOTIFICATION_ID = 1
        const val INTENT_ACTION = "com.suslanium.wordsfactory.TEST_REMINDER"
    }

    override fun onReceive(context: Context, intent: Intent) = goAsync {
        if (intent.action != INTENT_ACTION) return@goAsync
        if (!isNotificationPendingUseCase()) return@goAsync

        with(NotificationManagerCompat.from(context)) {
            if (ActivityCompat.checkSelfPermission(
                    context, Manifest.permission.POST_NOTIFICATIONS
                ) == PackageManager.PERMISSION_GRANTED || Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU
            ) {
                notify(NOTIFICATION_ID, createNotification(context, this))
            }
        }
    }

    private fun createNotification(
        context: Context,
        notificationManager: NotificationManagerCompat
    ): Notification {
        createNotificationChannel(context, notificationManager)

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

    private fun createNotificationChannel(
        context: Context,
        notificationManager: NotificationManagerCompat
    ) {
        val name = context.getString(R.string.notification_channel_name)
        val descriptionText = context.getString(R.string.notification_channel_description)
        val importance = NotificationManager.IMPORTANCE_HIGH
        val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
            description = descriptionText
        }

        notificationManager.createNotificationChannel(channel)
    }

    private fun BroadcastReceiver.goAsync(
        context: CoroutineContext = EmptyCoroutineContext,
        block: suspend CoroutineScope.() -> Unit
    ) {
        val pendingResult = goAsync()
        @OptIn(DelicateCoroutinesApi::class)
        GlobalScope.launch(context) {
            try {
                block()
            } finally {
                pendingResult.finish()
            }
        }
    }

}