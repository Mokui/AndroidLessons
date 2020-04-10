package com.example.liseuse;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toast.makeText(this, R.string.acceuil, Toast.LENGTH_LONG).show();
    }

    public void remonter(View v){
        ScrollView myScrollView = findViewById(R.id.scroll);
        myScrollView.smoothScrollTo(0,0);

        Toast.makeText(this, R.string.debut, Toast.LENGTH_LONG).show();
    }
}
