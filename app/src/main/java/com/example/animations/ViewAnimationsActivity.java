package com.example.animations;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;

import androidx.appcompat.app.AppCompatActivity;

public class ViewAnimationsActivity extends AppCompatActivity {

    private static final String TAG = "ANIMATIONS";
    private Handler mHandler;
    private Runnable mAction;

    private boolean mCreateViaXml = false;
    private View mView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_animations);
        mView = findViewById(R.id.imageView);
        mHandler = new Handler();
        mAction = new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "width = " + mView.getWidth() + " height = " + mView.getHeight() + " alpha = " + mView.getAlpha());
                mHandler.postDelayed(this, 200);
            }
        };
    }

    @Override
    protected void onStart() {
        super.onStart();
        mView.post(mAction);
        if (mCreateViaXml) {
            animateViaXml();
        } else {
            animateProgrammatically2();
        }
    }

    @Override
    protected void onStop() {
        findViewById(R.id.imageView).clearAnimation();
        mHandler.removeCallbacks(mAction);
        super.onStop();
    }

    private void animateViaXml() {
        // create animation via xml
        final Animation animation = AnimationUtils.loadAnimation(this, R.anim.tween_animations);
        // find view and launch animation
        View view = findViewById(R.id.imageView);
        view.startAnimation(animation);
    }

    private void animateProgrammatically() {
        // create animation
        final Animation animation = new ScaleAnimation(0.5f, 1f, 0.5f, 1f);
        animation.setRepeatMode(Animation.REVERSE);
        animation.setRepeatCount(Animation.INFINITE);
        animation.setDuration(500);

        // find view and launch animation
        View view = findViewById(R.id.imageView);
        view.startAnimation(animation);
    }

    private void animateProgrammatically2() {
        // create scale animation
        ScaleAnimation scaleAnimation = new ScaleAnimation(0.5f, 1f, 0.5f, 1f);
        scaleAnimation.setRepeatMode(Animation.REVERSE);
        scaleAnimation.setRepeatCount(Animation.INFINITE);
        scaleAnimation.setDuration(500);

        // create alpha animation
        AlphaAnimation alphaAnimation = new AlphaAnimation(0f, 1f);
        alphaAnimation.setRepeatMode(Animation.REVERSE);
        alphaAnimation.setRepeatCount(Animation.INFINITE);
        alphaAnimation.setDuration(500);

        // add animations to AnimationSet
        AnimationSet animations = new AnimationSet(false);
        animations.addAnimation(scaleAnimation);
        animations.addAnimation(alphaAnimation);

        // find view and launch animation
        View view = findViewById(R.id.imageView);
        view.startAnimation(animations);
    }
}
