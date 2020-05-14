package com.example.premierenumber;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    protected TextView txt;
    protected EditText input;
    protected ProgressBar bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt = findViewById(R.id.txt);
        input = findViewById(R.id.editText);
        bar = findViewById(R.id.progress);

        bar.setMax(100);
        bar.setProgress(0);
    }

    public void asyncCheckNum(View view) {
        txt.setVisibility(View.VISIBLE);
        txt.setText(" ");
        bar.setVisibility(View.VISIBLE);
        MonAsyncTask asyncTask = new MonAsyncTask(this);
        asyncTask.execute(Long.parseLong(input.getText().toString()));
    }

    @SuppressLint("SetTextI18n")
    public void getRes(Boolean resultat){
        if (resultat){
            txt.setText("Le nombre est premier!");
            txt.setTextColor(Color.GREEN);
        } else {
            txt.setText("Le nombre n'est pas premier!");
            txt.setTextColor(Color.RED);
        }
    }
}
