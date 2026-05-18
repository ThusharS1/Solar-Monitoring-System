package com.suryashakti.solarmonitor.ui.log

import android.graphics.Color
import android.view.*
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.suryashakti.solarmonitor.R
import com.suryashakti.solarmonitor.data.EnergyLog

class LogAdapter : ListAdapter<EnergyLog, LogAdapter.VH>(DIFF) {

    inner class VH(v: View) : RecyclerView.ViewHolder(v) {
        val tvDate    : TextView = v.findViewById(R.id.tv_log_date)
        val tvGen     : TextView = v.findViewById(R.id.tv_log_gen)
        val tvCons    : TextView = v.findViewById(R.id.tv_log_cons)
        val tvBadge   : TextView = v.findViewById(R.id.tv_log_badge)
    }

    override fun onCreateViewHolder(p: ViewGroup, t: Int) =
        VH(LayoutInflater.from(p.context).inflate(R.layout.item_log, p, false))

    override fun onBindViewHolder(h: VH, pos: Int) {
        val log = getItem(pos)
        h.tvDate.text  = log.date
        h.tvGen.text   = "${log.generatedKwh} kWh generated"
        h.tvCons.text  = "Consumed: ${log.consumedKwh} kWh"
        if (log.isExportToGrid) {
            h.tvBadge.text = "+${log.netKwh} Export"
            h.tvBadge.setBackgroundColor(Color.parseColor("#1a2a3a"))
            h.tvBadge.setTextColor(Color.parseColor("#90CAF9"))
        } else {
            h.tvBadge.text = "₹${"%.1f".format(log.savingsRupees)}"
            h.tvBadge.setBackgroundColor(Color.parseColor("#1a3a1a"))
            h.tvBadge.setTextColor(Color.parseColor("#81C784"))
        }
    }

    companion object {
        val DIFF = object : DiffUtil.ItemCallback<EnergyLog>() {
            override fun areItemsTheSame(a: EnergyLog, b: EnergyLog) = a.id == b.id
            override fun areContentsTheSame(a: EnergyLog, b: EnergyLog) = a == b
        }
    }
}
