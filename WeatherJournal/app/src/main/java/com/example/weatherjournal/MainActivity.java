package com.example.weatherjournal;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    FloatingActionButton addButton;
    FloatingActionButton weatherButton;

    private ListView lv;
    private NoteDataBase dbHelper;
    private NoteAdapter adapter;
    private List<Note> noteList = new ArrayList<>();
    private Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addButton = (FloatingActionButton) findViewById(R.id.fab);
        weatherButton = findViewById(R.id.weather);
        lv = findViewById(R.id.lv);
        adapter = new NoteAdapter(getApplicationContext(), noteList);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(this);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, EditActivity.class);
                intent.putExtra("mode", 4);
                startActivityForResult(intent, 0);
            }
        });

        weatherButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,WeatherActivity.class);
                startActivity(intent);
            }
        });
        refreshListView();

    }


    public void refreshListView() {

        DDBB op = new DDBB(context);
        op.open();
        // set adapter
        if (noteList.size() > 0) noteList.clear();
        noteList.addAll(op.getAllNotes());
        op.close();
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        int returnMode;
        long note_Id;
        returnMode = data.getExtras().getInt("mode", -1);
        note_Id = data.getExtras().getLong("id", 0);


        if (returnMode == 1) {  //update current note

            String content = data.getExtras().getString("content");
            String time = data.getExtras().getString("time");
            int tag = data.getExtras().getInt("tag", 1);

            Note newNote = new Note(content, time, tag);
            newNote.setId(note_Id);
            DDBB op = new DDBB(context);
            op.open();
            op.updateNote(newNote);
            op.close();
        } else if (returnMode == 0) {  // create new note
            String content = data.getExtras().getString("content");
            String time = data.getExtras().getString("time");
            int tag = data.getExtras().getInt("tag", 1);

            Note newNote = new Note(content, time, tag);
            DDBB op = new DDBB(context);
            op.open();
            op.addNote(newNote);
            op.close();
        } else if (returnMode == 2) { // delete
            Note curNote = new Note();
            curNote.setId(note_Id);
            DDBB op = new DDBB(context);
            op.open();
            op.removeNote(curNote);
            op.close();
        }
        else{

        }
        refreshListView();
        super.onActivityResult(requestCode, resultCode, data);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.lv:
                Note curNote = (Note) parent.getItemAtPosition(position);
                Intent intent = new Intent(MainActivity.this, EditActivity.class);
                intent.putExtra("content", curNote.getContent());
                intent.putExtra("id", curNote.getId());
                intent.putExtra("time", curNote.getTime());
                intent.putExtra("tag", curNote.getTag());
                intent.putExtra("mode",3);// edit mode
                startActivityForResult(intent, 1);
                break;
        }
    }
}