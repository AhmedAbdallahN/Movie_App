package com.example.ahmed.movie_app;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

public class DescreptionActivity extends AppCompatActivity {
    Movie movie;
    List<String> traliers;
    listAdapter traliersAdapter;
    List<review> reviews;
    ListView listView;
    ListView reviewList;
    reviewAdapter reviewAdapter;
    Database db;
    Boolean favorite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_descreption);
        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        mobileView mobile = new mobileView();
        fragmentTransaction.replace(android.R.id.content, mobile);
        fragmentTransaction.commit();
        db = new Database(getApplicationContext());
        Intent intetn = getIntent();
        movie = (Movie) intetn.getSerializableExtra("movieObject");
        Bundle bundle = new Bundle();
        bundle.putSerializable("movie", movie);
        mobile.setArguments(bundle);
    }

}
