package com.example.mynotes.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.mynotes.Model.Notes;

import java.util.List;

@androidx.room.Dao
public interface NotesDao {
    @Query("SELECT * FROM notes_database")
   LiveData<List<Notes>> getAllNotes();

    @Query("SELECT * FROM notes_database ORDER BY notes_priority DESC")
    LiveData<List<Notes>> hightolow();

    @Query("SELECT * FROM notes_database ORDER BY notes_priority ASC")
    LiveData<List<Notes>> lowtohigh();

    @Insert
    void insertNotes(Notes... notes);

    @Query("DELETE FROM notes_database WHERE id = :id")
    void deleteNotes(int id);

   @Update
    void updateNotes(Notes... notes);
}
