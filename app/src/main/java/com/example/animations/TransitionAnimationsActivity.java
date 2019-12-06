package com.example.animations;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class TransitionAnimationsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transition_animations);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (isFinishing()) {
            overridePendingTransition(R.anim.enter_from_top_left, R.anim.exit_from_top_left_to_right_down);
        }
    }
}
