package com.example.clients.Movie;

public class MainClient {

    public static void main(String[] args) {

        MovieClient client = new MovieClient();

        String movie1 = """
        {
          "id": 1,
          "title": "Inception",
          "genre": "Sci-Fi",
          "year": 2010
        }
        """;

        String movie2 = """
        {
          "id": 2,
          "title": "Interstellar",
          "genre": "Sci-Fi",
          "year": 2014
        }
        """;

        client.addMovie(movie1);
        client.addMovie(movie2);

        String review1 = """
        {
          "id": 1,
          "reviewer": "IMDB",
          "rating": 9,
          "comment": "Amazing"
        }
        """;

        String review2 = """
        {
          "id": 2,
          "reviewer": "Rotten Tomatoes",
          "rating": 8,
          "comment": "Very good"
        }
        """;

        client.addReview(1, review1);
        client.addReview(1, review2);

        System.out.println("All Movies:");
        System.out.println(client.getMovies());

        System.out.println("Movies by Rating:");
        System.out.println(client.getMoviesSorted());
    }
}