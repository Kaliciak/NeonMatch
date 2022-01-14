package com.kaliciak.neonmatch.view


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kaliciak.neonmatch.databinding.ActivityGameScreenBinding
import com.kaliciak.neonmatch.model.Board
import com.kaliciak.neonmatch.viewModel.GameScreenViewModel

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
        binding.boardView.dataProvider = this
        binding.boardView.board = viewModel.board
    }

    fun getBoard(): Board {
        return viewModel.board
    }

    fun getTouchedBlocks(): List<Pair<Int, Int>> {
        return viewModel.blockPath.toList()
    }

    fun refreshBoardView() {
        binding.boardView.board = viewModel.board
        binding.boardView.invalidate()
    }

    fun releasedBlock(blockCoords: Pair<Int, Int>) {
        viewModel.releasedBlock(blockCoords)
    }

    fun touchedBlock(blockCoords: Pair<Int, Int>) {
        viewModel.touchedBlock(blockCoords)
    }
}