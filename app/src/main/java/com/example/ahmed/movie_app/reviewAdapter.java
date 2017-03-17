package com.example.ahmed.movie_app;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by ahmed on 11/19/2016.
 */

public class reviewAdapter extends ArrayAdapter {
    Context context;
    List<review> reviews;
    public reviewAdapter(Context context, int resource, List<review> objects) {
        super(context, resource, objects);
        this.context = context;
        this.reviews = objects;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = LayoutInflater.from(context).inflate(R.layout.review_list, parent, false);
        TextView author = (TextView) row.findViewById(R.id.author);
        TextView content = (TextView) row.findViewById(R.id.content);
        review newReview = reviews.get(position);
author.setText(newReview.getAuthor());
content.setText(newReview.getReview());
        return row;
    }
}
