package com.example.mynotes.Activity;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.mynotes.Model.Notes;
import com.example.mynotes.R;
import com.example.mynotes.ViewModel.NotesViewModel;
import com.example.mynotes.databinding.ActivityInsertBinding;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class InsertActivity extends AppCompatActivity {
    ActivityInsertBinding binding;
    String title, subtitle, notes;

    NotesViewModel notesViewModel;

    String priority = "1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityInsertBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        notesViewModel = new ViewModelProvider(this).get(NotesViewModel.class);



        binding.greenPriority.setOnClickListener(v -> {
              binding.greenPriority.setImageResource(R.drawable.baseline_done_24);
              binding.yellowPriority.setImageResource(0);
              binding.redPriority.setImageResource(0);
           priority = "1";

        });

        binding.yellowPriority.setOnClickListener(v -> {
            binding.greenPriority.setImageResource(0);
            binding.yellowPriority.setImageResource(R.drawable.baseline_done_24);
            binding.redPriority.setImageResource(0);
            priority = "2";

        });

        binding.redPriority.setOnClickListener(v -> {
            binding.greenPriority.setImageResource(0);
            binding.yellowPriority.setImageResource(0);
            binding.redPriority.setImageResource(R.drawable.baseline_done_24);
            priority = "3";

        });


        binding.inserbtn.setOnClickListener(v -> {
            title = binding.addtitle.getText().toString();
            subtitle = binding.addsub.getText().toString();
            notes = binding.notes.getText().toString();

            CreateNotes(title, subtitle, notes);
        });
    }

    private void CreateNotes(String title, String subtitle, String notes) {
        Date date = new Date();

        // Use SimpleDateFormat for custom date formatting
        SimpleDateFormat sdf = new SimpleDateFormat("MMMM d, yyyy", Locale.getDefault());
        String formattedDate = sdf.format(date);

        Notes notes1 = new Notes();
        notes1.notesTitle = title;
        notes1.notesSubtitle = subtitle;
        notes1.notesPriority = priority;
        notes1.notesDescription = notes;
        notes1.notesDate = formattedDate;

        notesViewModel.insertNote(notes1);

        Toast.makeText(this, "Note Inserted successfully", Toast.LENGTH_SHORT).show();
        finish();
    }
}
