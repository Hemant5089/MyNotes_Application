package com.example.mynotes.Repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.mynotes.Dao.NotesDao;
import com.example.mynotes.Database.NotesDatabase;
import com.example.mynotes.Model.Notes;

import java.util.List;

public class NotesRepository {
    private NotesDao notesDao;

    private LiveData<List<Notes>>  noteList;

    public LiveData<List<Notes>> hightolow;
    public LiveData<List<Notes>> lowtohigh;

    public NotesRepository(Application application) {
        NotesDatabase notesDatabase = NotesDatabase.getInstance(application);
        notesDao = notesDatabase.notesDao();
        noteList = notesDao.getAllNotes();
        hightolow = notesDao.hightolow();
        lowtohigh = notesDao.lowtohigh();
    }
   public void insertNotes(Notes notes){
        notesDao.insertNotes(notes);
    }
   public void deleteNotes(int id){
        notesDao.deleteNotes(id);
    }
    public void updateNotes(Notes notes){
        notesDao.updateNotes(notes);
    }
    public LiveData<List<Notes>> getAllNotes(){
        return noteList;
    }
}
