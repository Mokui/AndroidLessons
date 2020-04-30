package com.example.liseuse20;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.liseuse20.adapter.MyPagerAdapter;
import com.example.liseuse20.fragments.MyDialogFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<String> elements;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        elements = new ArrayList<>();
        elements.add(getString(R.string.lorem1));
        elements.add(getString(R.string.lorem2));
        elements.add(getString(R.string.lorem3));

        MyPagerAdapter adapter = new MyPagerAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT,  elements);
        viewPager = findViewById(R.id.viewpager);
        viewPager.setAdapter(adapter);
    }

    public String showInfosPage() {
        String text = getString(R.string.words) + elements.get(viewPager.getCurrentItem()).length() + getString(R.string.pagen) + (viewPager.getCurrentItem()+1);
        return text;
    }
}
