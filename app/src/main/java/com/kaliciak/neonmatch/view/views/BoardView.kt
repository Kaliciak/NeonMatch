package com.kaliciak.neonmatch.view.views

import android.annotation.SuppressLint
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
import kotlin.math.min

class BoardView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    lateinit var board: Board
    var touchedBlocks: List<Pair<Int, Int>> = mutableListOf()
    lateinit var dataProvider: GameScreen
    lateinit var viewDelegate: GameScreen

    private val blockPaints: Map<Block, Paint>
    private val circlePaint: Paint

    init {
        blockPaints = buildMap {
            for (block in Block.values()) {
                put(block, getPaint(block))
            }
        }
        circlePaint = Paint(0).apply {
            color = ContextCompat.getColor(context, R.color.temporary_purple)
        }
    }

    private fun getData() {
        board = dataProvider.getBoard()
        touchedBlocks = dataProvider.getTouchedBlocks()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        getData()

        val blockWidth = (width / board.width).toFloat()
        val blockHeight = (height / board.height).toFloat()

        canvas?.apply {
            for ((i, column) in board.blocks.withIndex()) {
                for((j, block) in column.withIndex()) {
                    val xPos = blockWidth * i
                    val yPos = blockHeight * j

                    drawRect(xPos, yPos, xPos + blockWidth, yPos + blockHeight, getPaint(block))
                }
            }

            for (blockCoords in touchedBlocks) {
                val xPos = blockWidth * blockCoords.first
                val yPos = blockHeight * blockCoords.second

                drawCircle(xPos + blockWidth / 2, yPos + blockHeight / 2, min(blockWidth, blockHeight) / 4, circlePaint)
            }
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        super.onTouchEvent(event)

        when (event?.action) {
           MotionEvent.ACTION_MOVE -> {
               val blockCoords = getBlockCoords(Pair(event.x, event.y))
               viewDelegate.touchedBlock(blockCoords)
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
            Block.PURPLE -> ContextCompat.getColor(context, R.color.blockPurple)
//            else -> ContextCompat.getColor(context, R.color.white)
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