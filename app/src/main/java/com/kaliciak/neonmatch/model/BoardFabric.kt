package com.kaliciak.neonmatch.model

class BoardFabric(private val width: Int, private val height: Int, blockTypesCount: Int) {
    private val blockFabric: BlockFabric

    init {
        val blockTypes: MutableList<Block>

        if (blockTypesCount > Block.values().size) {
            blockTypes = Block.values().toMutableList()
        }
        else {
            blockTypes = mutableListOf()
            while (blockTypes.size < blockTypesCount) {
                val block = Block.values().random()
                if (!blockTypes.contains(block)) {
                    blockTypes.add(block)
                }
            }
        }

        blockFabric = BlockFabric(blockTypes.toList())
    }

    fun newBoard(): Board {
        var board: Board
        do {
            board = Board(width, height, blockFabric)
        } while (!board.canMatch() || board.hasMatch())

        return board
    }
}