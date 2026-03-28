package com.example.resources;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.example.models.Movie;
import com.example.models.Review;
import com.google.gson.Gson;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/movies")
public class MovieResource {

    private final static List<Movie> movies = new ArrayList<>();
    private final static Gson gson = new Gson();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getMovies(@QueryParam("sort") String sort) {

        if ("rating".equalsIgnoreCase(sort)) {
            movies.sort(Comparator.comparingDouble(Movie::getAverageRating).reversed());
        }

        return gson.toJson(movies);
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getMovie(@PathParam("id") int id) {
        for (Movie m : movies) {
            if (m.id == id) return gson.toJson(m);
        }
        return "{}";
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addMovie(String json) {
        Movie m = gson.fromJson(json, Movie.class);
        movies.add(m);
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateMovie(@PathParam("id") int id, String json) {
        Movie updated = gson.fromJson(json, Movie.class);

        for (int i = 0; i < movies.size(); i++) {
            if (movies.get(i).id == id) {
                updated.id = id;
                movies.set(i, updated);
                return Response.ok().build();
            }
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteMovie(@PathParam("id") int id) {
        movies.removeIf(m -> m.id == id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @POST
    @Path("/{id}/reviews")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addReview(@PathParam("id") int id, String json) {

        Review r = gson.fromJson(json, Review.class);

        for (Movie m : movies) {
            if (m.id == id) {
                m.reviews.add(r);
                return Response.status(Response.Status.CREATED).build();
            }
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @GET
    @Path("/{id}/reviews")
    @Produces(MediaType.APPLICATION_JSON)
    public String getReviews(@PathParam("id") int id) {

        for (Movie m : movies) {
            if (m.id == id) {
                return gson.toJson(m.reviews);
            }
        }

        return "{}";
    }

    @DELETE
    @Path("/{movieId}/reviews/{reviewId}")
    public Response deleteReview(@PathParam("movieId") int movieId,
                                 @PathParam("reviewId") int reviewId) {

        for (Movie m : movies) {
            if (m.id == movieId) {
                m.reviews.removeIf(r -> r.id == reviewId);
                return Response.status(Response.Status.NO_CONTENT).build();
            }
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }
}