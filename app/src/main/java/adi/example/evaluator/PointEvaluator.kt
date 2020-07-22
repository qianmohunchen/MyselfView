package adi.example.evaluator

import android.animation.TypeEvaluator

class PointEvaluator : TypeEvaluator<Point> {
    override fun evaluate(fraction: Float, startValue: Point?, endValue: Point?): Point {
        val x = startValue!!.x + (endValue!!.x - startValue.x) * fraction
        val y = startValue.y + (endValue.y - startValue.y) * fraction
        return Point(x, y)
    }
}