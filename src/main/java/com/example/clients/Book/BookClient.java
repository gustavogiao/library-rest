package com.example.clients.Book;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

public class BookClient {
    
    private Client client;
    private WebTarget target;
    
    public BookClient() {
        client = ClientBuilder.newClient();
        target = client.target("http://localhost:8080/books");
    }

    public String getBooks() {
        Response r = target.request()
                .accept(MediaType.APPLICATION_JSON)
                .get();

        if (r.getStatus() == 200) {
            return r.readEntity(String.class);
        }
        return "Erro";
    }

    public void addBook(String json) {
        target.request()
                .post(jakarta.ws.rs.client.Entity.entity(json, MediaType.APPLICATION_JSON));
    }
}
