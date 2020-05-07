package com.example.curlinggame;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

public class GameActivity extends AppCompatActivity {

    private View myview;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        myview = findViewById(R.id.mapierre);
        final View theview = findViewById(R.id.theview);

        theview.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int x = (int)event.getX();
                int y = (int)event.getY();

                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    myview.animate()
                            .setDuration(500)
                            .translationY(y-theview.getHeight())
                            .withLayer();
                    myview.invalidate();
                }
                return true;
            }
        });
    }
}
