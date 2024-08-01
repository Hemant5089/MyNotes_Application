package com.example.mynotes.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mynotes.Activity.UpdateActivity;
import com.example.mynotes.MainActivity;
import com.example.mynotes.Model.Notes;
import com.example.mynotes.R;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;
import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.notesViewholder> {

    MainActivity mainActivity;
    List<Notes> notes;
    List<Notes> allNotesItem;
    public NotesAdapter(MainActivity mainActivity, List<Notes> notes) {
        this.mainActivity = mainActivity;
        this.notes = notes;
        allNotesItem = new ArrayList<>(notes);
    }
        public void searchNotes(List<Notes> filterName){
           this.notes = filterName;
           notifyDataSetChanged();
        }


    @NonNull
    @Override
    public NotesAdapter.notesViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new notesViewholder(LayoutInflater.from(mainActivity).inflate(R.layout.item_notes,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull NotesAdapter.notesViewholder holder, int position) {
        Notes note = this.notes.get(position);

        if (note.notesPriority != null) { // Add this null check
            if (note.notesPriority.equals("1")) {
                holder.notesPriority.setBackgroundResource(R.drawable.green_shape);
            } else if (note.notesPriority.equals("2")) {
                holder.notesPriority.setBackgroundResource(R.drawable.yello_shape);
            } else {
                holder.notesPriority.setBackgroundResource(R.drawable.red_shape);
            }
        } else {
            // Handle the case where notesPriority is null, e.g., set a default background
            holder.notesPriority.setBackgroundResource(R.drawable.green_shape); // Replace with your default shape
        }


        holder.title.setText(note.notesTitle);
        holder.subtitle.setText(note.notesSubtitle);
        holder.date.setText(note.notesDate);

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(mainActivity, UpdateActivity.class);

            intent.putExtra("id",note.id);
            intent.putExtra("title",note.notesTitle);
            intent.putExtra("subtitle",note.notesSubtitle);
            intent.putExtra("note",note.notesDescription);
            intent.putExtra("priority",note.notesPriority);
            mainActivity.startActivity(intent);

        });
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    class notesViewholder extends RecyclerView.ViewHolder{

        MaterialTextView title,subtitle,date;
        View notesPriority;
        public notesViewholder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.notesTitle);
            subtitle = itemView.findViewById(R.id.notesSubtitle);
            date = itemView.findViewById(R.id.notesDate);
            notesPriority = itemView.findViewById(R.id.notesPriority);
        }
    }
}
