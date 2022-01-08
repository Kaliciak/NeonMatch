package com.kaliciak.neonmatch.viewModel

import com.kaliciak.neonmatch.model.BlockFabric
import com.kaliciak.neonmatch.model.Board
import com.kaliciak.neonmatch.view.GameScreen

class GameScreenViewModel {
    var board = Board(6, 6, BlockFabric())
        private set

    var firstBlockCoords: Pair<Int, Int>? = null
    var lastBlockCoords: Pair<Int, Int>? = null

    lateinit var delegate: GameScreen

    fun pressedBlock(blockCoords: Pair<Int, Int>) {
        if (board.validateBlockCoords(blockCoords)) {
            firstBlockCoords = blockCoords
        }
    }

    fun releasedBlock(blockCoords: Pair<Int, Int>) {
        if (board.validateBlockCoords(blockCoords) && firstBlockCoords != null) {
            lastBlockCoords = blockCoords

            board.swapBlocks(firstBlockCoords!!, lastBlockCoords!!)
            delegate.refreshBoardView()
        }
        firstBlockCoords = null
        lastBlockCoords = null
    }
}