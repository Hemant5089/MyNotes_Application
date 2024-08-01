package com.example.mynotes.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.mynotes.Model.Notes;
import com.example.mynotes.Dao.NotesDao;

@Database(entities = {Notes.class} , version = 1, exportSchema = false)
public abstract class NotesDatabase extends RoomDatabase {
    public abstract NotesDao notesDao();
    public static NotesDatabase instance;

    public static synchronized NotesDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),NotesDatabase.class, "notes_database")
                    .allowMainThreadQueries().build();
        }
        return instance;

    }
}
