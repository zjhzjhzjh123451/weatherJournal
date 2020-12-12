package com.example.weatherjournal;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.List;

public class NoteAdapter extends BaseAdapter implements Filterable {
    private Context mContext;

    private List<Note> backList;

    private List<Note> noteList;


    public NoteAdapter(Context mContext, List<Note> noteList){
        this.mContext = mContext;
        this.noteList = noteList;
        this.backList = noteList;
    }

    @Override
    public int getCount() {
        return noteList.size();
    }

    @Override
    public Object getItem(int position) {
        return noteList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = View.inflate(mContext, R.layout.note_layout, null);
        TextView tv_content = (TextView)v.findViewById(R.id.tv_content);
        TextView tv_time = (TextView)v.findViewById(R.id.tv_time);
        String allText = noteList.get(position).getContent();
        tv_content.setText(allText);
        tv_time.setText(noteList.get(position).getTime());
        v.setTag(noteList.get(position).getId());

        return v;
    }

    @Override
    public Filter getFilter() {
        return null;
    }
}
