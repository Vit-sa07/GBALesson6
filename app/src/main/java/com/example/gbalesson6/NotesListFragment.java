package com.example.gbalesson6;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class NotesListFragment extends Fragment {

    public static final String CURRENT_NOTE = "CurrentNote";
    private Notes selectedNote;
    private List<Notes> notesArray = new ArrayList<>();
    private boolean isLandscape;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_notes_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Notes note1 = new Notes("Купить хлеб", "После работы", "17.06.2021", 1);
        Notes note2 = new Notes("Задачи на сегодня", "1) Умыться\n2) Поспать\n3) Позвонить", "18.06.2021", 2);
        Notes note3 = new Notes("Книги", "Война и мир\nБудующее \nОно 2\n Я", "20.06.2021", 3);
        Notes note4 = new Notes("Фильмы", "Оно\n Max - 04.07\n Звездные войны", "22.06.2021", 4);
        Notes note5 = new Notes("Советы", "Улыбаться\nСмотреть\n Интернет", "17.06.2021", 5);
        notesArray.add(note1);
        notesArray.add(note2);
        notesArray.add(note3);
        notesArray.add(note4);
        notesArray.add(note5);

        initList(view);

    }
    private void initList(View view) {
        LinearLayout layoutView = (LinearLayout)view;
        for(Notes note : notesArray ){
            TextView tv = new TextView(getContext());
            String tvString = note.getNoteIndex() + ": " + note.getHeading();
            tv.setText(tvString);
            tv.setTextSize(30);
            layoutView.addView(tv);
            tv.setOnClickListener(v -> {
                selectedNote = note;
                showNoteDetails(selectedNote);
            });

        }
    }

    private void showNoteDetails(Notes selectedNote) {
        if (isLandscape){
            showLandNoteDetails(selectedNote);
        } else {
            showPortNoteDetails(selectedNote);
        }
    }

    private void showPortNoteDetails(Notes selectedNote) {
        Intent intent = new Intent();
        intent.setClass(getActivity(),DetailsActivity.class);
        intent.putExtra(NoteDetailsFragment.ARG_INDEX, selectedNote);
        startActivity(intent);
    }

    private void showLandNoteDetails(Notes selectedNote) {
        NoteDetailsFragment details = NoteDetailsFragment.newInstance(selectedNote);

        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameNoteDetails, details);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        fragmentTransaction.commit();
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        isLandscape = getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putParcelable(CURRENT_NOTE, selectedNote);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if(savedInstanceState != null){
            selectedNote = savedInstanceState.getParcelable(CURRENT_NOTE);
        } else {
            selectedNote = notesArray.get(0);
        }

        if (isLandscape) {
            showLandNoteDetails(selectedNote);
        }
    }


}
