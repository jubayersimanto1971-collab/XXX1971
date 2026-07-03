package com.salapb.app.ui

import android.os.Bundle
import android.webkit.WebViewClient
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity
import com.salapb.app.R

class GameWebViewActivity : AppCompatActivity() {

    private lateinit var gameWebView: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_webview)
        
        gameWebView = findViewById(R.id.gameWebView)
        
        val gameUrl = intent.getStringExtra("gameUrl") ?: "https://ck44.com"
        val gameName = intent.getStringExtra("gameName") ?: "Game"
        
        title = gameName
        
        gameWebView.apply {
            settings.apply {
                javaScriptEnabled = true
                domStorageEnabled = true
                databaseEnabled = true
            }
            webViewClient = WebViewClient()
            loadUrl(gameUrl)
        }
    }

    override fun onBackPressed() {
        if (gameWebView.canGoBack()) {
            gameWebView.goBack()
        } else {
            super.onBackPressed()
        }
    }
}
