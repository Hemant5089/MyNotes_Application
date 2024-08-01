package com.example.mynotes.Activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.ViewModelProvider;


import com.example.mynotes.Model.Notes;
import com.example.mynotes.R;
import com.example.mynotes.ViewModel.NotesViewModel;
import com.example.mynotes.databinding.ActivityUpdateBinding;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.textview.MaterialTextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class UpdateActivity extends AppCompatActivity {

     ActivityUpdateBinding binding;
     String priority = "1";

     NotesViewModel notesViewModel;

     String stitle,ssubtitle,snotes,spriority;
     int iid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUpdateBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        iid = getIntent().getIntExtra("id",0);
        stitle = getIntent().getStringExtra("title");
        ssubtitle = getIntent().getStringExtra("subtitle");
        snotes = getIntent().getStringExtra("note");
        spriority = getIntent().getStringExtra("priority");

         binding.upTitle.setText(stitle);
         binding.upSub.setText(ssubtitle);
         binding.updateNotes.setText(snotes);

         if(spriority.equals("1")){
             binding.greenPriority.setImageResource(R.drawable.baseline_done_24);
         }
         else if(spriority.equals("2")){
             binding.yellowPriority.setImageResource(R.drawable.baseline_done_24);
         }
         else if (spriority.equals("3")){
             binding.redPriority.setImageResource(R.drawable.baseline_done_24);
         }


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

        binding.updatebtn.setOnClickListener(v -> {

            String title = binding.upTitle.getText().toString();
            String subtitle = binding.upSub.getText().toString();
            String notes = binding.updateNotes.getText().toString();

            UpdateNotes(title,subtitle,notes);

        });



    }

    private void UpdateNotes(String title, String subtitle, String notes) {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("MMMM d, yyyy", Locale.getDefault());
        String formattedDate = sdf.format(date);
        Notes updateNotes = new Notes();
        updateNotes.id = iid;
        updateNotes.notesTitle = title;
        updateNotes.notesSubtitle = subtitle;
        updateNotes.notesDescription = notes;
        updateNotes.notesPriority = priority;
        updateNotes.notesDate = formattedDate;

        notesViewModel.updateNote(updateNotes);

        Toast.makeText(this, "Note Inserted successfully", Toast.LENGTH_SHORT).show();
        finish();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.delete_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.delete_menu){
            BottomSheetDialog sheetDialog = new BottomSheetDialog(UpdateActivity.this,R.style.BottomSheetStyle);
            View view = LayoutInflater.from(UpdateActivity.this).inflate(R.layout.delete_bottom_sheet,(ConstraintLayout)findViewById(R.id.bottom_sheet));
               sheetDialog.setContentView(view);

            MaterialTextView yes ,no;
            yes = view.findViewById(R.id.yes_indicater);
            no = view.findViewById(R.id.no_indicater);

            yes.setOnClickListener(v -> {
                  notesViewModel.deleteNote(iid);
                  finish();
            });

            no.setOnClickListener(v -> {
                      sheetDialog.dismiss();

            });

               sheetDialog.show();
        }
        return true;
    }
}