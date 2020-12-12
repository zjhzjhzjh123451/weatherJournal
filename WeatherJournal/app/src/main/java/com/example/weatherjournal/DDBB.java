package com.example.weatherjournal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DDBB {
    SQLiteOpenHelper dbHandler;
    SQLiteDatabase db;

    private static final String[] columns = {
            NoteDataBase.ID,
            NoteDataBase.CONTENT,
            NoteDataBase.TIME,
            NoteDataBase.MODE
    };

    public DDBB(Context context){
        dbHandler = new NoteDataBase(context);
    }

    public void open(){
        db = dbHandler.getWritableDatabase();
    }

    public void close(){
        dbHandler.close();
    }

    public Note addNote(Note note){
        ContentValues contentValues = new ContentValues();
        contentValues.put(NoteDataBase.CONTENT,note.getContent());
        contentValues.put(NoteDataBase.TIME,note.getTime());
        contentValues.put(NoteDataBase.MODE,note.getTag());
        long insertId = db.insert(NoteDataBase.TABLE_NAME,null,contentValues);
        note.setId(insertId);
        return note;
    }

    public Note getNote(long id){
        Cursor cursor = db.query(NoteDataBase.TABLE_NAME,columns,NoteDataBase.ID + "=?",
                new String[]{String.valueOf(id)},null,null,null);
        if (cursor != null) cursor.moveToFirst();
        Note e = new Note(cursor.getString(1),cursor.getString(2),cursor.getInt(3));
        return e;
    }

    public List<Note> getAllNotes(){
        Cursor cursor = db.query(NoteDataBase.TABLE_NAME,columns,null,null,null,null,null);
        List<Note> notes = new ArrayList<>();
        if (cursor.getCount()>0){
            while (cursor.moveToNext()){
                Note note = new Note();
                note.setId(cursor.getLong(cursor.getColumnIndex(NoteDataBase.ID)));
                note.setContent(cursor.getString(cursor.getColumnIndex(NoteDataBase.CONTENT)));
                note.setTime(cursor.getString(cursor.getColumnIndex(NoteDataBase.TIME)));
                note.setTag(cursor.getInt(cursor.getColumnIndex(NoteDataBase.MODE)));
                notes.add(note);
            }
        }
        return notes;
    }

    public int updateNote(Note note){
        ContentValues values = new ContentValues();
        values.put(NoteDataBase.CONTENT,note.getContent());
        values.put(NoteDataBase.TIME,note.getTime());
        values.put(NoteDataBase.MODE,note.getTag());
        return db.update(NoteDataBase.TABLE_NAME,values,NoteDataBase.ID+"=?",new String[]{String.valueOf(note.getId())});
    }

    public void removeNote(Note note){
        db.delete(NoteDataBase.TABLE_NAME,NoteDataBase.ID + "=" + note.getId(),null);
    }
}
