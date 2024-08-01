package com.example.mynotes;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.splashscreen.SplashScreen;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.mynotes.Activity.InsertActivity;
import com.example.mynotes.Adapter.NotesAdapter;
import com.example.mynotes.Model.Notes;
import com.example.mynotes.ViewModel.NotesViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton add_btn;
    NotesViewModel notesViewModel;
    RecyclerView notesRecycler;
    NotesAdapter adapter;

    MaterialTextView nofilter,hightolow,lowtohigh;

    List<Notes> filterNotesalllist = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SplashScreen splashScreen = SplashScreen.installSplashScreen(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        add_btn = findViewById(R.id.add_btn);
        notesRecycler = findViewById(R.id.rv);
        nofilter = findViewById(R.id.nofilter);
        hightolow = findViewById(R.id.hightolow);
        lowtohigh = findViewById(R.id.lowtohigh);

        nofilter.setBackgroundResource(R.drawable.filter_strok_shape);
        nofilter.setOnClickListener(V -> {
            loadData(0);
            hightolow.setBackgroundResource(R.drawable.filter_shape);
            lowtohigh.setBackgroundResource(R.drawable.filter_shape);
            nofilter.setBackgroundResource(R.drawable.filter_strok_shape);

        });
        hightolow.setOnClickListener(V -> {
            loadData(1);
            hightolow.setBackgroundResource(R.drawable.filter_strok_shape);
            lowtohigh.setBackgroundResource(R.drawable.filter_shape);
            nofilter.setBackgroundResource(R.drawable.filter_shape);
        });
        lowtohigh.setOnClickListener(V -> {
            loadData(2);
            hightolow.setBackgroundResource(R.drawable.filter_shape);
            lowtohigh.setBackgroundResource(R.drawable.filter_strok_shape  );
            nofilter.setBackgroundResource(R.drawable.filter_shape);
        });

        notesViewModel = new ViewModelProvider(this).get(NotesViewModel.class);

        add_btn.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, InsertActivity.class));
        });

//        notesViewModel.getAllNotes().observe(this, notes -> {
//            notesRecycler.setLayoutManager(new GridLayoutManager(this, 2));
//            adapter = new NotesAdapter(this, notes);
//            notesRecycler.setAdapter(adapter);
//        });


            notesViewModel.getAllNotes().observe(this, new Observer<List<Notes>>() {
                @Override
                public void onChanged(List<Notes> notes) {
                    setAdapter(notes);
                    filterNotesalllist = notes;
                }
            });
    }

    private void loadData(int i){
         if(i == 0){
             notesViewModel.getAllNotes().observe(this, new Observer<List<Notes>>() {
                 @Override
                 public void onChanged(List<Notes> notes) {
                     setAdapter(notes);
                     filterNotesalllist = notes;
                 }
             });

         }
         else if (i == 1) {
             notesViewModel.hightolow.observe(this, new Observer<List<Notes>>() {
                 @Override
                 public void onChanged(List<Notes> notes) {
                     setAdapter(notes);
                     filterNotesalllist = notes;
                 }
             });
         }
         else{
             notesViewModel.lowtohigh.observe(this, new Observer<List<Notes>>() {
                 @Override
                 public void onChanged(List<Notes> notes) {
                     setAdapter(notes);
                     filterNotesalllist = notes;
                 }
             });
         }
    }

    public void setAdapter(List<Notes> notes){
           notesRecycler.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
           adapter = new NotesAdapter(MainActivity.this,notes);
           notesRecycler.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_notes,menu);
        MenuItem menuItem = menu.findItem(R.id.app_bar_search);

        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint("Search Notes here...");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                NotesFilter(newText);
                return false;
            }
        });
           return true;
//        return super.onCreateOptionsMenu(menu);
    }

    private void NotesFilter(String newText) {
        ArrayList<Notes> filterNames = new ArrayList<>();
        for(Notes notes : this.filterNotesalllist){
            if(notes.notesTitle.contains(newText)  || notes.notesSubtitle.contains(newText)){
                filterNames.add(notes);
            }
        }
        this.adapter.searchNotes(filterNames);
    }
}
