package com.kaliciak.neonmatch.view


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kaliciak.neonmatch.databinding.ActivityGameScreenBinding
import com.kaliciak.neonmatch.model.BlockFabric
import com.kaliciak.neonmatch.model.Board

private lateinit var binding: ActivityGameScreenBinding

class GameScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityGameScreenBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.boardView.board = Board(6, 3, BlockFabric())
    }
}