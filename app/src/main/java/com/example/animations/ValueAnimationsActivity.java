package com.example.animations;

import android.animation.Animator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.vectordrawable.graphics.drawable.AnimatorInflaterCompat;

import java.util.ArrayList;
import java.util.List;

public class ValueAnimationsActivity extends AppCompatActivity {

    private static final String ALPHA = "alpha";
    private static final String SCALE = "scale";
    private static final int MODE_XML = 0;
    private static final int MODE_PROPERTY_VALUES_HOLDER = 1;
    private static final int MODE_SEPARATE_ANIMATORS = 2;
    private int mMode = MODE_SEPARATE_ANIMATORS;
    private List<Animator> mAnimators = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_value_animations);

        ImageView imageView = findViewById(R.id.imageView);


        switch (mMode) {
            case MODE_XML: {
                ValueAnimator animatorXml = (ValueAnimator) AnimatorInflaterCompat
                        .loadAnimator(this, R.animator.value_animator_example);
                animatorXml.addUpdateListener(
                        animator -> imageView.setAlpha((Float) animator.getAnimatedValue())
                );
                mAnimators.add(animatorXml);
                break;
            }
            case MODE_PROPERTY_VALUES_HOLDER: {
                PropertyValuesHolder alphaHolder = PropertyValuesHolder.ofFloat(ALPHA, 0f, 1f);
                PropertyValuesHolder scaleHolder = PropertyValuesHolder.ofFloat(SCALE, 0.5f, 1f);
                // both alpha and scale
                ValueAnimator animator = ValueAnimator.ofPropertyValuesHolder(alphaHolder, scaleHolder);
                configureAnimator(animator);
                animator.addUpdateListener(
                        animation -> {
                            imageView.setAlpha((Float) animation.getAnimatedValue(ALPHA));
                            Float scale = (Float) animation.getAnimatedValue(SCALE);
                            imageView.setScaleX(scale);
                            imageView.setScaleY(scale);
                        }
                );
                mAnimators.add(animator);
                break;
            }
            case MODE_SEPARATE_ANIMATORS: {
                // alpha
                ValueAnimator alphaAnimator = ValueAnimator.ofFloat(0f, 1f);
                alphaAnimator.setDuration(1000);
                alphaAnimator.setRepeatMode(ValueAnimator.REVERSE);
                alphaAnimator.setRepeatCount(ValueAnimator.INFINITE);
                alphaAnimator.addUpdateListener(
                        animator -> imageView.setAlpha((Float) animator.getAnimatedValue())
                );
                mAnimators.add(alphaAnimator);

                // scale
                ValueAnimator scaleAnimator = ValueAnimator.ofFloat(0.5f, 1f);
                scaleAnimator.setDuration(1000);
                scaleAnimator.setRepeatMode(ValueAnimator.REVERSE);
                scaleAnimator.setRepeatCount(ValueAnimator.INFINITE);
                scaleAnimator.addUpdateListener(
                        animator -> {
                            Float scale = (Float) animator.getAnimatedValue();
                            imageView.setScaleX(scale);
                            imageView.setScaleY(scale);
                        }
                );
                mAnimators.add(scaleAnimator);
                break;

            }
            default:
                Log.d("ANIMATIONS", "Unsupported mode: " + mMode);
        }

        imageView.setOnClickListener((v) -> {
            for (Animator animator : mAnimators) {
                if (animator.isRunning()) {
                    animator.cancel();
                } else {
                    animator.start();
                }
            }
        });
    }

    @Override
    protected void onStop() {
        for (Animator animator : mAnimators) {
            if (animator.isRunning()) {
                animator.cancel();
            }
        }
        super.onStop();
    }

    private void configureAnimator(ValueAnimator animator) {
        animator.setDuration(1000);
        animator.setRepeatMode(ValueAnimator.REVERSE);
        animator.setRepeatCount(ValueAnimator.INFINITE);
    }
}
