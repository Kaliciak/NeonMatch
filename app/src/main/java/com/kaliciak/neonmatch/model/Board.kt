package com.kaliciak.neonmatch.model

class Board(val height: Int, val width: Int, val blockfabric: BlockFabric) {
    var blocks = Array<Array<Block>>(height) {Array<Block>(width) {blockfabric.newBlock()} }

    fun getBlock(x: Int, y: Int): Block {
        return blocks[x][y]
    }
}