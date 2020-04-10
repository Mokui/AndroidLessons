package com.example.reclyclerviewexo.notes.DAO;

import android.content.Context;

import androidx.room.Room;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

public class NotesDatabaseHelper
{
    // Attributs :
    private static NotesDatabaseHelper databaseHelper = null;
    private NotesDatabase database;

    /*
    // Script de migration :
    static final Migration MIGRATION_1_2 = new Migration(1, 2)
    {
        @Override
        public void migrate(SupportSQLiteDatabase database)
        {
            //database.execSQL("...");
            //database.execSQL("...");
        }
    };
     */

    // Constructeur :
    private NotesDatabaseHelper(Context context)
    {
        database = Room
                .databaseBuilder(context, NotesDatabase.class, "notes.db")
                //.addMigrations(MIGRATION_1_2,...)
                .allowMainThreadQueries()
                .build();
    }
    // Getter instance :
    public static synchronized NotesDatabase getDatabase(Context context)
    {
        if (databaseHelper == null)
        {
            databaseHelper = new NotesDatabaseHelper(
                    context.getApplicationContext());
        }
        return databaseHelper.database;
    }
}
