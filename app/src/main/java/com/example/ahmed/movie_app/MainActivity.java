package com.example.ahmed.movie_app;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements gridFragment.passMovie ,mobileView.refresh {
    List<Movie> movieList;
    MovieAdapter adapter;
    GridView grid;
    Database db;
    Boolean favorite = false;
    public static Boolean large;
    public static Boolean xlarge;
    gridFragment gridFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        xlarge = ((getApplicationContext().getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == 4);
        large = ((getApplicationContext().getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_LARGE);

        if (large || xlarge) {
            setContentView(R.layout.larg_screen);

        } else {
            setContentView(R.layout.activity_main);
            android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
            android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            gridFragment = new gridFragment();
            fragmentTransaction.replace(android.R.id.content, gridFragment);
            fragmentTransaction.commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (large || xlarge) {
            gridFragment grid = (gridFragment) getSupportFragmentManager().findFragmentById(R.id.gridFragment);
            grid.updateGrid(itemId);
        } else {
            gridFragment.updateGrid(itemId);
        }

        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (favorite) {
            movieList.clear();
            movieList = db.getAllMovies();
            adapter.clear();
            adapter.addAll(movieList);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void passMovie(Movie movie) {
        mobileView details = (mobileView) getSupportFragmentManager().findFragmentById(R.id.detailsFragment);
        details.updateMovie(movie);
    }


    @Override
    public void refresh() {
        gridFragment grid = (gridFragment) getSupportFragmentManager().findFragmentById(R.id.gridFragment);
        grid.updateGrid(0);
    }
}
