package com.suryashakti.solarmonitor.ui.tips

import android.os.Bundle
import android.view.*
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.google.ai.client.generativeai.GenerativeModel
import com.suryashakti.solarmonitor.BuildConfig
import com.suryashakti.solarmonitor.R
import com.suryashakti.solarmonitor.databinding.FragmentTipsBinding
import kotlinx.coroutines.launch

class TipsFragment : Fragment() {

    private var _b: FragmentTipsBinding? = null
    private val b get() = _b!!

    override fun onCreateView(i: LayoutInflater, c: ViewGroup?, s: Bundle?): View {
        _b = FragmentTipsBinding.inflate(i, c, false); return b.root
    }

    override fun onViewCreated(view: View, s: Bundle?) {
        super.onViewCreated(view, s)

        b.btnAnalyze.setOnClickListener {
            b.btnAnalyze.isEnabled = false
            b.progressBar.visibility = View.VISIBLE
            b.llTips.removeAllViews()
            fetchTips()
        }
    }

    private fun fetchTips() {
        val prompt = """
            You are a solar energy advisor for rural India. Based on this 30-day data:
            - Avg daily generation: 6.6 kWh, Avg consumption: 5.4 kWh
            - Heavy appliances (AC, washing machine) used in evenings 7-10 PM
            - Grid dependency: 12%, Exported: 36 kWh, Cloudy days: 6
            - Per unit rate: ₹8/kWh
            
            Give exactly 4 short energy-saving tips. For each tip use this format:
            TITLE: <short title>
            TIP: <2 sentences of practical advice>
            SAVING: ₹<amount>/month
            
            Separate each tip with a blank line.
        """.trimIndent()

        viewLifecycleOwner.lifecycleScope.launch {
            try {
                val model = GenerativeModel(
                    modelName = "gemini-pro",
                    apiKey    = BuildConfig.GEMINI_API_KEY
                )
                val response = model.generateContent(prompt)
                parseTips(response.text ?: "No response received.")
            } catch (e: Exception) {
                showError("Gemini API error: ${e.message}\n\nMake sure GEMINI_API_KEY is set in local.properties")
            } finally {
                b.btnAnalyze.isEnabled = true
                b.progressBar.visibility = View.GONE
            }
        }
    }

    private fun parseTips(raw: String) {
        val blocks = raw.trim().split("\n\n").filter { it.contains("TITLE:") }
        if (blocks.isEmpty()) { addTipCard("AI Response", raw, ""); return }
        blocks.forEach { block ->
            val title  = block.lines().find { it.startsWith("TITLE:") }?.removePrefix("TITLE:")?.trim() ?: ""
            val tip    = block.lines().find { it.startsWith("TIP:") }?.removePrefix("TIP:")?.trim() ?: ""
            val saving = block.lines().find { it.startsWith("SAVING:") }?.removePrefix("SAVING:")?.trim() ?: ""
            addTipCard(title, tip, saving)
        }
    }

    private fun addTipCard(title: String, tip: String, saving: String) {
        val inflater = LayoutInflater.from(requireContext())
        val card = inflater.inflate(R.layout.item_tip_card, b.llTips, false)
        card.findViewById<TextView>(R.id.tv_tip_title).text = title
        card.findViewById<TextView>(R.id.tv_tip_body).text = tip
        val tvSave = card.findViewById<TextView>(R.id.tv_tip_saving)
        if (saving.isNotEmpty()) { tvSave.text = "💰 $saving"; tvSave.visibility = View.VISIBLE }
        else tvSave.visibility = View.GONE
        b.llTips.addView(card)
    }

    private fun showError(msg: String) = addTipCard("⚠ Error", msg, "")

    override fun onDestroyView() { super.onDestroyView(); _b = null }
}
