package com.nl.learn_android_bt1;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends ArrayAdapter {
    Context context;
    ArrayList<Note> note=new ArrayList<>();
    LayoutInflater inflater;
    int resource;

    public MyAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Note> note) {
        super(context, resource, note);
        this.context=context;
        this.resource=resource;
        this.note=note;
        inflater=(LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView==null){
            convertView=inflater.inflate(resource,parent,false);
        }
        TextView tvTitle=convertView.findViewById(R.id.tvTitle);
        TextView tvContain=convertView.findViewById(R.id.tvContain);
        TextView tvLink=convertView.findViewById(R.id.tvLink);
        tvTitle.setText(note.get(position).getTitle());
        tvContain.setText(note.get(position).getContain());
        tvLink.setText(note.get(position).getLink());
        return convertView;
    }
}
