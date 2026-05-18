package com.suryashakti.solarmonitor.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface EnergyLogDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(log: EnergyLog)

    @Query("SELECT * FROM energy_log ORDER BY date DESC")
    fun getAllLogs(): LiveData<List<EnergyLog>>

    @Query("SELECT * FROM energy_log ORDER BY date DESC LIMIT 30")
    fun getLast30(): LiveData<List<EnergyLog>>

    @Query("SELECT * FROM energy_log ORDER BY date DESC LIMIT 1")
    suspend fun getLatest(): EnergyLog?

    @Query("SELECT COALESCE(SUM(savingsRupees),0) FROM energy_log WHERE date >= :from")
    fun getTotalSavings(from: String): LiveData<Float>

    @Query("SELECT COALESCE(SUM(generatedKwh),0) FROM energy_log WHERE date >= :from")
    fun getTotalGenerated(from: String): LiveData<Float>

    @Query("SELECT COUNT(*) FROM energy_log")
    fun getCount(): LiveData<Int>
}
