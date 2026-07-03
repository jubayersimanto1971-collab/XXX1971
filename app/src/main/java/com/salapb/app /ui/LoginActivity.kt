package com.salapb.app.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.salapb.app.R

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        
        val usernameInput = findViewById<EditText>(R.id.usernameInput)
        val passwordInput = findViewById<EditText>(R.id.passwordInput)
        val loginButton = findViewById<Button>(R.id.loginButton)
        
        loginButton.setOnClickListener {
            val username = usernameInput.text.toString().trim()
            val password = passwordInput.text.toString().trim()
            
            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "ইউজারনেম এবং পাসওয়ার্ড প্রবেশ করুন", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            
            if (username.length >= 4 && password.length >= 6) {
                val sharedPref = getSharedPreferences("SalaPB", MODE_PRIVATE)
                with(sharedPref.edit()) {
                    putBoolean("isLoggedIn", true)
                    putString("username", username)
                    apply()
                }
                
                Toast.makeText(this, "লগইন সফল!", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, GameListActivity::class.java))
                finish()
            } else {
                Toast.makeText(this, "ইউজারনেম ৪ এবং পাসওয়ার্ড ৬ অক্ষরের বেশি হতে হবে", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
