package com.kaliciak.neonmatch.viewModel

import com.kaliciak.neonmatch.model.Board
import com.kaliciak.neonmatch.model.BoardFabric
import com.kaliciak.neonmatch.view.GameScreen

class GameScreenViewModel {
    private val boardFabric = BoardFabric(6, 8, 6)
    var board: Board
        private set

    var blockPath: MutableList<Pair<Int, Int>> = mutableListOf()

    lateinit var delegate: GameScreen

    init {
        board = boardFabric.newBoard()
    }

    fun movePath() {
        var firstBlock = blockPath.first()
        for(block in blockPath) {
            board.swapBlocks(firstBlock, block)
            firstBlock = block
        }
    }

    fun releasedBlock(blockCoords: Pair<Int, Int>) {
        touchedBlock(blockCoords)

        // Released in proper area and touched more than 2 blocks
        if (blockPath.last() == blockCoords && blockPath.size >= 2) {
            movePath()
        }
        blockPath.clear()
        delegate.refreshBoardView()
    }

    fun touchedBlock(blockCoords: Pair<Int, Int>) {
        if (!board.validateBlockCoords(blockCoords)) {
            return
        }

        if (blockPath.contains(blockCoords)) {
            blockPath = blockPath.subList(0, blockPath.indexOf(blockCoords))
        }
        if (blockPath.isEmpty() || areNeighbours(blockPath.last(), blockCoords)) {
            blockPath.add(blockCoords)
        }
        delegate.refreshBoardView()
    }

    private fun areNeighbours(blockA: Pair<Int, Int>, blockB: Pair<Int, Int>): Boolean {
        return (blockA.first == blockB.first + 1 && blockA.second == blockB.second) ||
                (blockA.first == blockB.first - 1 && blockA.second == blockB.second) ||
                (blockA.first == blockB.first && blockA.second == blockB.second + 1) ||
                (blockA.first == blockB.first && blockA.second == blockB.second - 1)
    }
}