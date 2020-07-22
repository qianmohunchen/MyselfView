package adi.example.Interpolator

import android.animation.TimeInterpolator
import android.util.Log
import android.view.animation.Interpolator

class DecelerateAccelerateInterpolator : Interpolator {
    override fun getInterpolation(input: Float): Float {
        var result = 0.0f
        Log.d("input", "input=" + input)
        if (input < 0.5) {
            result = (Math.sin(Math.PI * input) / 2).toFloat()

        } else {
            result = ((2.0f - (Math.sin(Math.PI * input)))/2).toFloat()
        }
        Log.d("result", "result=" + result)
        return result
    }
}