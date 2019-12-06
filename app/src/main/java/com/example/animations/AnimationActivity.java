package com.example.animations;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;

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
        onClickGoTo(R.id.sceneAnimations, SceneAnimationActivity.class);
        findViewById(R.id.transitionAnimations).setOnClickListener(this::launchTransitionAnimation);
        findViewById(R.id.sharedElementAnimations).setOnClickListener(this::launchSharedElementAnimation);
    }

    private void onClickGoTo(@IdRes int viewId, @NonNull Class<? extends Activity> whereToGo) {
        findViewById(viewId).setOnClickListener((v) ->
                startActivity(new Intent(this, whereToGo))
        );
    }

    private void launchTransitionAnimation(View view) {
        ActivityOptionsCompat options = ActivityOptionsCompat.makeCustomAnimation(
                this,
                R.anim.enter_from_bottom_right,
                R.anim.exit_from_top_left_to_left_up
        );
        Intent intent = new Intent(this, TransitionAnimationsActivity.class);
        startActivity(intent, options.toBundle());
    }

    private void launchSharedElementAnimation(View view) {
        String transitionName = getString(R.string.shared_element_button);
        ActivityOptionsCompat options = ActivityOptionsCompat
                .makeSceneTransitionAnimation(this, view, transitionName);
        Intent intent = new Intent(this, SharedElementAnimationsActivity.class);
        startActivity(intent, options.toBundle());
    }
}
