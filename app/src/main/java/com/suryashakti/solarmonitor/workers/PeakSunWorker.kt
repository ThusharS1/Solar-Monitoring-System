package com.suryashakti.solarmonitor.workers

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.work.*
import com.suryashakti.solarmonitor.R
import java.util.*
import java.util.concurrent.TimeUnit

class PeakSunWorker(ctx: Context, params: WorkerParameters) : Worker(ctx, params) {

    override fun doWork(): Result {
        val nm = applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val channelId = "peak_sun"

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            nm.createNotificationChannel(
                NotificationChannel(channelId, "Peak Sun Alerts", NotificationManager.IMPORTANCE_HIGH)
            )
        }

        nm.notify(1001,
            NotificationCompat.Builder(applicationContext, channelId)
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setContentTitle("🔆 Peak Solar Hours!")
                .setContentText("Best time to run: washing machine, water pump, AC")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setAutoCancel(true)
                .build()
        )
        return Result.success()
    }

    companion object {
        fun scheduleDailyAlert(context: Context) {
            val now    = Calendar.getInstance()
            val target = Calendar.getInstance().apply {
                set(Calendar.HOUR_OF_DAY, 11); set(Calendar.MINUTE, 0); set(Calendar.SECOND, 0)
                if (before(now)) add(Calendar.DAY_OF_YEAR, 1)
            }
            val delay = target.timeInMillis - now.timeInMillis

            WorkManager.getInstance(context).enqueueUniquePeriodicWork(
                "peak_sun",
                ExistingPeriodicWorkPolicy.KEEP,
                PeriodicWorkRequestBuilder<PeakSunWorker>(24, TimeUnit.HOURS)
                    .setInitialDelay(delay, TimeUnit.MILLISECONDS)
                    .build()
            )
        }
    }
}
