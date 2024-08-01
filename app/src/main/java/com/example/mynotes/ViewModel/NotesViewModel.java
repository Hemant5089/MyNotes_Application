package com.example.mynotes.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.mynotes.Model.Notes;
import com.example.mynotes.Repository.NotesRepository;

import java.util.List;

public class NotesViewModel extends AndroidViewModel {

    public NotesRepository notesRepository;
    public LiveData<List<Notes>> NoteList;

    public LiveData<List<Notes>> hightolow;
    public LiveData<List<Notes>> lowtohigh;
    public NotesViewModel(@NonNull Application application) {
        super(application);

      notesRepository = new NotesRepository(application);
      NoteList = notesRepository.getAllNotes();
      hightolow = notesRepository.hightolow;
      lowtohigh = notesRepository.lowtohigh;
    }

    public void insertNote(Notes notes){
        notesRepository.insertNotes(notes);
    }
    public void deleteNote(int id){
        notesRepository.deleteNotes(id);
    }
    public void updateNote(Notes notes){
        notesRepository.updateNotes(notes);
}
    public LiveData<List<Notes>> getAllNotes(){
        return NoteList;
    }

}
