package adi.example.path

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View

class PathView @JvmOverloads constructor(context: Context, attr: AttributeSet, defStyle: Int = 0) :
    View(context, attr, defStyle) {

    var paint: Paint? = null

    init {
        paint = Paint()
        paint?.let {
            it.isAntiAlias = true
            it.setColor(Color.GREEN)
            it.style = Paint.Style.STROKE
        }
    }


    override fun onDraw(canvas: Canvas?) {
        var path = Path()
        var circlePath = Path()
        var centerX = width / 2.toFloat()
        var centerY = height / 2.toFloat()
        var curR = 100f
        var angle = 2 * Math.PI / 6
        canvas?.let { canvas ->
//            canvas.translate(width / 2.toFloat(), height / 2.toFloat())
//            path.lineTo(200f, 200f)
//            path.lineTo(200f, 0f)
//            path.close()
//            path.moveTo(300f,300f)
//            path.lineTo(300f,0f)
//
//            path.addCircle(300f,300f,150f,Path.Direction.CW)
//
//            path.addRect(-200f,-200f,200f,200f,Path.Direction.CW)
//            path.setLastPoint(-300f,300f)
            //翻转Y轴
//            canvas.scale(1f,-1f)
//            path.addRect(-200f,-200f,200f,200f,Path.Direction.CW)
//            circlePath.addCircle(0f,200f,100f,Path.Direction.CCW)
//            path.addPath(circlePath)

            //画弧
//            path.addRect(0f,0f,200f,200f,Path.Direction.CW)
//            path.addArc(0f,0f,200f,200f,0f,270f)

            for (j in 0..5) {
                if (j == 0) {
                    path.moveTo(centerX + curR, centerY)
                } else {
                    //根据半径，计算出蜘蛛丝上每个点的坐标
                    var x = (centerX + curR * Math.cos(angle * j)).toFloat()
                    var y = (centerY + curR * Math.sin(angle * j)).toFloat()
                    path.lineTo(x, y)
                }
                curR += 20
            }
            path.close()//闭合路径
            canvas.drawPath(path, paint!!)
        }

    }
}