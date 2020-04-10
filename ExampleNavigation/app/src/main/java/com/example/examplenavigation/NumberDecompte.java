package com.example.examplenavigation;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class NumberDecompte extends AppCompatActivity {

    private EditText inputDecompte;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number_decompte);
        inputDecompte = findViewById(R.id.editText);
    }

    public void lancementDecompte(View v){
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("valueDecompte", Integer.valueOf(inputDecompte.getText().toString()));
        //intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        setResult(Activity.RESULT_OK, intent);
        finish();
    }
}
