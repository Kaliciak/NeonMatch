package com.kaliciak.neonmatch.model

class Board(val width: Int, val height: Int, val blockfabric: BlockFabric) {
    // blocks[column][row]
    var blocks = Array<Array<Block>>(width) {Array<Block>(height) {blockfabric.newBlock()} }

    fun swapBlocks(aCoords: Pair<Int, Int>, bCoords: Pair<Int, Int>) {
        val temp = blocks[aCoords.first][aCoords.second]
        blocks[aCoords.first][aCoords.second] = blocks[bCoords.first][bCoords.second]
        blocks[bCoords.first][bCoords.second] = temp
    }

    fun validateBlockCoords(blockCoords: Pair<Int, Int>): Boolean {
        return (blockCoords.first in 0 until width && blockCoords.second in 0 until height)
    }

    fun hasMatch(): Boolean {
        var lastType: Block
        var combo: Int

        // By row
        for (row in 0 until height) {
            lastType = blocks[0][row]
            combo = 1
            for (column in 1 until width) {
                val nextType = blocks[column][row]
                if (nextType == lastType) {
                    combo += 1
                }
                else {
                    lastType = nextType
                    combo = 1
                }

                if (combo >= 3) {
                    return true
                }
            }
        }

        // By column
        for (column in 0 until width) {
            lastType = blocks[column][0]
            combo = 1
            for (row in 1 until height) {
                val nextType = blocks[column][row]
                if (nextType == lastType) {
                    combo += 1
                }
                else {
                    lastType = nextType
                    combo = 1
                }

                if (combo >= 3) {
                    return true
                }
            }
        }

        return false
    }

    fun canMatch(): Boolean {
        if (canMatchToHole() || canMatchToNeighbours()) {
            return true
        }
        return false
    }

    private fun canMatchToHole(): Boolean {
        val blockNumber = countBlocks()

        // By row
        for (row in 0 until height) {
            for (column in 0 until width - 2) {
                val firstBlock = blocks[column][row]
                val otherBlock = blocks[column + 2][row]

                if (firstBlock == otherBlock) {
                    if (blockNumber[firstBlock]!! > 2) {
                        return true
                    }
                }
            }
        }

        // By column
        for (column in 0 until width) {
            for (row in 0 until height - 2) {
                val firstBlock = blocks[column][row]
                val otherBlock = blocks[column][row + 2]

                if (firstBlock == otherBlock) {
                    if (blockNumber[firstBlock]!! > 2) {
                        return true
                    }
                }
            }
        }

        return false
    }

    private fun canMatchToNeighbours(): Boolean {
        val blockNumber = countBlocks()

        // By row
        for (row in 0 until height) {
            for (column in 0 until width - 1) {
                val firstBlock = blocks[column][row]
                val otherBlock = blocks[column + 1][row]

                if (firstBlock == otherBlock) {
                    if (blockNumber[firstBlock]!! > 2) {
                        return true
                    }
                }
            }
        }

        // By column
        for (column in 0 until width) {
            for (row in 0 until height - 1) {
                val firstBlock = blocks[column][row]
                val otherBlock = blocks[column][row + 1]

                if (firstBlock == otherBlock) {
                    if (blockNumber[firstBlock]!! > 2) {
                        return true
                    }
                }
            }
        }

        return false
    }

    private fun countBlocks(): Map<Block, Int> {
        val blockNumber: Map<Block, Int> = buildMap {
            for (column in blocks) {
                for (block in column) {
                    if (!keys.contains(block)) {
                        put(block, 0)
                    }
                    put(block, get(block)!! + 1)
                }
            }
        }

        return blockNumber
    }
}