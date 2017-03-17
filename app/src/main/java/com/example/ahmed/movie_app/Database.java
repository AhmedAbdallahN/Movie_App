package com.example.ahmed.movie_app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ahmed on 11/4/2016.
 */

public class Database extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "MovieApp";
    private static final int DATABASEVERSION = 4;
    private static final String TABLE_NAME = "Favorite";
    private static final String KEY_ID = "id";
    private static final String KEY_TITLE = "title";
    private static final String KEY_IMAGEPATH = "imagePath";
    private static final String KEY_RATE = "rate";
    private static final String KEY_DESCRIPTION = "description";
    private static final String KEY_YEAR = "year";

    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASEVERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_VIDEOS_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_TITLE + " TEXT," +
                KEY_DESCRIPTION + " TEXT," +
                KEY_IMAGEPATH + " TEXT," +
                KEY_RATE + " Double," +
                KEY_YEAR + " TEXT" +
                ")";
        db.execSQL(CREATE_VIDEOS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE " + TABLE_NAME);
        onCreate(db);
    }

    public void addMovie(Movie newMovie) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_ID, newMovie.getId());
        values.put(KEY_DESCRIPTION, newMovie.getDescription());
        values.put(KEY_IMAGEPATH, newMovie.getImageId());
        values.put(KEY_RATE, newMovie.getRate());
        values.put(KEY_YEAR, newMovie.getYear());
        values.put(KEY_TITLE, newMovie.getTitle());

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public Boolean checkFavorite(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT " + KEY_ID + " FROM " + TABLE_NAME + " WHERE " + KEY_ID + "=" + id;
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public List<Movie> getAllMovies() {
        List<Movie> movieList = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        Movie movie;
        if (cursor.moveToFirst()) {
            do {
                movie = new Movie(
                        cursor.getString(1),
                        cursor.getString(3),
                        Double.parseDouble(cursor.getString(4)),
                        Integer.parseInt(cursor.getString(0)),
                        cursor.getString(2),
                        cursor.getString(5)
                );
                movieList.add(movie);
            } while (cursor.moveToNext());
        }
        return movieList;
    }


    public void deleteMovie(Movie movie) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, KEY_ID + " = ?",
                new String[]{String.valueOf(movie.getId())});
        db.close();
    }
}
