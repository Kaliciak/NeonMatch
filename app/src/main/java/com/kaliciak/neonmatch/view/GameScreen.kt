package com.kaliciak.neonmatch.view


import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import androidx.appcompat.app.AppCompatActivity
import com.kaliciak.neonmatch.databinding.ActivityGameScreenBinding
import com.kaliciak.neonmatch.model.BlockFabric
import com.kaliciak.neonmatch.model.Board
import com.kaliciak.neonmatch.viewModel.GameScreenViewModel
import kotlinx.coroutines.selects.select
import kotlin.math.log

private lateinit var binding: ActivityGameScreenBinding

class GameScreen : AppCompatActivity() {
    val viewModel: GameScreenViewModel = GameScreenViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityGameScreenBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        viewModel.delegate = this

        binding.boardView.viewDelegate = this
        binding.boardView.board = viewModel.board


    }

    fun refreshBoardView() {
        binding.boardView.board = viewModel.board
        binding.boardView.invalidate()
    }

    fun pressedBlock(blockCoords: Pair<Int, Int>) {
        viewModel.pressedBlock(blockCoords)
    }

    fun releasedBlock(blockCoords: Pair<Int, Int>) {
        viewModel.releasedBlock(blockCoords)
    }
}