package com.example.examplenavigation;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public int valueDecompte = 100;
    public TextView myBtnTxt;
    public TextView sendBtnTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sendBtnTxt = findViewById(R.id.send);
        myBtnTxt = findViewById(R.id.decpt);
    }

    public void appuie(View v){
        Intent intent = new Intent(this, NumberDecompte.class);
        //intent.putExtra("",);
        //intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivityForResult(intent, valueDecompte);
        //finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        sendBtnTxt.setVisibility(View.VISIBLE);

        if(requestCode == valueDecompte && resultCode == Activity.RESULT_OK) {
            int val = data.getIntExtra("valueDecompte",-1);

            new CountDownTimer((val*1000), 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    final String tosend = String.valueOf(millisUntilFinished / 1000);
                    sendBtnTxt.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent msg = new Intent();
                            msg.setAction(Intent.ACTION_SEND);
                            msg.putExtra(Intent.EXTRA_TEXT, tosend);
                            msg.setType("text/plain");

                            if(msg.resolveActivity(getPackageManager()) != null){
                                startActivity(msg);
                            }
                        }
                    });

                    myBtnTxt.setText(String.valueOf(millisUntilFinished / 1000));
                }

                @Override
                public void onFinish() {
                    myBtnTxt.setText(R.string.decompte);
                    sendBtnTxt.setVisibility(View.INVISIBLE);
                }
            }.start();
        }
    }
}
