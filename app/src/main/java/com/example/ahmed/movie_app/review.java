package com.example.ahmed.movie_app;

/**
 * Created by ahmed on 11/19/2016.
 */

public class review {
    String author;
    String review;

    public review(String author, String review) {
        this.author = author;
        this.review = review;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }



}
