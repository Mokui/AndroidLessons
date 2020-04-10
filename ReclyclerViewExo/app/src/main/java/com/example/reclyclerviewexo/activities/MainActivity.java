package com.example.reclyclerviewexo.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.reclyclerviewexo.R;
import com.example.reclyclerviewexo.notes.DAO.NotesDatabaseHelper;
import com.example.reclyclerviewexo.notes.POJOS.NoteDTO;
import com.example.reclyclerviewexo.notes.adapter.NoteAdapter;
import com.example.reclyclerviewexo.notes.helpers.ItemTouchHelperCallback;
import com.example.reclyclerviewexo.notes.helpers.NetworkHelper;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public NoteAdapter noteAdapter;
    public List<NoteDTO> listNotes;
    public EditText myInput;

    public ItemTouchHelper itemTouchHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences preferences =
                PreferenceManager.getDefaultSharedPreferences(this);
        int lastMemo = preferences.getInt("last", -1);
        if(lastMemo != -1){
            Toast.makeText(this, String.valueOf(lastMemo), Toast.LENGTH_SHORT).show();
        }

        //Check connectivity
        if(!NetworkHelper.estConnecte(getApplicationContext())){
            Toast.makeText(this, R.string.no_co, Toast.LENGTH_SHORT).show();
        }

        myInput = findViewById(R.id.insertnote);

        RecyclerView recyclerView = findViewById(R.id.liste_notes);
        // à ajouter pour de meilleures performances :
        recyclerView.setHasFixedSize(true);
        // layout manager, décrivant comment les items sont disposés :
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        listNotes = NotesDatabaseHelper.getDatabase(this).noteDAO().getListeNotes();

        // adapter :
        noteAdapter = new NoteAdapter(listNotes, this);
        recyclerView.setAdapter(noteAdapter);

        itemTouchHelper = new ItemTouchHelper(new ItemTouchHelperCallback(noteAdapter));
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

    public void insertMemo(View v) throws FileNotFoundException {
        NoteDTO n = new NoteDTO(myInput.getText().toString());

        listNotes.add(n);
        NotesDatabaseHelper.getDatabase(v.getContext()).noteDAO().insert(n);

        noteAdapter.notifyItemInserted(listNotes.size());
        myInput.setText("");
    }
}
