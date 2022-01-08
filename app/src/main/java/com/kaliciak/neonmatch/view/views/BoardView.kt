package com.kaliciak.neonmatch.view.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import androidx.core.content.ContextCompat
import com.kaliciak.neonmatch.R
import com.kaliciak.neonmatch.model.Block
import com.kaliciak.neonmatch.model.Board
import com.kaliciak.neonmatch.view.GameScreen
import kotlin.math.floor

class BoardView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    lateinit var board: Board
    lateinit var viewDelegate: GameScreen

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        val blockWidth = width / board.width
        val blockHeight = height / board.height

        canvas?.apply {
            for ((i, column) in board.blocks.withIndex()) {
                for((j, block) in column.withIndex()) {
                    val xPos = (blockWidth * i).toFloat()
                    val yPos = (blockHeight * j).toFloat()

                    drawRect(xPos, yPos, xPos + blockWidth, yPos + blockHeight, getPaint(block))
                }
            }
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        super.onTouchEvent(event)


        when (event?.action) {
           MotionEvent.ACTION_DOWN -> {
               val blockCoords = getBlockCoords(Pair(event.x, event.y))
               viewDelegate.pressedBlock(blockCoords)
           }

           MotionEvent.ACTION_UP -> {
               val blockCoords = getBlockCoords(Pair(event.x, event.y))
               viewDelegate.releasedBlock(blockCoords)
           }
        }

        return true
    }

    private fun getPaint(type: Block): Paint {
        val blockColor = when (type) {
            Block.RED -> ContextCompat.getColor(context, R.color.blockRed)
            Block.GREEN -> ContextCompat.getColor(context, R.color.blockGreen)
            Block.BLUE -> ContextCompat.getColor(context, R.color.blockBlue)
            Block.YELLOW -> ContextCompat.getColor(context, R.color.blockYellow)
            Block.ORANGE -> ContextCompat.getColor(context, R.color.blockOrange)
            Block.CYAN -> ContextCompat.getColor(context, R.color.blockCyan)
            else -> ContextCompat.getColor(context, R.color.white)
        }
        return Paint(0).apply { color = blockColor }
    }

    private fun getBlockCoords(screenCoords: Pair<Float, Float>): Pair<Int, Int> {
        val blockHeight = height / board.height
        val blockWidth = width / board.width

        val blockX = floor(screenCoords.first / blockWidth).toInt()
        val blockY = floor(screenCoords.second / blockHeight).toInt()

        return Pair(blockX, blockY)
    }
}