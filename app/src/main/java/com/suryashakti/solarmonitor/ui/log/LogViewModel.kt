package com.suryashakti.solarmonitor.ui.log

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.suryashakti.solarmonitor.data.AppDatabase
import com.suryashakti.solarmonitor.data.EnergyLog
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class LogViewModel(app: Application) : AndroidViewModel(app) {

    private val dao = AppDatabase.get(app).energyLogDao()
    val allLogs = dao.getAllLogs()

    fun save(generated: Float, prevMeter: Float, currMeter: Float, rate: Float) {
        viewModelScope.launch {
            val consumed = (currMeter - prevMeter).coerceAtLeast(0f)
            val net      = generated - consumed
            val savings  = minOf(generated, consumed) * rate
            val score    = if (consumed > 0) (generated / consumed * 100f).coerceAtMost(100f) else 0f
            val today    = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())

            dao.insert(EnergyLog(
                date              = today,
                generatedKwh      = generated,
                consumedKwh       = consumed,
                netKwh            = net,
                savingsRupees     = savings,
                ratePerUnit       = rate,
                independenceScore = score,
                isExportToGrid    = net > 0
            ))
        }
    }
}
