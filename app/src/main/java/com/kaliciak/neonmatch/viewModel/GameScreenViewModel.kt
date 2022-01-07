package com.kaliciak.neonmatch.viewModel

import com.kaliciak.neonmatch.model.BlockFabric
import com.kaliciak.neonmatch.model.Board

class GameScreenViewModel {
    var board = Board(5, 5, BlockFabric())
        private set
}