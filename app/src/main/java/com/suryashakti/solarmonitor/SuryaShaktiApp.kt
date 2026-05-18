package com.suryashakti.solarmonitor

import android.app.Application
import com.suryashakti.solarmonitor.workers.PeakSunWorker

class SuryaShaktiApp : Application() {
    override fun onCreate() {
        super.onCreate()
        // Schedule daily 11 AM peak sun notification
        PeakSunWorker.scheduleDailyAlert(this)
    }
}
