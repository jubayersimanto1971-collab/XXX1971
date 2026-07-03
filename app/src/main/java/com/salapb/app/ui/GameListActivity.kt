package com.salapb.app.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.salapb.app.R
import com.salapb.app.adapter.GameAdapter
import com.salapb.app.model.Game
import com.salapb.app.service.BotFloatingService

class GameListActivity : AppCompatActivity() {

    private lateinit var gameAdapter: GameAdapter
    private val games = mutableListOf<Game>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_list)
        
        val gamesRecyclerView = findViewById<RecyclerView>(R.id.gamesRecyclerView)
        val addGameButton = findViewById<Button>(R.id.addGameButton)
        val logoutButton = findViewById<Button>(R.id.logoutButton)
        
        gameAdapter = GameAdapter(games) { game ->
            val intent = Intent(this, GameWebViewActivity::class.java)
            intent.putExtra("gameUrl", game.url)
            intent.putExtra("gameName", game.name)
            startActivity(intent)
        }
        
        gamesRecyclerView.layoutManager = LinearLayoutManager(this)
        gamesRecyclerView.adapter = gameAdapter
        
        addGameButton.setOnClickListener {
            startActivity(Intent(this, AddGameActivity::class.java))
        }
        
        logoutButton.setOnClickListener {
            val sharedPref = getSharedPreferences("SalaPB", MODE_PRIVATE)
            with(sharedPref.edit()) {
                putBoolean("isLoggedIn", false)
                clear()
                apply()
            }
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
        
        loadGames()
        startBotService()
    }

    private fun loadGames() {
        games.clear()
        games.add(Game("Mines", "https://ck44.com"))
        games.add(Game("Aviator", "https://299bet.com"))
        games.add(Game("Coin Toss", "https://499bet.com"))
        games.add(Game("Roulette", "https://3333bet.com"))
        games.add(Game("Andar Bahar", "https://eg33bet.com"))
        gameAdapter.notifyDataSetChanged()
    }

    private fun startBotService() {
        val intent = Intent(this, BotFloatingService::class.java)
        startService(intent)
    }
}
