package com.suslanium.wordsfactory.presentation.ui

import android.Manifest.permission.POST_NOTIFICATIONS
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Intent
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
import com.suslanium.wordsfactory.R
import com.suslanium.wordsfactory.presentation.notification.NotificationReceiver
import com.suslanium.wordsfactory.presentation.ui.navigation.WordsFactoryNavigation
import com.suslanium.wordsfactory.presentation.ui.theme.WordsFactoryTheme
import java.time.LocalTime
import java.time.ZonedDateTime

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
        val alarmIntent = Intent(this, NotificationReceiver::class.java).apply {
            action = NotificationReceiver.INTENT_ACTION
        }
        alarmIntent.setPackage(packageName)
        var pendingIntent = PendingIntent.getBroadcast(
            this, 0, alarmIntent, PendingIntent.FLAG_NO_CREATE or PendingIntent.FLAG_IMMUTABLE
        )
        if (pendingIntent == null) {
            pendingIntent = PendingIntent.getBroadcast(
                this, 0, alarmIntent, PendingIntent.FLAG_IMMUTABLE
            )
            var notificationTime = ZonedDateTime.now().with(LocalTime.of(20, 0))
            if (ZonedDateTime.now() > notificationTime) {
                notificationTime = notificationTime.plusDays(1)
            }

            val alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager
            alarmManager.setRepeating(
                AlarmManager.RTC_WAKEUP,
                notificationTime.toInstant().toEpochMilli(),
                AlarmManager.INTERVAL_DAY,
                pendingIntent
            )
        }
    }
}