package com.example.showcontact.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.showcontact.R;
import com.example.showcontact.bean.Contact;
import com.example.showcontact.holder.ContactViewHolder;

import java.util.List;
import androidx.recyclerview.widget.RecyclerView;

public class ContactAdapter extends RecyclerView.Adapter<ContactViewHolder>
{
    // Liste d'objets métier :
    private List<Contact> listeContacts;
    // Constructeur :
    public ContactAdapter(List<Contact> listeContacts) {
        this.listeContacts = listeContacts;
    }

    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View viewCourse = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.simple_row, parent, false);
        return new ContactViewHolder(viewCourse);
    }
    @Override
    public void onBindViewHolder(ContactViewHolder holder, int position)
    {
        holder.nomContact.setText(listeContacts.get(position).nom);
        holder.numContact.setText(listeContacts.get(position).numero);
    }
    @Override
    public int getItemCount()
    {
        return listeContacts.size();
    }
}
