package com.example.reclyclerviewexo.notes.DAO;

import com.example.reclyclerviewexo.notes.POJOS.NoteDTO;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public abstract class NoteDAO {
    @Query("SELECT * FROM notes")
    public abstract List<NoteDTO> getListeNotes();

    @Query("SELECT COUNT(*) FROM notes WHERE intitule = :intitule")
    public abstract long countNotesByIntitule(String intitule);

    @Insert
    public abstract void insert(NoteDTO... notes);

    @Update
    public abstract void update(NoteDTO... notes);

    @Delete
    public abstract void delete(NoteDTO... notes);
}
