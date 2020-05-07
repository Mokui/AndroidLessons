package com.example.internetimage;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Tag";
    private TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Typeface myFont = Typeface.createFromAsset(getAssets(),"font/GreatVibes-Regular.ttf");
        txt = findViewById(R.id.mytext);
        txt.setTypeface(myFont);

        ImageView img = findViewById(R.id.myimg);
        Picasso.with(this).load("https://pbs.twimg.com/media/Dkq5wlWX4A0Zgkj.jpg")
                .fit()
                .centerCrop()
                .into(img);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.action_reglages:
                if (!txt.isShown()){
                    txt.setVisibility(View.VISIBLE);
                } else {
                    txt.setVisibility(View.INVISIBLE);
                }

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
