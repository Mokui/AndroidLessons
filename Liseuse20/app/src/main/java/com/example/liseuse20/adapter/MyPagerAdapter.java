package com.example.liseuse20.adapter;

import android.os.Bundle;

import com.example.liseuse20.fragments.PagerFragment;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class MyPagerAdapter extends FragmentStatePagerAdapter {

    private final List<String> texts;

    public MyPagerAdapter(@NonNull FragmentManager myFragmentManager, int behavior, List<String> texts) {
        super(myFragmentManager, behavior);
        this.texts = texts;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        PagerFragment fragment = new PagerFragment();
        String txtToSend = texts.get(position);

        Bundle bundle = new Bundle();
        bundle.putString(PagerFragment.EXTRA_PARAM, txtToSend);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getCount() {
        return texts.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return super.getPageTitle(position);
    }
}
