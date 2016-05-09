package com.example.guest.borp_it;


import android.animation.ObjectAnimator;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.view.ScaleGestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Random;

import butterknife.Bind;
import butterknife.ButterKnife;

public class GameActivity extends AppCompatActivity {
    public static final String TAG = GameActivity.class.getSimpleName();

    @Bind(R.id.borpButton) Button mBorpButton;
    @Bind(R.id.flingImageButton) ImageButton mFlingImageButton;
    @Bind(R.id.zoomFab) ScaleableButton mZoomFab;
    @Bind(R.id.promptTextView) TextView mPromptTextView;
    @Bind(R.id.container) RelativeLayout mainScreen;
    private String[] mPrompts;
    private Random mRandom;
    private String mCurrentPrompt;
    private GestureDetectorCompat mDetector;
    private ScaleGestureDetector mScaleDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        ButterKnife.bind(this);

        mRandom = new Random();
        mPrompts = new String[] {"Borp", "Pull", "Twist"};
        generatePrompt();
        mDetector = new GestureDetectorCompat(this, new BoomerangGestureListener());
        mScaleDetector = mZoomFab.mScaleDetector;

        mBorpButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if(mCurrentPrompt.equals("Borp")){
                    Log.v(TAG, "correct Borp");
                    generatePrompt();
                    return true;
                }
                Log.v(TAG, "incorrect Borp");
                generatePrompt();
                return false;
            }
        });

        mFlingImageButton.setOnTouchListener(new View.OnTouchListener(){
            public boolean onTouch(View v, MotionEvent event){
                mDetector.onTouchEvent(event);
                return true;
            }
        });

        mZoomFab.setOnTouchListener(new View.OnTouchListener(){
            public boolean onTouch(View v, MotionEvent event){
                Log.v(TAG, "touched fab");
                mScaleDetector.onTouchEvent(event);
                return true;
            }
        });

    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        this.mDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }


    private class BoomerangGestureListener extends GestureDetector.SimpleOnGestureListener {
        int MIN_DIST = 100;

        @Override
        public boolean onDown(MotionEvent e){
            Log.v(TAG, "touched boomerang");
            return true;
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY){
            if(mCurrentPrompt.equals("Pull")){
                float e1X = e1.getX();
                float e1Y = e1.getY();
                float e2X = e2.getX();
                float e2Y = e2.getY();
                float distX = e2X - e1X;
                float distY = e2Y - e1Y;

                DisplayMetrics displayMetrics = new DisplayMetrics();
                getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
                int offsetY = displayMetrics.heightPixels - mainScreen.getMeasuredHeight();

                int[] location = new int[2];
                mFlingImageButton.getLocationOnScreen(location);
                float orgX = location[0];
                float orgY = location[1] - offsetY;

                float stopX = orgX + distX;
                float stopY = orgY + distY;


                if (distX > MIN_DIST) {
                    //Fling Right
                    ObjectAnimator flingAnimator = ObjectAnimator.ofFloat(mFlingImageButton, "translationX", orgX, stopX);
                    flingAnimator.setDuration(1000);
                    flingAnimator.start();
                }else if(distX < - MIN_DIST){
                    //Fling Left
                    ObjectAnimator flingAnimator = ObjectAnimator.ofFloat(mFlingImageButton, "translationX", orgX, stopX);
                    flingAnimator.setDuration(1000);
                    flingAnimator.start();
                }else if (distY > MIN_DIST) {
                    //Fling Down
                    ObjectAnimator flingAnimator = ObjectAnimator.ofFloat(mFlingImageButton, "translationY", orgY, stopY);
                    flingAnimator.setDuration(1000);
                    flingAnimator.start();
                }else if(distY < - MIN_DIST){
                    //Fling Up
                    ObjectAnimator flingAnimator = ObjectAnimator.ofFloat(mFlingImageButton, "translationY", orgY, stopY);
                    flingAnimator.setDuration(1000);
                    flingAnimator.start();
                }

                Log.v(TAG, "flung boomerang");
                Log.d(TAG, "onFling: " + e1.toString()+e2.toString());
                //mainScreen.postInvalidateDelayed(3500); todo: MAKE THE SCREEN REFRESH
                generatePrompt();
            } else {
                //Todo: shake the phone.
            }


            return true;
        }

    }

    public void generatePrompt(){
        //todo: add animation for new prompt
        int randomNumber = mRandom.nextInt(3);
        Log.v(TAG, "random number: " + randomNumber);
        mCurrentPrompt = mPrompts[randomNumber];
        mPromptTextView.setText(mCurrentPrompt);
    }

    //todo: write phone shaker to indicate wrong action


}
