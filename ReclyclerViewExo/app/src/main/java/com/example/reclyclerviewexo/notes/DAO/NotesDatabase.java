package com.example.reclyclerviewexo.notes.DAO;

import com.example.reclyclerviewexo.notes.POJOS.NoteDTO;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {NoteDTO.class /*, ...*/}, version = 1)
public abstract class NotesDatabase extends RoomDatabase {
    public abstract NoteDAO noteDAO();
}
