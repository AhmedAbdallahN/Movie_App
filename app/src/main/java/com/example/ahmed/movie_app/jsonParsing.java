package com.example.ahmed.movie_app;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ahmed on 11/4/2016.
 */

public class jsonParsing {

    public List<Movie> movieParsing(String json) {
        List<Movie> movieList = new ArrayList<>();
        try {


            JSONObject mainObject = new JSONObject(json);
            JSONArray movieArray = mainObject.optJSONArray("results");
            Movie newMovie;
            JSONObject newMovieObj;
            for (int i = 0; i < movieArray.length(); i++) {
                newMovieObj = movieArray.getJSONObject(i);
                String title = newMovieObj.getString("title");
                double rate = Double.parseDouble(newMovieObj.getString("vote_average"));
                String detalies = newMovieObj.getString("overview");
                String year = newMovieObj.getString("release_date");
                String imagPath = "https://image.tmdb.org/t/p/w500/" + newMovieObj.getString("poster_path");
                int id = newMovieObj.getInt("id");
                newMovie = new Movie(title, imagPath, rate, id, detalies, year);
                movieList.add(newMovie);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return movieList;
    }

    public List<tralier> traliersParsing(String json){
        try {
            List<tralier> traliersList = new ArrayList<>();
            JSONObject mainObject = new JSONObject(json);
            JSONArray strArray = mainObject.optJSONArray("results");
            JSONObject newstrObj;
            tralier newtralier ;
            for (int i = 0 ; i < strArray.length();i++){
                newstrObj = strArray.getJSONObject(i);
             newtralier = new tralier(newstrObj.getString("name"),newstrObj.getString("key"));
                traliersList.add(newtralier);
            }

            return traliersList;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;

    }

    public  List<review> reviewsParsing (String json){
        try {
            List<review> reviewsList = new ArrayList<>();
            JSONObject mainObject = new JSONObject(json);
            JSONArray strArray = mainObject.optJSONArray("results");
            JSONObject reviewsObject;
            review newReview;
            for (int i = 0;i<strArray.length();i++){
                reviewsObject = strArray.getJSONObject(i);
                String author = reviewsObject.getString("author");
                String review = reviewsObject.getString("content");
                newReview = new review(author,review);
                reviewsList.add(newReview);
            }
            return reviewsList;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
