package com.kaliciak.neonmatch.model

import kotlin.random.Random

class BlockFabric {
    fun newBlock(): Block {
        return when (Random.nextInt(0, 3)) {
            0 -> Block(BlockType.RED)
            1 -> Block(BlockType.GREEN)
            else -> Block(BlockType.BLUE)
        }
    }
}