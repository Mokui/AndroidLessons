package com.example.curlinggame;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView = findViewById(R.id.myimg);
        AnimatorSet set = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.anim_txt);

        set.setTarget(textView);
        set.start();
    }

    public void clickOnStart(View v){
        Intent in = new Intent(this,GameActivity.class);
        startActivity(in);
        overridePendingTransition(R.anim.page_in,R.anim._page_out);
    }
}
