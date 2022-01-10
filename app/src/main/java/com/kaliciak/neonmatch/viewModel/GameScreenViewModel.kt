package com.kaliciak.neonmatch.viewModel

import com.kaliciak.neonmatch.model.BlockFabric
import com.kaliciak.neonmatch.model.Board
import com.kaliciak.neonmatch.view.GameScreen

class GameScreenViewModel {
    var board = Board(6, 6, BlockFabric())
        private set

    var firstBlockCoords: Pair<Int, Int>? = null
    var lastBlockCoords: Pair<Int, Int>? = null
    var touchedBlocks: MutableList<Pair<Int, Int>> = mutableListOf()

    lateinit var delegate: GameScreen

    fun pressedBlock(blockCoords: Pair<Int, Int>) {
        touchedBlock(blockCoords)

        if (board.validateBlockCoords(blockCoords)) {
            firstBlockCoords = blockCoords
        }
    }

    fun releasedBlock(blockCoords: Pair<Int, Int>) {
        touchedBlock(blockCoords)

        if (board.validateBlockCoords(blockCoords) && firstBlockCoords != null) {
            lastBlockCoords = blockCoords

            board.swapBlocks(firstBlockCoords!!, lastBlockCoords!!)
        }
        firstBlockCoords = null
        lastBlockCoords = null
        touchedBlocks.clear()
        delegate.refreshBoardView()
    }

    fun touchedBlock(blockCoords: Pair<Int, Int>) {
        if (touchedBlocks.isEmpty()) {
            touchedBlocks.add(blockCoords)
        }
        else {
            val lastBlock = touchedBlocks.last()
            if (lastBlock != blockCoords) {
                touchedBlocks.add(blockCoords)
            }
        }
        delegate.refreshBoardView()
    }
}