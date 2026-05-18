package com.suryashakti.solarmonitor.ui.log

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.suryashakti.solarmonitor.databinding.FragmentLogBinding

class LogFragment : Fragment() {

    private var _b: FragmentLogBinding? = null
    private val b get() = _b!!
    private lateinit var vm: LogViewModel
    private lateinit var adapter: LogAdapter

    override fun onCreateView(i: LayoutInflater, c: ViewGroup?, s: Bundle?): View {
        _b = FragmentLogBinding.inflate(i, c, false); return b.root
    }

    override fun onViewCreated(view: View, s: Bundle?) {
        super.onViewCreated(view, s)
        vm = ViewModelProvider(this)[LogViewModel::class.java]

        adapter = LogAdapter()
        b.rvHistory.layoutManager = LinearLayoutManager(requireContext())
        b.rvHistory.adapter = adapter

        vm.allLogs.observe(viewLifecycleOwner) { adapter.submitList(it) }

        b.btnSave.setOnClickListener {
            val gen  = b.etGenerated.text.toString().toFloatOrNull()
            val prev = b.etPrevMeter.text.toString().toFloatOrNull() ?: 0f
            val curr = b.etCurrMeter.text.toString().toFloatOrNull()
            val rate = b.etRate.text.toString().toFloatOrNull() ?: 8f

            if (gen == null || curr == null) {
                Toast.makeText(requireContext(), "Please fill all required fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            vm.save(gen, prev, curr, rate)
            Toast.makeText(requireContext(), "✅ Entry saved!", Toast.LENGTH_SHORT).show()
            b.etGenerated.text?.clear()
            b.etPrevMeter.text?.clear()
            b.etCurrMeter.text?.clear()
        }
    }

    override fun onDestroyView() { super.onDestroyView(); _b = null }
}
