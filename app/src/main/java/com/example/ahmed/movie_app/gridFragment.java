package com.example.ahmed.movie_app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.List;


public class gridFragment extends Fragment {
    List<Movie> movieList;
    MovieAdapter adapter;
    GridView grid;
    Database db;
    passMovie sendMovie;
    public static Boolean favorite = false;

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View page = inflater.inflate(R.layout.fragment_grid, container, false);


        db = new Database(getContext());
        grid = (GridView) page.findViewById(R.id.gridView);
        String url = "https://api.themoviedb.org/3/discover/movie?api_key=&language=en-US";

        backgroundTask task = new backgroundTask() {
            @Override
            protected void onPostExecute(String jsonResult) {

                jsonParsing parsing = new jsonParsing();
                movieList = parsing.movieParsing(jsonResult);
                adapter = new MovieAdapter(getContext(), R.layout.grid_item, movieList);
                grid.setAdapter(adapter);
                sendMovie.passMovie(movieList.get(0));

            }
        };

        task.execute(url);
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Movie descreptionMovie = movieList.get(position);
                if (MainActivity.large || MainActivity.xlarge) {
                    sendMovie.passMovie(descreptionMovie);
                } else {
                    Intent intent = new Intent(getContext(), DescreptionActivity.class);
                    intent.putExtra("movieObject", descreptionMovie);
                    startActivity(intent);
                }
            }
        });
        return page;
    }


    public void updateGrid(int id) {
        String url;
        backgroundTask task = new backgroundTask() {
            @Override
            protected void onPostExecute(String jsonResult) {

                jsonParsing parsing = new jsonParsing();
                movieList.clear();
                movieList = parsing.movieParsing(jsonResult);
                adapter.clear();
                adapter.addAll(movieList);
                adapter.notifyDataSetChanged();
                sendMovie.passMovie(movieList.get(0));
            }
        };
        if (id == R.id.topRated) {
            favorite = false;
            url = "https://api.themoviedb.org/3/movie/top_rated?api_key=&language=en-US";
            task.execute(url);
        } else if (id == R.id.popular) {
            favorite = false;
            url = "https://api.themoviedb.org/3/movie/popular?api_key=&language=en-US";
            task.execute(url);
        } else {
            favorite = true;
            movieList.clear();
            movieList = db.getAllMovies();
            adapter.clear();
            if (movieList.size() != 0) {
                adapter.addAll(movieList);
            }
            adapter.notifyDataSetChanged();
            if (movieList.size() != 0) {
                sendMovie.passMovie(movieList.get(0));
            }
        }

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        sendMovie = (passMovie) activity;

    }

    public interface passMovie {
        public void passMovie(Movie movie);

    }
}
