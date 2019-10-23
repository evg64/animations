package com.example.animations;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class AnimationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);

        onClickGoTo(R.id.animationDrawable, AnimationDrawableActivity.class);
        onClickGoTo(R.id.viewAnimations, ViewAnimationsActivity.class);
        onClickGoTo(R.id.valueAnimations, ValueAnimationsActivity.class);
        onClickGoTo(R.id.objectAnimations, ObjectAnimationsActivity.class);
        onClickGoTo(R.id.customViewAnimations, CustomViewAnimationsActivity.class);
    }

    private void onClickGoTo(@IdRes int viewId, @NonNull Class<? extends Activity> whereToGo) {
        findViewById(viewId).setOnClickListener((v) ->
                startActivity(new Intent(this, whereToGo))
        );
    }
}
