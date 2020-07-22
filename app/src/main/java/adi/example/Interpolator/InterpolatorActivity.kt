package adi.example.Interpolator

import adi.example.bezier.R
import android.animation.IntEvaluator
import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.animation.*
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_interpolator.*

/**
 * 差值器 业务场景主要是为动画实现非线性运动
 */
class InterpolatorActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_interpolator)

        start_btn.setOnClickListener {
            start_btn.startAnimation(overshootInterpolator())
            txt1.startAnimation(accelerateInterpolator())
            txt2.startAnimation(overshootInterpolator2())
            txt3.startAnimation(accelerateDecelerateInterpolator())
            txt4.startAnimation(anticipateInterpolator())
            txt5.startAnimation(anticipateOvershootInterpolator())
            txt6.startAnimation(bounceInterpolator())
            txt7.startAnimation(cycleInterpolator())
            txt8.startAnimation(decelerateAccelerateInterpolator())
        }
    }

    fun overshootInterpolator(): Animation {
        var alphaAnimation = AlphaAnimation(1.0f, 0f)
        alphaAnimation.duration = 3000
        alphaAnimation.interpolator = OvershootInterpolator()
        return alphaAnimation
    }

    /**
     * 动画加速进行
     */
    fun accelerateInterpolator(): Animation {
        var translateAnimation = TranslateAnimation(0f, 600f, 0f, 0f)
        translateAnimation.duration = 3000
        translateAnimation.interpolator = AccelerateInterpolator()
        return translateAnimation
    }

    /**
     * 快速完成动画，超出再回到结束样式
     */
    fun overshootInterpolator2(): Animation {
        var translateAnimation = TranslateAnimation(0f, 600f, 0f, 0f)
        translateAnimation.duration = 3000
        translateAnimation.interpolator = OvershootInterpolator()
        return translateAnimation
    }

    /**
     * 先加速再减速
     */
    fun accelerateDecelerateInterpolator(): Animation {
        var translateAnimation = TranslateAnimation(0f, 600f, 0f, 0f)
        translateAnimation.duration = 3000
        translateAnimation.interpolator = AccelerateDecelerateInterpolator()
        return translateAnimation
    }

    /**
     * 先退后再加速前进
     */
    fun anticipateInterpolator(): Animation {
        var translateAnimation = TranslateAnimation(0f, 600f, 0f, 0f)
        translateAnimation.duration = 3000
        translateAnimation.interpolator = AnticipateInterpolator()
        return translateAnimation
    }

    /**
     * 先退后再加速前进，超出终点后再回终点
     */
    fun anticipateOvershootInterpolator(): Animation {
        var translateAnimation = TranslateAnimation(0f, 800f, 0f, 0f)
        translateAnimation.duration = 3000
        translateAnimation.interpolator = AnticipateOvershootInterpolator()
        return translateAnimation
    }

    /**
     * 最后阶段弹球效果
     */
    fun bounceInterpolator(): Animation {
        var translateAnimation = TranslateAnimation(0f, 600f, 0f, 0f)
        translateAnimation.duration = 3000
        translateAnimation.interpolator = BounceInterpolator()
        return translateAnimation
    }

    /**
     * 周期运动
     */
    fun cycleInterpolator(): Animation {
        var translateAnimation = TranslateAnimation(0f, 600f, 0f, 0f)
        translateAnimation.duration = 3000
        translateAnimation.interpolator = CycleInterpolator(3.0f)
        return translateAnimation
    }

    /**
     * 先减速后加速
     */
    fun decelerateAccelerateInterpolator(): Animation {
        var translateAnimation = TranslateAnimation(0f, 600f, 0f, 0f)
        translateAnimation.duration = 3000
        translateAnimation.interpolator = DecelerateAccelerateInterpolator()
        return translateAnimation

        val curTranslationX = txt8.translationX;

        // 获得当前按钮的位置
        ObjectAnimator.ofFloat(txt8, "translationX", curTranslationX, 300f, curTranslationX).also {
            // 创建动画对象 & 设置动画
            // 表示的是:
            // 动画作用对象是mButton
            // 动画作用的对象的属性是X轴平移
            // 动画效果是:从当前位置平移到 x=1500 再平移到初始位置
            it.setDuration(5000)
            it.setInterpolator(DecelerateAccelerateInterpolator())
            // 设置插值器
            it.start()
        }


    }

}
