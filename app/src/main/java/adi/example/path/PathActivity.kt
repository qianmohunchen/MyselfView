package adi.example.path

import adi.example.bezier.BezierActivity
import adi.example.bezier.R
import android.content.Intent
import android.graphics.Matrix
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_host.*

class PathActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_path)

        val matrix = Matrix()
        matrix.setScale(0.5f,1f)
    }

}
