package com.salapb.app.ui

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.salapb.app.R

class AddGameActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_game)
        
        val gameNameInput = findViewById<EditText>(R.id.gameNameInput)
        val gameUrlInput = findViewById<EditText>(R.id.gameUrlInput)
        val saveGameButton = findViewById<Button>(R.id.saveGameButton)
        
        saveGameButton.setOnClickListener {
            val gameName = gameNameInput.text.toString().trim()
            val gameUrl = gameUrlInput.text.toString().trim()
            
            if (gameName.isEmpty() || gameUrl.isEmpty()) {
                Toast.makeText(this, "সব ফিল্ড পূরণ করুন", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            
            val sharedPref = getSharedPreferences("SalaPB", MODE_PRIVATE)
            with(sharedPref.edit()) {
                putString("lastGameName", gameName)
                putString("lastGameUrl", gameUrl)
                apply()
            }
            
            Toast.makeText(this, "গেম যোগ হয়েছে!", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}
