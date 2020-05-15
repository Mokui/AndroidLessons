package com.example.reclyclerviewexo;


import android.content.Context;

import com.example.reclyclerviewexo.notes.DAO.NoteDAO;
import com.example.reclyclerviewexo.notes.DAO.NoteDAO_Impl;
import com.example.reclyclerviewexo.notes.DAO.NotesDatabaseHelper;
import com.example.reclyclerviewexo.notes.POJOS.NoteDTO;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class NoteDAOInstrumentalTest {
    @Test
    public void getListeNotesTest() {
        // Context of the app under test.
        Context context = ApplicationProvider.getApplicationContext();
        List<NoteDTO> listNotes = NotesDatabaseHelper.getDatabase(context).noteDAO().getListeNotes();
        int nb = listNotes.size();
        NoteDTO n = new NoteDTO("intitule");

        listNotes.add(n);
        NotesDatabaseHelper.getDatabase(context).noteDAO().insert(n);
        assertEquals(listNotes.size(), nb+1);
    }
}
