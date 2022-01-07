package com.kaliciak.neonmatch

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.core.content.ContextCompat

class TestView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    val testPaint = Paint(0).apply {
        color = ContextCompat.getColor(context, R.color.temporary_purple)
    }

    var size = 10f

    init {
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.TestView,
            0, 0).apply {
            try {
                size = getFloat(R.styleable.TestView_dotSize, 10f)
            } finally {
                recycle()
            }
        }
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        canvas?.apply {
            drawCircle(20 + size, 20 + size, size, testPaint)
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        size *= 1.1f
        invalidate()
        return super.onTouchEvent(event)
    }
}