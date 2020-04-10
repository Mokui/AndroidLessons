package com.example.reclyclerviewexo.fragments;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.reclyclerviewexo.R;
import com.example.reclyclerviewexo.activities.DetailActivity;

public class DetailFragment extends Fragment implements View.OnClickListener {
    public static final String EXTRA_PARAM = "EXTRA PARAM";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(getArguments() != null && getView() != null){
            String argument = getArguments().getString(EXTRA_PARAM);

            TextView txtv = getView().findViewById(R.id.txt);
            txtv.setText(argument);

            //Button btn = getView().findViewById(R.id.btn);
            //btn.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        FragmentActivity activity = getActivity();
        if(activity instanceof DetailActivity){
            ((DetailActivity) activity).afficheNote();
        }
    }
}
