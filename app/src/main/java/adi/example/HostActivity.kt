package adi.example

import adi.example.bezier.BezierActivity
import adi.example.bezier.R
import adi.example.eventdispatch.EventDispatchActivity
import adi.example.gesturedetector.GestureDetectorActivity
import adi.example.indicator.IndicatorActivity
import adi.example.path.PathActivity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_host.*

class HostActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_host)
        path_btn.setOnClickListener {
            startActivity(Intent(this, PathActivity::class.java))
        }
        btn2.setOnClickListener {
            startActivity(Intent(this, BezierActivity::class.java))
        }
        btn3.setOnClickListener {
            startActivity(Intent(this, EventDispatchActivity::class.java))
        }
        btn4.setOnClickListener {
            startActivity(Intent(this, GestureDetectorActivity::class.java))
        }
        btn5.setOnClickListener {
            startActivity(Intent(this, IndicatorActivity::class.java))
        }
    }

}
