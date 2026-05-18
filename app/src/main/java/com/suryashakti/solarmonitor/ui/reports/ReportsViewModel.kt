package com.suryashakti.solarmonitor.ui.reports

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.suryashakti.solarmonitor.data.AppDatabase
import java.text.SimpleDateFormat
import java.util.*

class ReportsViewModel(app: Application) : AndroidViewModel(app) {

    private val dao = AppDatabase.get(app).energyLogDao()

    private fun thirtyDaysAgo(): String {
        val cal = Calendar.getInstance().apply { add(Calendar.DAY_OF_YEAR, -30) }
        return SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(cal.time)
    }

    val logs         = dao.getLast30()
    val totalSavings = dao.getTotalSavings(thirtyDaysAgo())
    val totalGenerated = dao.getTotalGenerated(thirtyDaysAgo())
}
