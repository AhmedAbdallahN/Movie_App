package com.example.ahmed.movie_app;

/**
 * Created by ahmed on 11/21/2016.
 */

public class tralier {
    private String Key;
    private String Name;

    public tralier(String name, String key) {
        Name = name;
        Key = key;
    }

    public tralier() {
    }

    public String getKey() {
        return Key;
    }

    public void setKey(String key) {
        Key = key;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
