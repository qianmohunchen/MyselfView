package adi.example.eventdispatch;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

public class MyLinearLayout2 extends LinearLayout {
    public MyLinearLayout2(Context context) {
        super(context);
    }

    public MyLinearLayout2(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyLinearLayout2(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.d("touchevent"," MyLinearout22 dispatchTouchEvent");
        return  super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.d("touchevent"," MyLinearout22 onInterceptTouchEvent33");
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d("touchevent"," MyLinearout22 onTouchEvent");
        return false;
    }
}
