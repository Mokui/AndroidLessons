package com.formationandroid.starwarsrandomplanet.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.formationandroid.starwarsrandomplanet.R;
import com.formationandroid.starwarsrandomplanet.model.Item;
import com.formationandroid.starwarsrandomplanet.repository.MainRepository;
import com.formationandroid.starwarsrandomplanet.viewmodel.MainViewModel;

import java.text.CollationElementIterator;

public class MainActivity extends AppCompatActivity {

    private MainViewModel mainViewModel;
    private TextView monTextView;
    private ProgressBar monSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        monTextView = findViewById(R.id.mytxt);
        monSpinner = findViewById(R.id.progress_bar);

        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        mainViewModel.init(new MainRepository());
        mainViewModel.getLiveDataItem().observe(this, new Observer<Item>(){
            @Override
            public void onChanged(Item item)
            {
                monTextView.setText(item.libelle);
            }
        });
        mainViewModel.getLiveDataSpinner().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean){
                    monSpinner.setVisibility(View.VISIBLE);
                } else {
                    monSpinner.setVisibility(View.INVISIBLE);
                }
            }
        });
    }

    public void clicRandom(View view){
        mainViewModel.clicRandomViewModel(view);
    }
}
