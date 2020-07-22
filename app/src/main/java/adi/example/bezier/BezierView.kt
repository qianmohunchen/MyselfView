package adi.example.bezier

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

/**
 * 在Kotlin中@JvmOverloads注解的作用就是：在有默认参数值的方法中使用@JvmOverloads注解，则Kotlin就会暴露多个重载方法。
 */

class BezierView @JvmOverloads constructor(
    context: Context?,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0
) :
    View(context, attributeSet, defStyleAttr) {

    private lateinit var mPaint: Paint

    private var startX: Float = 0.0f
    private var startY: Float = 0.0f
    private var endX: Float = 0.0f
    private var endY: Float = 0.0f
    private var eventX: Float = 0.0f
    private var eventY: Float = 0.0f
    private var centerX: Float = 0.0f
    private var centerY: Float = 0.0f

    init {
        mPaint = Paint()
        mPaint.isAntiAlias = true
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        centerX = (w / 2).toFloat()
        centerY = (h / 2).toFloat()
        startX = (centerX - 250).toFloat()
        startY = centerY.toFloat()
        endX = (centerX + 250).toFloat()
        endY = centerY.toFloat()
        eventX = centerX.toFloat()
        eventY = (centerY - 250).toFloat()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.let {
            canvas.drawCircle(startX, startY, 8.0f, mPaint)
            canvas.drawCircle(endX, endY, 8.0f, mPaint)
            canvas.drawCircle(eventX, eventY, 8.0f, mPaint)

            //绘制连线
            mPaint.setStrokeWidth(3.0f)
            canvas.drawLine(startX, centerY, eventX, eventY, mPaint)
            canvas.drawLine(endX, centerY, eventX, eventY, mPaint)
            //画二阶赛贝尔曲线
            mPaint.setColor(Color.GREEN)
            var path = Path()
            path.moveTo(startX, startY)
            path.quadTo(eventX, eventY, endX, endY)
            canvas.drawPath(path, mPaint)
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when (event?.action) {
            MotionEvent.ACTION_MOVE -> {
                eventX = event.getX()
                eventY = event.getY()
                invalidate()
            }
        }
        return true
    }
}