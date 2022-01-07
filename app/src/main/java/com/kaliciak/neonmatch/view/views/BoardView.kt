package com.kaliciak.neonmatch.view.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat
import com.kaliciak.neonmatch.R
import com.kaliciak.neonmatch.model.BlockType
import com.kaliciak.neonmatch.model.Board

class BoardView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    lateinit var board: Board

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        val blockHeight = height / board.height
        val blockWidth = width / board.width

        canvas?.apply {
            for ((i, row) in board.blocks.withIndex()) {
                for((j, block) in row.withIndex()) {
                    val xPos = (blockWidth * j).toFloat()
                    val yPos = (blockHeight * i).toFloat()

                    drawRect(xPos, yPos, xPos + blockWidth, yPos + blockHeight, getPaint(block.type))
                }
            }
        }
    }

    private fun getPaint(type: BlockType): Paint {
        val blockColor = when (type) {
            BlockType.RED -> ContextCompat.getColor(context, R.color.blockRed)
            BlockType.GREEN -> ContextCompat.getColor(context, R.color.blockGreen)
            BlockType.BLUE -> ContextCompat.getColor(context, R.color.blockBlue)
        }
        return Paint(0).apply { color = blockColor }
    }
}