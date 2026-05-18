package com.suryashakti.solarmonitor.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "energy_log")
data class EnergyLog(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val date: String,
    val generatedKwh: Float,
    val consumedKwh: Float,
    val netKwh: Float,
    val savingsRupees: Float,
    val ratePerUnit: Float,
    val independenceScore: Float,
    val isExportToGrid: Boolean,
    val weatherCondition: String = "SUNNY"
)
