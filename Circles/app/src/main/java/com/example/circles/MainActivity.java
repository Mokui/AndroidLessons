package com.example.circles;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import com.example.circles.affichages.MaVue;

public class MainActivity extends AppCompatActivity {

    private MaVue myview;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myview = findViewById(R.id.mavue);

        myview.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int x = (int)event.getX();
                int y = (int)event.getY();

                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    myview.setValX(x);
                    myview.setValY(y);
                    myview.setRandColor();
                    myview.invalidate();
                }
                return true;
            }
        });
    }
}
