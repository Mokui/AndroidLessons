package com.example.liseuse20.fragments;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.ScrollView;

import com.example.liseuse20.MainActivity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class MyDialogFragment extends DialogFragment {
    public static final String EXTRA_POSITION = "EXTRA_POSITION";

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        String message= null;
        if( getArguments() != null) {
            message = getArguments().getString(EXTRA_POSITION);
        }

        // paramétrage de la boîte de dialogue grâce à un builder :
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(message)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id)
                    {
                        dismiss();
                        /*if(getActivity() instanceof MainActivity) {
                            ((MainActivity) getActivity()).showInfosPage(id);
                        }*/
                    }
                });
        // création de la boîte de dialogue:
        return builder.create();
    }
}
