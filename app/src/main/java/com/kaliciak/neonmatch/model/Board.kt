package com.kaliciak.neonmatch.model

class Board(val width: Int, val height: Int, val blockfabric: BlockFabric) {
    var blocks = Array<Array<Block>>(width) {Array<Block>(height) {blockfabric.newBlock()} }

    fun swapBlocks(aCoords: Pair<Int, Int>, bCoords: Pair<Int, Int>) {
        val temp = blocks[aCoords.first][aCoords.second]
        blocks[aCoords.first][aCoords.second] = blocks[bCoords.first][bCoords.second]
        blocks[bCoords.first][bCoords.second] = temp
    }

    fun validateBlockCoords(blockCoords: Pair<Int, Int>): Boolean {
        return (blockCoords.first in 0 until width && blockCoords.second in 0 until height)
    }
}