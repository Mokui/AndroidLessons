package com.example.reclyclerviewexo.notes.POJOS;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "notes")
public class NoteDTO
{
    @PrimaryKey(autoGenerate = true)
    public long noteId = 0;
    public String intitule;
    @Ignore
    public boolean selectionne;

    public NoteDTO() {}

    public NoteDTO(String intitule)
    {
        this.intitule = intitule;
    }
}
