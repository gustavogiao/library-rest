package com.example.clients.Movie;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

public class MovieClient {

    private final Client client;
    private final WebTarget target;

    public MovieClient() {
        client = ClientBuilder.newClient();
        target = client.target("http://localhost:8080/movies");
    }

    public String getMovies() {
        Response r = target.request()
                .accept(MediaType.APPLICATION_JSON)
                .get();

        return r.readEntity(String.class);
    }

    public String getMoviesSorted() {
        Response r = target
                .queryParam("sort", "rating")
                .request()
                .get();

        return r.readEntity(String.class);
    }

    public void addMovie(String json) {
        target.request()
                .post(Entity.entity(json, MediaType.APPLICATION_JSON));
    }

    public void addReview(int movieId, String json) {
        target.path(String.valueOf(movieId))
              .path("reviews")
              .request()
              .post(Entity.entity(json, MediaType.APPLICATION_JSON));
    }
}