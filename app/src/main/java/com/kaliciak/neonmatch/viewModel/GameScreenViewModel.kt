package com.kaliciak.neonmatch.viewModel

import com.kaliciak.neonmatch.model.Board
import com.kaliciak.neonmatch.model.BoardFabric
import com.kaliciak.neonmatch.view.GameScreen

class GameScreenViewModel {
    private val boardFabric = BoardFabric(6, 8, 6)
    var board: Board
        private set

    var firstBlockCoords: Pair<Int, Int>? = null
    var lastBlockCoords: Pair<Int, Int>? = null
    var touchedBlocks: MutableList<Pair<Int, Int>> = mutableListOf()

    lateinit var delegate: GameScreen

    init {
        board = boardFabric.newBoard()
    }

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