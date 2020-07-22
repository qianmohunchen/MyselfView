package adi.example.bezier

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

/**
 * 三阶赛贝尔曲线
 * 在Kotlin中@JvmOverloads注解的作用就是：在有默认参数值的方法中使用@JvmOverloads注解，则Kotlin就会暴露多个重载方法。
 */

class ThreeBezierView @JvmOverloads constructor(
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
    private var leftX: Float = 0.0f
    private var leftY: Float = 0.0f
    private var rightX: Float = 0.0f
    private var rightY: Float = 0.0f

    private var centerX: Float = 0.0f
    private var centerY: Float = 0.0f

    private var isMoveLeft: Boolean = true

    init {
        mPaint = Paint()
        mPaint.isAntiAlias = true
    }

//    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
//        setMeasuredDimension(measureWidth(widthMeasureSpec), heightMeasureSpec)
//    }


    /**
     * 测量宽度
     *
     * @param widthMeasureSpec
     * @return
     */
    fun measureWidth(widthMeasureSpec: Int): Int {
        val size = MeasureSpec.getSize(widthMeasureSpec)
        val mode = MeasureSpec.getMode(widthMeasureSpec)
        var result = size
        when (mode) {
            MeasureSpec.AT_MOST -> {
                result = Math.min(width, size)
            }
            MeasureSpec.UNSPECIFIED ->
                result = 1 + paddingLeft + paddingRight
            MeasureSpec.EXACTLY ->
                result = size
        }
        return result
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        centerX = (w / 2).toFloat()
        centerY = (h / 2).toFloat()
        startX = (centerX - 250).toFloat()
        startY = centerY.toFloat()
        endX = (centerX + 250).toFloat()
        endY = centerY.toFloat()
        leftX = centerX.toFloat()
        leftY = (centerY - 250).toFloat()
        rightX = endX
        rightY = (centerY - 250).toFloat()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        var w = width
        var h = height
        canvas?.let {
            canvas.drawCircle(startX, startY, 8.0f, mPaint)
            canvas.drawCircle(endX, endY, 8.0f, mPaint)
            canvas.drawCircle(leftX, leftY, 8.0f, mPaint)
            canvas.drawCircle(rightX, rightY, 8.0f, mPaint)
            //绘制连线
            mPaint.setStrokeWidth(3.0f)
            canvas.drawLine(startX, startY, leftX, leftY, mPaint)
            canvas.drawLine(leftX, leftY, rightX, rightY, mPaint)
            canvas.drawLine(rightX, rightY, endX, endY, mPaint)
            //画二阶赛贝尔曲线
//            mPaint.setColor(Color.GREEN)
            mPaint.setStyle(Paint.Style.STROKE)
            var path = Path()
            path.moveTo(startX, startY)
            path.cubicTo(leftX, leftY, rightX, rightY, endX, endY)
            canvas.drawPath(path, mPaint)
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when (event?.action) {
            MotionEvent.ACTION_MOVE -> {
                if (isMoveLeft) {
                    leftX = event.getX()
                    leftY = event.getY()
                } else {
                    rightX = event.getX()
                    rightY = event.getY()
                }
                invalidate()
            }
        }
        return true
    }

    fun isMoveLeft() {
        isMoveLeft = true
    }

    fun isMoveRight() {
        isMoveLeft = false
    }

}