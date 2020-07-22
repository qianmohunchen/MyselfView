package adi.example.path

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View


class RadarView @JvmOverloads constructor(context: Context, attr: AttributeSet, defStyle: Int = 0) :
    View(context, attr, defStyle) {

    val count: Int = 6
    var paint: Paint? = null
    var radius: Float = 0f
    var centerX: Float = 0f
    var centerY: Float = 0f
    var angle = 2 * Math.PI / 6

    var path = Path()

    init {
        paint = Paint()
        paint?.let {
            it.isAntiAlias = true
            it.setColor(Color.GREEN)
            it.style = Paint.Style.STROKE
        }
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        radius = Math.min(h, w) / 2 * 0.9f
        //中心坐标
        centerX = (w / 2).toFloat()
        centerY = (h / 2).toFloat()
    }

    override fun onDraw(canvas: Canvas?) {

        drawRadar(canvas)
        drawLine(canvas)
    }

    fun drawRadar(canvas: Canvas?) {
        canvas?.let { canvas ->

            val r: Float = radius / (count - 1) //r是蜘蛛丝之间的间距
            for (i in 1..5) {
                var curR: Float = r * i //当前半径

                path.reset()
                for (j in 0..5) {
                    if (j == 0) {
                        path.moveTo(centerX + curR, centerY)
                    } else {
                        //根据半径，计算出蜘蛛丝上每个点的坐标
                        var x = (centerX + curR * Math.cos(angle * j)).toFloat()
                        var y = (centerY + curR * Math.sin(angle * j)).toFloat()
                        path.lineTo(x, y)
                    }
                }
                path.close()//闭合路径
                canvas.drawPath(path, paint!!)
            }
        }
    }

    fun drawLine(canvas: Canvas?) {
        var path = Path()
        for (i in 0 until count) {
            path.reset()
            path.moveTo(centerX, centerY)
            var x = (centerX + radius * Math.cos(angle * i)).toFloat()
            var y = (centerY + radius * Math.sin(angle * i)).toFloat()
            path.lineTo(x, y)
            canvas!!.drawPath(path, paint!!)
        }
    }
}