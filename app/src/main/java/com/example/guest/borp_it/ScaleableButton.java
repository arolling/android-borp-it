package com.example.guest.borp_it;

import android.support.design.widget.FloatingActionButton;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;

/**
 * Created by Guest on 5/9/16.
 */
public class ScaleableButton extends FloatingActionButton implements View.OnTouchListener, ScaleGestureDetector.OnScaleGestureListener {

    ScaleGestureDetector mScaleDetector = new ScaleGestureDetector(getContext(), this);

    public ScaleableButton
    @Override
    public boolean onScale(ScaleGestureDetector scaleGestureDetector) {
        return false;
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
        return false;
    }
}
