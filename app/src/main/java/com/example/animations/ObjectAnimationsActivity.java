package com.example.animations;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.TimeInterpolator;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.BounceInterpolator;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class ObjectAnimationsActivity extends AppCompatActivity {

    private static final boolean VIA_PROPERTY_VALUES_HOLDER = false;
    private List<Animator> mAnimators = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_object_animations);

        ImageView imageView = findViewById(R.id.imageView);

        float translationY = getResources().getDimension(R.dimen.translationY);
        if (VIA_PROPERTY_VALUES_HOLDER) {
            PropertyValuesHolder alphaHolder = PropertyValuesHolder.ofFloat(View.TRANSLATION_Y, 0f, translationY);
            PropertyValuesHolder rotationHolder = PropertyValuesHolder.ofFloat(View.ROTATION, 0f, 360f);
            ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(imageView, alphaHolder, rotationHolder);
            configure(animator).setDuration(2000);
            mAnimators.add(animator);
        } else {
            // rotation animator
            ObjectAnimator rotationAnimator = ObjectAnimator.ofFloat(imageView, "rotation", 0f, 360f);
            rotationAnimator.setRepeatCount(ObjectAnimator.INFINITE);
            rotationAnimator.setRepeatMode(ObjectAnimator.REVERSE);
            rotationAnimator.setDuration(1000);
            mAnimators.add(rotationAnimator);

            // translation animator
            ObjectAnimator translationAnimator = ObjectAnimator.ofFloat(imageView, "translationY", 0f, translationY);
            translationAnimator.setRepeatCount(ObjectAnimator.INFINITE);
            translationAnimator.setRepeatMode(ObjectAnimator.REVERSE);
            translationAnimator.setDuration(1000);
            mAnimators.add(translationAnimator);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        for (Animator animator : mAnimators) {
            animator.start();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        for (Animator animator : mAnimators) {
            animator.end();
        }
    }

    private ObjectAnimator configure(ObjectAnimator animator) {
        animator.setRepeatCount(ObjectAnimator.INFINITE);
        animator.setRepeatMode(ObjectAnimator.REVERSE);
        return animator;
    }
}
