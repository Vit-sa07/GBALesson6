package com.example.gbalesson6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.os.Bundle;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            finish();
            return;
        }

        if(savedInstanceState == null){

            NoteDetailsFragment details = new NoteDetailsFragment();
            details.setArguments(getIntent().getExtras());

            getSupportFragmentManager().beginTransaction().replace(R.id.frameNoteDetails, details).commit();
        }
    }
}
