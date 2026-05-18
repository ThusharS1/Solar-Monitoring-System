package com.suryashakti.solarmonitor.ui.dashboard

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.suryashakti.solarmonitor.data.AppDatabase
import kotlinx.coroutines.launch

class DashboardViewModel(app: Application) : AndroidViewModel(app) {

    private val dao = AppDatabase.get(app).energyLogDao()

    private val _solarPct   = MutableLiveData(75)
    private val _generated  = MutableLiveData(8.5f)
    private val _consumed   = MutableLiveData(5.8f)
    private val _savings    = MutableLiveData(46.4f)
    private val _isExporting = MutableLiveData(true)

    val solarPct:    LiveData<Int>     = _solarPct
    val generated:   LiveData<Float>   = _generated
    val consumed:    LiveData<Float>   = _consumed
    val savings:     LiveData<Float>   = _savings
    val isExporting: LiveData<Boolean> = _isExporting

    init {
        loadLatest()
    }

    private fun loadLatest() {
        viewModelScope.launch {
            dao.getLatest()?.let {
                _generated.postValue(it.generatedKwh)
                _consumed.postValue(it.consumedKwh)
                _savings.postValue(it.savingsRupees)
                _solarPct.postValue(it.independenceScore.toInt())
                _isExporting.postValue(it.isExportToGrid)
            }
        }
    }

    fun setWeather(condition: String) {
        val gen = when (condition) {
            "SUNNY"  -> 8.5f
            "PARTLY" -> 5.2f
            "CLOUDY" -> 2.8f
            else     -> 8.5f
        }
        _generated.value = gen
        recalc()
    }

    private fun recalc() {
        val gen  = _generated.value ?: 0f
        val cons = _consumed.value  ?: 0f
        val rate = 8f
        _solarPct.value    = if (cons > 0) ((gen / cons) * 100).toInt().coerceAtMost(100) else 0
        _savings.value     = minOf(gen, cons) * rate
        _isExporting.value = gen > cons
    }
}
