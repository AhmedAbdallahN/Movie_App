package com.example.ahmed.movie_app;

import android.content.Context;
import android.media.Image;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by ahmed on 10/10/2016.
 */

public class MovieAdapter extends ArrayAdapter {

    private Context context;
    private List<Movie> list;

    private static class resycleAdapter {
        public View row;
        public Movie griditem;
        public ImageView imageView;

    }

    public MovieAdapter(Context context, int resource, List list) {
        super(context, resource, list);
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        if (row == null) {
            row = LayoutInflater.from(context).inflate(R.layout.grid_item, parent, false);
            resycleAdapter Holder = new resycleAdapter();
            Holder.imageView = (ImageView) row.findViewById(R.id.imageView);
            Holder.griditem = list.get(position);
            row.setTag(Holder);
        }
        final resycleAdapter Holder = (resycleAdapter) row.getTag();


        Holder.griditem = list.get(position);
        Holder.imageView = (ImageView) row.findViewById(R.id.imageView);
        Picasso.with(context).load(Holder.griditem.getImageId()).into(Holder.imageView, new Callback() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onError() {
              //Holder.imageView.setImageResource();
            }
        });

        return row;
    }
}
