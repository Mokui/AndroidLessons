package com.example.showcontact.holder;

import android.view.View;
import android.widget.TextView;

import com.example.showcontact.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ContactViewHolder extends RecyclerView.ViewHolder {
    public TextView nomContact;
    public TextView numContact;

    public ContactViewHolder(@NonNull View itemView) {
        super(itemView);
        this.nomContact = itemView.findViewById(R.id.mycontact);
        this.numContact = itemView.findViewById(R.id.mycontactnumber);
    }
}
