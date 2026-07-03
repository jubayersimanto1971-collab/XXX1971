package com.salapb.app.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.salapb.app.R
import com.salapb.app.model.Game

class GameAdapter(
    private val games: List<Game>,
    private val onGameClick: (Game) -> Unit
) : RecyclerView.Adapter<GameAdapter.GameViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_game, parent, false)
        return GameViewHolder(view, onGameClick)
    }

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        holder.bind(games[position])
    }

    override fun getItemCount() = games.size

    class GameViewHolder(
        private val view: android.view.View,
        private val onGameClick: (Game) -> Unit
    ) : RecyclerView.ViewHolder(view) {

        fun bind(game: Game) {
            val gameNameText = view.findViewById<TextView>(R.id.gameNameText)
            val gameUrlText = view.findViewById<TextView>(R.id.gameUrlText)
            
            gameNameText.text = game.name
            gameUrlText.text = game.url
            
            view.setOnClickListener {
                onGameClick(game)
            }
        }
    }
}
