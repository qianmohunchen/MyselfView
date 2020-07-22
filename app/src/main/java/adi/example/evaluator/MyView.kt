package adi.example.evaluator

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class MyView @JvmOverloads constructor(
    context: Context?,
    attr: AttributeSet?,
    defStyle: Int = 0
) :
    View(context, attr, defStyle) {
    // 设置需要用到的变量
    val RADIUS = 70f// 圆的半径 = 70
    var currentPoint: Point? = null // 当前点坐标
    var mPaint: Paint// 绘图画笔
    var mTxtPaint: Paint// 绘图画笔
    /**
     * 字体测量
     */
    private var mFontMetrics: Paint.FontMetrics? = null

    init {
        mPaint = Paint()
        mPaint.isAntiAlias = true
        mPaint.setColor(Color.BLUE)

        mTxtPaint = Paint()
        mTxtPaint.isAntiAlias = true
        mTxtPaint.setColor(Color.YELLOW)
        mTxtPaint.textSize = 28f
        mFontMetrics = mTxtPaint.fontMetrics
        mTxtPaint.textAlign=Paint.Align.CENTER
    }

    override fun onDraw(canvas: Canvas?) {
        drawCircle(canvas)
        drawText(canvas)
    }

    private fun drawText(canvas: Canvas?) {

        var baseLine = RADIUS - mFontMetrics?.bottom!!.toInt()/2 - mFontMetrics?.top!!.toInt()/2
        canvas?.drawText("周德群", RADIUS, baseLine, mTxtPaint)
    }

    private fun drawCircle(canvas: Canvas?) {
        if (currentPoint == null) {
            currentPoint = Point(RADIUS, RADIUS)

            // 在该点画一个圆:圆心 = (70,70),半径 = 70
            var x = currentPoint!!.x
            var y = currentPoint!!.y
            canvas?.drawCircle(x, y, RADIUS, mPaint)
// 步骤1:创建初始动画时的对象点  & 结束动画时的对象点
            var startPoint = Point(RADIUS, RADIUS)// 初始点为圆心(70,70)
            var endPoint = Point(700f, 1000f)// 结束点为(700,1000)

            ValueAnimator.ofObject(PointEvaluator(), startPoint, endPoint).let {
                it.duration = 5000
                it.repeatCount = -1
                it.addUpdateListener { animation: ValueAnimator ->
                    currentPoint = animation.getAnimatedValue() as Point
                    invalidate()
                }
                it.start()
            }
        } else {
            // 在该点画一个圆:圆心 = (30,30),半径 = 30
            var x = currentPoint!!.x
            var y = currentPoint!!.y
            canvas?.drawCircle(x, y, RADIUS, mPaint)

        }
    }
}