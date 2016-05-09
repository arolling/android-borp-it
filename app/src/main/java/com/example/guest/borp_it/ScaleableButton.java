package com.example.guest.borp_it;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;

/**
 * Created by Guest on 5/9/16.
 */
public class ScaleableButton extends FloatingActionButton implements View.OnTouchListener, ScaleGestureDetector.OnScaleGestureListener {

    ScaleGestureDetector mScaleDetector = new ScaleGestureDetector(getContext(), this);

    private float mScaleFactor = 1.f;


    public ScaleableButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    @Override
    public boolean onScale(ScaleGestureDetector scaleGestureDetector) {
        mScaleFactor *= scaleGestureDetector.getScaleFactor();

        mScaleFactor = Math.max(0.1f, Math.min(mScaleFactor, 5.0f));
        Log.v("This is scaling", "wow");
        invalidate();
        return true;
    }

    @Override
    public boolean onScaleBegin(ScaleGestureDetector scaleGestureDetector) {
        return false;
    }

    @Override
    public void onScaleEnd(ScaleGestureDetector scaleGestureDetector) {

    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        Log.v("This is touched", "wow");
        if (mScaleDetector.onTouchEvent(motionEvent))
            return true;
        return super.onTouchEvent(motionEvent);
    }
}
