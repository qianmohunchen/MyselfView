package adi.example.bezier

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View

class RoundRectView @JvmOverloads constructor(
    context: Context? = null,
    attr: AttributeSet? = null,
    defStyle: Int = 0
) :
    View(context, attr, defStyle) {
    private lateinit var mPaint: Paint

    init {
        mPaint = Paint()
        mPaint.isAntiAlias = true
    }

    override fun onDraw(canvas: Canvas?) {
        var rect = RectF(30f, 30f, 200f, 200f)
        canvas?.drawRoundRect(rect, 10f, 20f, mPaint)
    }
}