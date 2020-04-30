package com.example.liseuse20.fragments;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.liseuse20.MainActivity;
import com.example.liseuse20.R;

public class PagerFragment extends Fragment implements View.OnClickListener {

    public static final String EXTRA_PARAM = "EXTRA PARAM";

    public PagerFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pager, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(getArguments() != null && getView() != null){
            TextView myText = getView().findViewById(R.id.mytext);
            String txtToShow = getArguments().getString(EXTRA_PARAM);

            myText.setText(txtToShow);
            getView().setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        if(getActivity() instanceof MainActivity) {
            String txtToSend = ((MainActivity) getActivity()).showInfosPage();

            MyDialogFragment dialog = new MyDialogFragment();
            Bundle bundle = new Bundle();
            bundle.putString(MyDialogFragment.EXTRA_POSITION, txtToSend);

            dialog.setArguments(bundle);
            dialog.show(getActivity().getSupportFragmentManager(), "tag");
        }
    }
}
