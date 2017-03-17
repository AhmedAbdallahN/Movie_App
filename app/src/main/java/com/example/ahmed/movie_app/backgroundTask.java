package com.example.ahmed.movie_app;

import android.content.Context;
import android.os.AsyncTask;
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

/**
 * Created by ahmed on 10/21/2016.
 */
// be generic

public class backgroundTask extends AsyncTask<String, Void, String> {
    // String URL;
   /* MovieAdapter adapter;
    Context context;
    GridView grid;
    List<Movie> movieList;*/

    public backgroundTask() {
    }

    public backgroundTask(MovieAdapter adapter, Context context, GridView grid) {

        // this.URL = URL;

       /* this.grid = grid;
        this.adapter = adapter;
        this.context = context;*/
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String jsonResult) {

    }

    @Override
    protected String doInBackground(String... URL) {
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        try {

            java.net.URL url = new URL(URL[0]);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if (inputStream == null) {
                return null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));
            buffer.append(reader.readLine());
            return buffer.toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
