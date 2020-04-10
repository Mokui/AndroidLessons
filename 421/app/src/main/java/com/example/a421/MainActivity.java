package com.example.a421;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView text1;
    private TextView text2;
    private TextView text3;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text1 = findViewById(R.id.textView1);
        text2 = findViewById(R.id.textView2);
        text3 = findViewById(R.id.textView3);

        if(savedInstanceState != null){
            text1.setText(savedInstanceState.getString("de1"));
            text2.setText(savedInstanceState.getString("de2"));
            text3.setText(savedInstanceState.getString("de3"));
        }else {
            text1.setText(""+new Random().nextInt(6-1));
            text2.setText(""+new Random().nextInt(6-1));
            text3.setText(""+new Random().nextInt(6-1));
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString("de1", (String) text1.getText());
        outState.putString("de2", (String) text2.getText());
        outState.putString("de3", (String) text3.getText());

        super.onSaveInstanceState(outState);
    }
}
