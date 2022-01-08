package com.kaliciak.neonmatch.model

import kotlin.random.Random

class BlockFabric {
    fun newBlock(): Block {
        return when (Random.nextInt(0, 6)) {
            0 -> Block.RED
            1 -> Block.GREEN
            2 -> Block.BLUE
            3 -> Block.YELLOW
            4 -> Block.ORANGE
            else -> Block.CYAN
        }
    }
}