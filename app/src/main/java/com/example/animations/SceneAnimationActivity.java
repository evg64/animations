package com.example.animations;

import android.os.Bundle;
import android.transition.ChangeBounds;
import android.transition.Scene;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;

public class SceneAnimationActivity extends AppCompatActivity {

    private State mState = State.SCENE_1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scene_animation);

        FrameLayout sceneContainer = findViewById(R.id.sceneContainer);
        findViewById(R.id.swapScenes).setOnClickListener(v -> {
            int targetLayout;
            if (mState == State.SCENE_1) {
                targetLayout = R.layout.scene_2;
                mState = State.SCENE_2;
            } else {
                targetLayout = R.layout.scene_1;
                mState = State.SCENE_1;
            }
            Transition transition = new ChangeBounds();
            transition.setDuration(1000L);
            Scene finalScene = Scene.getSceneForLayout(sceneContainer, targetLayout, this);
            TransitionManager.go(finalScene, transition);
        });
    }

    private enum State {
        SCENE_1, SCENE_2
    }
}
