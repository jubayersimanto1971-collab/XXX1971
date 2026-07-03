package com.salapb.app.ui

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.salapb.app.R

class BotDashboardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bot_dashboard)
        
        title = "বট ড্যাশবোর্ড"
        
        val totalPredictionsText = findViewById<TextView>(R.id.totalPredictionsText)
        val lastPredictionText = findViewById<TextView>(R.id.lastPredictionText)
        val botStatusText = findViewById<TextView>(R.id.botStatusText)
        
        val predictions = loadPredictions()
        
        totalPredictionsText.text = "মোট: ${predictions.size}"
        lastPredictionText.text = if (predictions.isNotEmpty()) 
            "সর্বশেষ: ${predictions.last()}" 
            else "কোন প্রেডিকশন নেই"
        botStatusText.text = "সক্রিয় 🟢"
    }

    private fun loadPredictions(): List<String> {
        val sharedPref = getSharedPreferences("SalaPB", MODE_PRIVATE)
        return emptyList()
    }
}
