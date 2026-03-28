package com.example.models;

import java.util.ArrayList;
import java.util.List;

public class Movie {
    public int id;
    public String title;
    public String genre;
    public int year;
    
    public List<Review> reviews = new ArrayList<>();

    public double getAverageRating() {
        if (reviews == null || reviews.isEmpty()) return 0;

        double sum = 0;
        for (Review r : reviews) {
            sum += r.rating;
        }

        return sum / reviews.size();
    }
}
