package com.salapb.app.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Check if user is logged in
        val sharedPref = getSharedPreferences("SalaPB", MODE_PRIVATE)
        val isLoggedIn = sharedPref.getBoolean("isLoggedIn", false)
        
        if (isLoggedIn) {
            // Go to game list
            startActivity(Intent(this, GameListActivity::class.java))
            finish()
        } else {
            // Go to login
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}
