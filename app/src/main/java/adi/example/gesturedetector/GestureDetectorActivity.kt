package adi.example.gesturedetector

import adi.example.bezier.R
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.GestureDetector
import android.view.GestureDetector.OnGestureListener
import android.view.MotionEvent
import android.view.View
import android.widget.Scroller
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_gesturedetector.*


class GestureDetectorActivity : AppCompatActivity(), View.OnTouchListener {

    private lateinit var gestureDetector: GestureDetector

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gesturedetector)
        var scoller = Scroller(this)
        var mScrollerX = 0
        txt.setOnTouchListener(this)

        leftBtn.setOnClickListener {
            linear1.scrollBy(-20,-20)
        }
        rightBtn.setOnClickListener {
            linear1.scrollBy(20, 20)
        }
        object : Thread() {
            override fun run() {
                super.run()
                val handler = Handler(Looper.getMainLooper())
                gestureDetector =
                    GestureDetector(this@GestureDetectorActivity, object : OnGestureListener {
                        override fun onShowPress(e: MotionEvent?) {
                            Log.d("SimpleOnGestureListener", "onShowPress")
                        }

                        override fun onSingleTapUp(e: MotionEvent?): Boolean {
                            Log.d("SimpleOnGestureListener", "onSingleTapUp")
                            return false
                        }

                        override fun onDown(e: MotionEvent?): Boolean {
                            Log.d("SimpleOnGestureListener", "onDown")
                            return false
                        }

                        override fun onFling(
                            e1: MotionEvent?,
                            e2: MotionEvent?,
                            velocityX: Float,
                            velocityY: Float
                        ): Boolean {
                            Log.d(
                                "SimpleOnGestureListener",
                                "onFling velocityX=" + velocityX + " velocityY=" + velocityY
                            )
                            return false
                        }

                        override fun onScroll(
                            e1: MotionEvent?,
                            e2: MotionEvent?,
                            distanceX: Float,
                            distanceY: Float
                        ): Boolean {
                            Log.d(
                                "SimpleOnGestureListener",
                                "onScroll distanceX=" + distanceX + " distanceY=" + distanceY
                            )
                            return false
                        }

                        override fun onLongPress(e: MotionEvent?) {
                            Log.d("SimpleOnGestureListener", "onLongPress ")
                        }

                    }, handler)
            }
        }.start()
    }


//    override fun onTouchEvent(event: MotionEvent?): Boolean {
//        return gestureDetector.onTouchEvent(event)
//    }

    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        return gestureDetector.onTouchEvent(event)
    }
}
