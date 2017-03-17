package com.example.ahmed.movie_app;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by ahmed on 11/11/2016.
 */

public class listAdapter extends ArrayAdapter{
    Context context;
    List<tralier> objects;
    public listAdapter(Context context, int resource, List<tralier> objects) {
        super(context, resource, objects);
        this.context = context;
        this.objects = objects;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
        TextView textView = (TextView)row.findViewById(R.id.textView3);
        textView.setText(objects.get(position).getName());
        return row;
    }
}
