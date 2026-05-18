package com.suryashakti.solarmonitor.ui.reports

import android.graphics.Color
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.suryashakti.solarmonitor.databinding.FragmentReportsBinding

class ReportsFragment : Fragment() {

    private var _b: FragmentReportsBinding? = null
    private val b get() = _b!!
    private lateinit var vm: ReportsViewModel

    override fun onCreateView(i: LayoutInflater, c: ViewGroup?, s: Bundle?): View {
        _b = FragmentReportsBinding.inflate(i, c, false); return b.root
    }

    override fun onViewCreated(view: View, s: Bundle?) {
        super.onViewCreated(view, s)
        vm = ViewModelProvider(this)[ReportsViewModel::class.java]

        setupBarChart()

        vm.totalSavings.observe(viewLifecycleOwner) {
            b.tvTotalSavings.text = "₹${"%.0f".format(it)}"
        }
        vm.totalGenerated.observe(viewLifecycleOwner) {
            b.tvTotalGenerated.text = "${"%.1f".format(it)} kWh"
        }
        vm.logs.observe(viewLifecycleOwner) { logs ->
            val entries = logs.mapIndexed { i, log ->
                BarEntry(i.toFloat(), log.generatedKwh)
            }
            val ds = BarDataSet(entries, "Solar Generation (kWh)").apply {
                color = Color.parseColor("#FDD835")
                valueTextColor = Color.TRANSPARENT
            }
            b.barChart.data = BarData(ds).apply { barWidth = 0.7f }
            b.barChart.invalidate()
        }
    }

    private fun setupBarChart() {
        b.barChart.apply {
            description.isEnabled = false
            legend.isEnabled = false
            setTouchEnabled(false)
            setDrawGridBackground(false)
            setBackgroundColor(Color.TRANSPARENT)
            xAxis.position = XAxis.XAxisPosition.BOTTOM
            xAxis.setDrawGridLines(false)
            xAxis.textColor = Color.parseColor("#7CB47C")
            axisLeft.textColor = Color.parseColor("#7CB47C")
            axisLeft.setDrawGridLines(false)
            axisRight.isEnabled = false
        }
    }

    override fun onDestroyView() { super.onDestroyView(); _b = null }
}
