package com.suryashakti.solarmonitor.ui.dashboard

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.suryashakti.solarmonitor.databinding.FragmentDashboardBinding

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!
    private lateinit var vm: DashboardViewModel

    override fun onCreateView(i: LayoutInflater, c: ViewGroup?, s: Bundle?): View {
        _binding = FragmentDashboardBinding.inflate(i, c, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vm = ViewModelProvider(this)[DashboardViewModel::class.java]

        setupPieChart()

        vm.solarPct.observe(viewLifecycleOwner) { pct ->
            updateGauge(pct)
            binding.tvGaugePct.text = "$pct%"
            binding.tvIndependence.text = "$pct% Independent"
        }
        vm.generated.observe(viewLifecycleOwner) {
            binding.tvGenerated.text = "${it} kWh"
        }
        vm.consumed.observe(viewLifecycleOwner) {
            binding.tvConsumed.text = "${it} kWh"
        }
        vm.savings.observe(viewLifecycleOwner) {
            binding.tvSavings.text = "₹$it"
        }
        vm.isExporting.observe(viewLifecycleOwner) {
            binding.exportBanner.visibility = if (it) View.VISIBLE else View.GONE
        }

        // Weather buttons
        binding.btnSunny.setOnClickListener    { vm.setWeather("SUNNY");  highlightWeather(0) }
        binding.btnCloudy.setOnClickListener   { vm.setWeather("PARTLY"); highlightWeather(1) }
        binding.btnOvercast.setOnClickListener { vm.setWeather("CLOUDY"); highlightWeather(2) }
    }

    private fun setupPieChart() {
        binding.pieChart.apply {
            isDrawHoleEnabled = true
            holeRadius = 72f
            setHoleColor(Color.TRANSPARENT)
            description.isEnabled = false
            legend.isEnabled = false
            setTouchEnabled(false)
            setDrawEntryLabels(false)
        }
    }

    private fun updateGauge(pct: Int) {
        val entries = listOf(
            PieEntry(pct.toFloat()),
            PieEntry((100 - pct).toFloat())
        )
        val ds = PieDataSet(entries, "").apply {
            colors = listOf(Color.parseColor("#FDD835"), Color.parseColor("#2a3d2a"))
            sliceSpace = 2f
            setDrawValues(false)
        }
        binding.pieChart.data = PieData(ds)
        binding.pieChart.invalidate()
    }

    private fun highlightWeather(idx: Int) {
        val btns = listOf(binding.btnSunny, binding.btnCloudy, binding.btnOvercast)
        btns.forEachIndexed { i, b -> b.isSelected = (i == idx) }
    }

    override fun onDestroyView() { super.onDestroyView(); _binding = null }
}
