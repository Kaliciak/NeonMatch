package com.kaliciak.neonmatch.model

class BlockFabric(private val availableBlocks: List<Block>) {

    fun newBlock(): Block {
        return availableBlocks.random()
    }
}