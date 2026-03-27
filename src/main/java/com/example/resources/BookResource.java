package com.example.resources;

import java.util.ArrayList;
import java.util.List;

import com.example.models.Book;
import com.google.gson.Gson;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/books")
public class BookResource {
    
    private static List<Book> books = new ArrayList<>();
    private static Gson gson = new Gson();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getBooks() {
        return gson.toJson(books);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addBook(String bookJson){
        Book book = gson.fromJson(bookJson, Book.class);
        books.add(book);
        return Response.status(Response.Status.CREATED).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getBook(@PathParam("id") int id) {
        for (Book b : books) {
            if(b.id == id) return gson.toJson(b);
        }
        return "{}";
    }

    @DELETE
    @Path("/{id}")
    public Response deleteBook(@PathParam("id") int id) {
        books.removeIf(b -> b.id == id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateBook(String bookJson) {
        Book updatedBook = gson.fromJson(bookJson, Book.class);
        for (int i = 0; i < books.size(); i++) {
            if(books.get(i).id == updatedBook.id) {
                books.set(i, updatedBook);
                return Response.ok().build();
            }
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }


}
