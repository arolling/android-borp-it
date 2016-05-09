package com.example.guest.borp_it;


import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Random;

import butterknife.Bind;
import butterknife.ButterKnife;

public class GameActivity extends AppCompatActivity {
    public static final String TAG = GameActivity.class.getSimpleName();

    @Bind(R.id.borpButton) Button mBorpButton;
    @Bind(R.id.flingImageButton) ImageButton mFlingImageButton;
    @Bind(R.id.zoomFab) FloatingActionButton mZoomFab;
    @Bind(R.id.promptTextView) TextView mPromptTextView;
    private String[] mPrompts;
    private Random mRandom;
    private String mCurrentPrompt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        ButterKnife.bind(this);

        mRandom = new Random();
        mPrompts = new String[] {"Borp", "Pull", "Twist"};
        generatePrompt();

    }

    public void generatePrompt(){
        int randomNumber = mRandom.nextInt(3);
        Log.v(TAG, "random number: " + randomNumber);
        mCurrentPrompt = mPrompts[randomNumber];
        mPromptTextView.setText(mCurrentPrompt);
    }
}
