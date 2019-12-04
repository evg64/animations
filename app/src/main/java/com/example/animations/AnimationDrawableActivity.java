package com.example.animations;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class AnimationDrawableActivity extends AppCompatActivity {

    private AnimationDrawable mDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation_drawable);
        ImageView horseImageView = findViewById(R.id.horseImageView);
        mDrawable = (AnimationDrawable) horseImageView.getDrawable();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mDrawable.start();
    }

    @Override
    protected void onStop() {
        mDrawable.stop();
        super.onStop();
    }
}
